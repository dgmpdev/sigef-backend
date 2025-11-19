package ci.dgmp.sigefbackend.metier.repositories;

import ci.dgmp.sigefbackend.metier.model.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepo extends JpaRepository<Evaluation, Long> {
}
