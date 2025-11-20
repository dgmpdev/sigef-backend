package ci.dgmp.sigefbackend.metier.model.validators;

import ci.dgmp.sigefbackend.metier.model.dtos.ActionActivteDTO;
import ci.dgmp.sigefbackend.metier.repositories.ActionActivteRepo;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ExistingActId.FieldValidator.class, ExistingActId.TypeValidator.class})
@Documented
public @interface ExistingActId {
    String message() default "L'action/activité avec l'identifiant '{validatedValue}' n'existe pas";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    boolean allowNull() default false;

    @Component
    class FieldValidator implements ConstraintValidator<ExistingActId, Long> {
        @Autowired
        private ActionActivteRepo repo;
        private boolean allowNull;

        @Override
        public void initialize(ExistingActId constraintAnnotation) {
            this.allowNull = constraintAnnotation.allowNull();
        }

        @Override
        public boolean isValid(Long id, ConstraintValidatorContext context) {
            if (id == null) return allowNull;
            return repo.existsById(id);
        }
    }

    @Component
    class TypeValidator implements ConstraintValidator<ExistingActId, ActionActivteDTO> {
        @Autowired
        private ActionActivteRepo repo;
        private boolean allowNull;

        @Override
        public void initialize(ExistingActId constraintAnnotation) {
            this.allowNull = constraintAnnotation.allowNull();
        }

        @Override
        public boolean isValid(ActionActivteDTO dto, ConstraintValidatorContext context) {
            if (dto == null) return true;
            Long id = dto.getActId();
            if (id == null) return allowNull;
            return repo.existsById(id);
        }
    }
}
