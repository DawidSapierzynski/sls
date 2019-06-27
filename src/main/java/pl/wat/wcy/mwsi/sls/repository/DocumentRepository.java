package pl.wat.wcy.mwsi.sls.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wat.wcy.mwsi.sls.models.DokumentEntity;
import pl.wat.wcy.mwsi.sls.models.SzkodaEntity;

import java.util.List;


@Repository
public interface DocumentRepository extends CrudRepository<DokumentEntity, Long> {
    List<DokumentEntity> findBySzkoda(SzkodaEntity szkodaEntity);
}
