package bullsAndCows.valid;

import bullsAndCows.valid.NoRepeatNumbersValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = NoRepeatNumbersValidator.class)
@Documented
public @interface NoRepeatNumbers {

    String message() default "Число не должно содержать повторяющихся чисел";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
