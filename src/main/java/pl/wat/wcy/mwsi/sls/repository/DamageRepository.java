package pl.wat.wcy.mwsi.sls.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.wat.wcy.mwsi.sls.models.OsobaEntity;
import pl.wat.wcy.mwsi.sls.models.SzkodaEntity;

import java.util.List;

@Repository
public interface DamageRepository extends CrudRepository<SzkodaEntity, Long> {
    SzkodaEntity findByIdSzkoda(Long id);
    List<SzkodaEntity> findByPoszkodowanyOrSprawca(OsobaEntity poszkodowany,OsobaEntity sprawca);
    @Query("SELECT s FROM SzkodaEntity s WHERE s.likwidatorTechniczny=:lTech AND (s.stan=:stan1 OR s.stan=:stan2)")
    List<SzkodaEntity> findAllDamageForTech(@Param("lTech")OsobaEntity lTechniczny, @Param("stan1")String stan1, @Param("stan2")String stan2);
    List<SzkodaEntity> findByLikwidatorMerytorycznyAndStan(OsobaEntity lTechniczny, String stan);
    List<SzkodaEntity> findByLikwidatorTechniczny(OsobaEntity lTechniczny);
    List<SzkodaEntity> findByLikwidatorMerytoryczny(OsobaEntity lMerytoryczny);
    List<SzkodaEntity> findByLikwidatorMerytorycznyIsNullAndLikwidatorTechnicznyIsNull();
}
