package ci.dgmp.sigefbackend.metier.model.views;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

public interface VDetailsPlanFormationRepo extends JpaRepository<VDetailsPlanFormation, Long>
{
    @Query("""
    select vdpf 
    from VDetailsPlanFormation vdpf 
    where vdpf.gesCode = coalesce(:gesCode, vdpf.gesCode) 
        AND locate(coalesce(:key, ''), vdpf.searchText) >0
        
        or exists (select vdpf1 
    from VDetailsPlanFormation vdpf1 where locate(vdpf.numOrdreCode, vdpf1.numOrdreCode) >0 and(vdpf1.gesCode = coalesce(:gesCode, vdpf1.gesCode) 
        AND (locate(coalesce(:key, ''), vdpf1.searchText) >0)) )
""")
    Page<VDetailsPlanFormation> search(@Param("key") String key, @Param("gesCode") Long gesCode, Pageable pageable);
}