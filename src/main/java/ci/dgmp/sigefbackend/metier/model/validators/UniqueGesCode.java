package ci.dgmp.sigefbackend.metier.model.validators;

import ci.dgmp.sigefbackend.admin.utilities.StringUtils;
import ci.dgmp.sigefbackend.metier.model.dtos.GestionDTO;
import ci.dgmp.sigefbackend.metier.repositories.GestionRepo;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueGesCode.FieldValidator.class, UniqueGesCode.TypeValidator.class})
@Documented
public @interface UniqueGesCode {
    String message() default "La gestion avec le code '{validatedValue}' existe déjà";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    boolean allowNull() default false;

    @Component
    class FieldValidator implements ConstraintValidator<UniqueGesCode, Long> {
        @Autowired
        private GestionRepo gestionRepo;
        private boolean allowNull;

        @Override
        public void initialize(UniqueGesCode constraintAnnotation) {
            this.allowNull = constraintAnnotation.allowNull();
        }

        @Override
        public boolean isValid(Long code, ConstraintValidatorContext context) {
            if (code == null) return allowNull;
            return !gestionRepo.existsById(code);
        }
    }

    @Component
    class TypeValidator implements ConstraintValidator<UniqueGesCode, GestionDTO> {
        @Autowired
        private GestionRepo gestionRepo;
        private boolean allowNull;

        @Override
        public void initialize(UniqueGesCode constraintAnnotation) {
            this.allowNull = constraintAnnotation.allowNull();
        }

        @Override
        public boolean isValid(GestionDTO dto, ConstraintValidatorContext context) {
            if (dto == null) return true;
            Long code = dto.getGesCode();
            if (code == null) return allowNull;
            return !gestionRepo.existsById(code);
        }
    }
}
