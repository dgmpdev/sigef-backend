package ci.dgmp.sigefbackend.metier.repositories;

import ci.dgmp.sigefbackend.metier.model.dtos.GestionDTO;
import ci.dgmp.sigefbackend.metier.model.entities.Gestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GestionRepo extends JpaRepository<Gestion, Long> {

    @Query("""
        select new ci.dgmp.sigefbackend.metier.model.dtos.GestionDTO(g.gesCode, g.gesCourant, g.gesLibelle)
        from Gestion g
        where (
            locate(upper(coalesce(:key, '')), upper(cast(function('unaccent', coalesce(g.gesLibelle, '')) as string))) > 0
            or locate(upper(coalesce(:key, '')), upper(cast(function('unaccent', coalesce(concat(g.gesCode, ''), '')) as string))) > 0
        )
        """)
    Page<GestionDTO> search(@Param("key") String key, Pageable pageable);

    @Modifying
    @Query("update Gestion g set g.gesCourant = false where g.gesCourant = true")
    int resetAllCourant();

    @Modifying
    @Query("update Gestion g set g.gesCourant = true where g.gesCode = :gesCode")
    int setCourant(@Param("gesCode") Long gesCode);

    @Query("select count(g)>0 from Gestion g where upper(g.gesLibelle) = upper(:libelle)")
    boolean existsByLibelle(@Param("libelle") String libelle);

    @Query("select count(g)>0 from Gestion g where upper(g.gesLibelle) = upper(:libelle) and g.gesCode <> :gesCode")
    boolean existsByLibelleAndNotCode(@Param("libelle") String libelle, @Param("gesCode") Long gesCode);
}
