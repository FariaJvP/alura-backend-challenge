package br.com.alura.videoapi.funcionalidades.cadastravideo;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = { UniqueURLValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueURL {

    String message() default "";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    Class<?> domainClass();
    String fieldName();
}
