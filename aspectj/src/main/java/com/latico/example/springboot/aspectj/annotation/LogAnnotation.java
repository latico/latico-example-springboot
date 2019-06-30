package com.latico.example.springboot.aspectj.annotation;

import java.lang.annotation.*;

/**
 * <PRE>
 * 这里填写类注释
 * </PRE>
 *
 * @author zhangmaolin
 * @version 1.0.0
 * @since 2019-03-18 09:43
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String value() default "";
}
