package pl.wat.wcy.mwsi.sls.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.wat.wcy.mwsi.sls.models.OsobaEntity;
import pl.wat.wcy.mwsi.sls.models.PolisaEntity;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PolicyRepository extends CrudRepository<PolisaEntity, Long> {
    @Query("SELECT P FROM PolisaEntity P WHERE P.osoba = :osoba AND P.dataRozpoczecia <= :data AND P.dataZakonczenia >= :data")
    List<PolisaEntity> findAllActivePolisy(@Param("osoba")OsobaEntity osobaEntity, @Param("data") Timestamp currentTime);
    PolisaEntity findByNumer(String numberPolicy);
    List<PolisaEntity> findByOsoba(OsobaEntity osobaEntity);
}
