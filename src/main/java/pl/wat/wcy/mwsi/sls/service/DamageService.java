package pl.wat.wcy.mwsi.sls.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wat.wcy.mwsi.sls.models.myEnum.ConditionDamage;
import pl.wat.wcy.mwsi.sls.repository.DamageRepository;
import pl.wat.wcy.mwsi.sls.models.OsobaEntity;
import pl.wat.wcy.mwsi.sls.models.SzkodaEntity;

import java.util.List;

@Service
public class DamageService {
    @Autowired
    private DamageRepository damageRepository;

    public List<SzkodaEntity> getDamages(OsobaEntity osoba) {
        return damageRepository.findByPoszkodowanyOrSprawca(osoba, osoba);
    }

    @Transactional
    public SzkodaEntity save(SzkodaEntity szkodaEntity) {
        return damageRepository.save(szkodaEntity);
    }


    public List<SzkodaEntity> getActiveDamagesByLTechnical(OsobaEntity osoba) {
        return damageRepository.findAllDamageForTech(osoba, ConditionDamage.ANULOWANO.getCondition(),ConditionDamage.ZGLOSZONO.getCondition());
    }


    @Transactional
    public SzkodaEntity getDamagesById(long idOfDamage) {
        return damageRepository.findByIdSzkoda(idOfDamage);
    }

    public List<SzkodaEntity> getActiveDamagesByLSubstantive(OsobaEntity osoba) {
        return damageRepository.findByLikwidatorMerytorycznyAndStan(osoba, ConditionDamage.WYCENIONO.getCondition());

    }

    public List<SzkodaEntity> getDamagesByLTechnical(OsobaEntity osoba) {
        return damageRepository.findByLikwidatorTechniczny(osoba);
    }

    public List<SzkodaEntity> getDamagesByLSubstantive(OsobaEntity osoba) {
        return damageRepository.findByLikwidatorMerytoryczny(osoba);
    }


    public List<SzkodaEntity> getDamagesByNullLiquidators() {
        return damageRepository.findByLikwidatorMerytorycznyIsNullAndLikwidatorTechnicznyIsNull();

    }
}
