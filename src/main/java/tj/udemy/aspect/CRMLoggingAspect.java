package tj.udemy.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;

@Component
@Aspect
public class CRMLoggingAspect {

    private final Logger myLogger = Logger.getLogger(getClass().getName());


    @Before("tj.udemy.aspect.PointCut.appFlow()")
    public void beforeAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toLongString();

        myLogger.info("============> Calling @Before - method name: " + methodName);

        Arrays.asList(joinPoint.getArgs()).stream()
                .filter(Objects::nonNull)
                .forEachOrdered(el -> myLogger.info("----> arguments: " + el.toString()));

    }

    @AfterReturning(pointcut = "tj.udemy.aspect.PointCut.appFlow()", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {

        myLogger.info(":::::::@AfterReturning method name " + joinPoint.toShortString());

        if (result != null) {
            myLogger.info("###########Returning object: " + result.toString());
        }


    }

}
