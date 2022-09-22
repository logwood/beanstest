package org.example;
import java.lang.annotation.*;
import java.lang.annotation.Annotation;

public interface BeanFactory {
    Object getBean(String str);
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface Component {
    String value() default "";
}


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component // 关键注解
public @interface Controller {
    @AliasFor(annotation = Component.class)
    String value() default "";
}


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component // 关键注解
public @interface Service {
    @AliasFor(annotation = Component.class)
    String value() default "";
}


