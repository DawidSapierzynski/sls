package pl.wat.wcy.mwsi.sls.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.wcy.mwsi.sls.repository.PolicyRepository;
import pl.wat.wcy.mwsi.sls.models.OsobaEntity;
import pl.wat.wcy.mwsi.sls.models.PolisaEntity;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PolicyService {
    @Autowired
    private PolicyRepository policyRepository;

    public List<PolisaEntity> getActivePolicy(OsobaEntity osoba) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        return policyRepository.findAllActivePolisy(osoba, currentTime);
    }

    public PolisaEntity getPolicyByNumber(String numberPolicy) {
        return policyRepository.findByNumer(numberPolicy);

    }

    public List<PolisaEntity> getPolicyByPerson(OsobaEntity osobaEntity) {
        return policyRepository.findByOsoba(osobaEntity);
    }
}
