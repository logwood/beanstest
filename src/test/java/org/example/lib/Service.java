package org.example.lib;

import org.example.lib.AliasFor;
import org.example.lib.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component // 关键注解
public @interface Service {
    @AliasFor(annotation = Component.class)
    String value() default "";
}
