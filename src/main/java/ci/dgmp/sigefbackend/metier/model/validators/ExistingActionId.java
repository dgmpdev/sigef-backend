package ci.dgmp.sigefbackend.metier.model.validators;

import ci.dgmp.sigefbackend.metier.model.dtos.ActionActivteDTO;
import ci.dgmp.sigefbackend.metier.model.entities.ActionActivte;
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
@Constraint(validatedBy = {ExistingActionId.FieldValidator.class, ExistingActionId.TypeValidator.class})
@Documented
public @interface ExistingActionId {
    String message() default "L'action parente '{validatedValue}' est introuvable ou n'est pas de type ACTION";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    boolean allowNull() default false;

    @Component
    class FieldValidator implements ConstraintValidator<ExistingActionId, Long> {
        @Autowired
        private ActionActivteRepo repo;
        private boolean allowNull;

        @Override
        public void initialize(ExistingActionId constraintAnnotation) {
            this.allowNull = constraintAnnotation.allowNull();
        }

        @Override
        public boolean isValid(Long id, ConstraintValidatorContext context) {
            if (id == null) return allowNull;
            return repo.findById(id)
                    .map(ActionActivte::getActType)
                    .map(t -> t != null && t.code != null && "ACTION".equalsIgnoreCase(t.code))
                    .orElse(false);
        }
    }

    @Component
    class TypeValidator implements ConstraintValidator<ExistingActionId, ActionActivteDTO> {
        @Autowired
        private ActionActivteRepo repo;
        private boolean allowNull;

        @Override
        public void initialize(ExistingActionId constraintAnnotation) {
            this.allowNull = constraintAnnotation.allowNull();
        }

        @Override
        public boolean isValid(ActionActivteDTO dto, ConstraintValidatorContext context) {
            if (dto == null) return true;
            Long id = dto.getActParentId();
            if (id == null) return allowNull;
            return repo.findById(id)
                    .map(ActionActivte::getActType)
                    .map(t -> t != null && t.code != null && "ACTION".equalsIgnoreCase(t.code))
                    .orElse(false);
        }
    }
}
