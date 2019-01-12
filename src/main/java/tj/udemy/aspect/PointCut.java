package tj.udemy.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class PointCut {

    @Pointcut("execution(* tj.udemy.controller.*.*(..))")
    public void methodsFromControllerPackage() {
    }

    @Pointcut("execution(* tj.udemy.DAO.*.*(..))")
    public void methodsFromDAOPackage() {
    }

    @Pointcut("execution(* tj.udemy.service.*.*(..))")
    public void methodsFromServicePackage() {
    }


    @Pointcut("methodsFromControllerPackage() || methodsFromDAOPackage() || methodsFromServicePackage()")
    public void appFlow() {
    }
}
