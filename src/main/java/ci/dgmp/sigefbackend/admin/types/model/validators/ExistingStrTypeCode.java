package ci.dgmp.sigefbackend.admin.types.model.validators;

import ci.dgmp.sigefbackend.admin.types.controller.repositories.TypeRepo;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import ci.dgmp.sigefbackend.admin.utilities.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistingStrTypeCode.Validator.class)
@Documented
public @interface ExistingStrTypeCode 
{
    String message() default "Le type de structure avec le code '{validatedValue}' n'existe pas";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    boolean allowNull() default false;

    @Component
    class Validator implements ConstraintValidator<ExistingStrTypeCode, String> 
    {
        @Autowired
        private TypeRepo typeRepo;

        private boolean allowNull;

        @Override
        public void initialize(ExistingStrTypeCode constraintAnnotation) 
        {
            this.allowNull = constraintAnnotation.allowNull();
        }

        @Override
        public boolean isValid(String code, ConstraintValidatorContext context) 
        {
            if (StringUtils.isBlank(code)) return allowNull;
            return typeRepo.existsByCodeAndGroupCode(code.toUpperCase(), "STR");
        }
    }
}
