package com.rsd.anno;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
//@Documented
//@Inherited
public @interface FirstAnno {

    String name() default "sss";

    int aaa() default 9;

    String value();

    String haha() default "aaa";
}
