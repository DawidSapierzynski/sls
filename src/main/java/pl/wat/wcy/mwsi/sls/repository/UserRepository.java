package pl.wat.wcy.mwsi.sls.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wat.wcy.mwsi.sls.models.OsobaEntity;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<OsobaEntity, Long> {
    List<OsobaEntity> findAll();
    List<OsobaEntity> findByRola(String role);
    OsobaEntity findByIdOsoba(Long id);
    OsobaEntity findByLogin(String login);
}
