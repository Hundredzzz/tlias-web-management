package com.hundredz.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hundredz.mapper.OperateLogMapper;
import com.hundredz.pojo.OperateLog;

import com.hundredz.utils.CurrentHolder;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.security.auth.login.LoginContext;
import java.time.LocalDateTime;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    private final ObjectMapper objectMapper = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    // 切点：只切 controller 包下所有方法
    @Pointcut("@annotation(com.hundredz.anno.Log)")
    private void pt() {}

    // 环绕通知，只记录增删改
    @Around("pt()")
    public Object recordLog(ProceedingJoinPoint pjp) throws Throwable {
        OperateLog olog = new OperateLog();
        long start = System.currentTimeMillis();

        // 1. 获取方法，判断是否增删改（只记录这些）
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        boolean isInsert = method.isAnnotationPresent(PostMapping.class);
        boolean isUpdate = method.isAnnotationPresent(PutMapping.class);
        boolean isDelete = method.isAnnotationPresent(DeleteMapping.class);

        // 不是增删改，直接放行不记录
        if (!isInsert && !isUpdate && !isDelete) {
            return pjp.proceed();
        }

        try {
            // 2. 执行目标方法
            Object result = pjp.proceed();

            // 3. 封装日志信息
            // 操作时间
            olog.setOperateTime(LocalDateTime.now());

            // 类全限定名
            String className = pjp.getTarget().getClass().getName();
            olog.setClassName(className);

            // 方法名
            String methodName = method.getName();
            olog.setMethodName(methodName);

            // 方法参数（转JSON）
            String params = objectMapper.writeValueAsString(pjp.getArgs());
            olog.setMethodParams(params);

            // 返回值
            String returnValue = objectMapper.writeValueAsString(result);
            olog.setReturnValue(returnValue);

            // 耗时
            long costTime = System.currentTimeMillis() - start;
            olog.setCostTime(costTime);

            // 操作人ID（从登录上下文获取，如ThreadLocal、SecurityContext）
            // 示例：log.setOperateEmpId(LoginContext.getCurrentEmpId());
            // 这里先用固定值，你替换成真实登录用户ID即可
            olog.setOperateEmpId(getCurrentUserId());

            // 4. 保存日志
            operateLogMapper.insert(olog);

            log.info("记录日志：{}",olog);

            return result;
        } catch (Throwable e) {
            // 异常也可以选择记录，这里按需求只记录成功执行的
            throw e;
        }


    }
    private Integer getCurrentUserId() {


        return CurrentHolder.getCurrentId();

    }
}