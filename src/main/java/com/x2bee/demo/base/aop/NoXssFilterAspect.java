/**
 * 
 */
package com.x2bee.demo.base.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.x2bee.demo.base.util.RequestUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author choiyh44
 * @version 1.0
 * @since 2022. 4. 12.
 *
 */
@Aspect
@Component
@Slf4j
public class NoXssFilterAspect {
    @Around("@annotation(com.x2bee.demo.base.annotation.NoXssFilter)")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        RequestUtils.setAttribute("NoXssFilter", true);
        Object result = pjp.proceed();
        return result;
    }

}
