package ci.dgmp.sigefbackend.metier.repositories;

import ci.dgmp.sigefbackend.metier.model.entities.ActionActivte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActionActivteRepo extends JpaRepository<ActionActivte, Long>
{
    @Query("SELECT a FROM VAction a WHERE a.gesCode = :gesCode ORDER BY a.actNumOrdre")
    List<ActionActivte> findActionsByGesCode(@Param("gesCode") Long gesCode);

    @Query("SELECT a FROM VActivite a WHERE a.actionParentId = :actionId ORDER BY a.actNumOrdre")
    List<ActionActivte> findActivitesByActionId(@Param("actionId") Long actionId);

    @Query("SELECT a FROM VAction a WHERE a.gesCode = :gesCode AND a.actId <>:currentActionId ORDER BY a.actNumOrdre")
    List<ActionActivte> findActionsByGesCodeAndNotActionId(@Param("gesCode") Long gesCode, @Param("currentActionId")Long currentActionId);

    @Query("SELECT a FROM VActivite a WHERE a.actionParentId = :actionId and a.actId <> :currentActiviteId ORDER BY a.actNumOrdre")
    List<ActionActivte> findActivitesByActionIdAndNotActiviteId(@Param("actionId") Long actionId, @Param("currentActiviteId")Long currentActiviteId);


    @Query("SELECT COUNT(a)>0 FROM VAction a WHERE a.gesCode = :gesCode AND a.actNumOrdre = :order")
    boolean actionExistsWithSameOrder(@Param("gesCode")Long gesCode, @Param("order")int order);

    @Query("SELECT COUNT(a)>0 FROM VAction a WHERE a.actId <> :currentActionId AND a.gesCode = :gesCode AND a.actNumOrdre = :order")
    boolean actionExistsWithSameOrderAndNotActionId(@Param("gesCode")Long gesCode, @Param("order")int order, @Param("currentActionId")Long currentActionId);

    @Query("SELECT COUNT(a)>0 FROM VActivite a WHERE a.actionParentId = :actionId AND a.actNumOrdre = :order")
    boolean activiteExistsWithSameOrder(@Param("actionId")Long actionId, @Param("order")int order);

    @Query("SELECT COUNT(a)>0 FROM VActivite a WHERE a.actId <> :currentActiviteId AND a.actionParentId = :actionId AND a.actNumOrdre = :order")
    boolean activiteExistsWithSameOrderAndNotActiviteId(@Param("actionId")Long actionId, @Param("order")int order, @Param("currentActiviteId")Long currentActiviteId);

    @Query("SELECT coalesce(max(a.actNumOrdre), 0) + 1 FROM VAction a WHERE a.gesCode = :gesCode")
    int getNextActionNumOrdre(Long gesCode);

    @Query("SELECT coalesce(max(a.actNumOrdre), 0) + 1 FROM VActivite a WHERE a.actionParentId = :actionId")
    int getNextActiviteNumOrdre(Long actionId);
}