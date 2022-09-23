package org.example.lib;
import java.lang.annotation.*;
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AliasFor {
    /**
     * attribute的别名，用来简化编码
     */
    @AliasFor("attribute")
    String value() default "";

    /**
     * 要指向的属性名
     */
    @AliasFor("value")
    String attribute() default "";

    /**
     * 包含源属性定义的注解，Annotation.class表示当前注解
     */
    Class<? extends Annotation> annotation() default Annotation.class;
}