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

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueGesLibelle.FieldValidator.class, UniqueGesLibelle.TypeValidator.class})
@Documented
public @interface UniqueGesLibelle
{
    String message() default "Le libellé de la gestion '{validatedValue}' existe déjà";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Component
    class FieldValidator implements ConstraintValidator<UniqueGesLibelle, String>
    {
        @Autowired
        private GestionRepo gestionRepo;

        @Override
        public boolean isValid(String libelle, ConstraintValidatorContext context)
        {
            if (StringUtils.isBlank(libelle)) return true;
            return !gestionRepo.existsByLibelle(libelle);
        }
    }

    @Component
    class TypeValidator implements ConstraintValidator<UniqueGesLibelle, GestionDTO>
    {
        @Autowired
        private GestionRepo gestionRepo;

        @Override
        public boolean isValid(GestionDTO dto, ConstraintValidatorContext context)
        {
            if (dto == null || StringUtils.isBlank(dto.getGesLibelle())) return true;
            String libelle = dto.getGesLibelle();
            Long gesCode = dto.getGesCode();
            boolean exists = (gesCode == null)
                    ? gestionRepo.existsByLibelle(libelle)
                    : gestionRepo.existsByLibelleAndNotCode(libelle, gesCode);
            return !exists;
        }
    }
}