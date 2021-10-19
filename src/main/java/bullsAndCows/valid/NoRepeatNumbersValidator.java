package bullsAndCows.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoRepeatNumbersValidator implements
        ConstraintValidator<NoRepeatNumbers, Integer> {

    @Override
    public void initialize(NoRepeatNumbers constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer s, ConstraintValidatorContext constraintValidatorContext) {
        String number = String.valueOf(s);
        for(int i = 0; i < number.length(); i++ ){
            for(int j = 0; j < number.length(); j++){
                if(number.charAt(i) == number.charAt(j) && i != j){
                    return false;
                }
            }
        }
        return true;
    }
}
