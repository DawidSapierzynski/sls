package pl.wat.wcy.mwsi.sls.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.wcy.mwsi.sls.repository.DocumentRepository;
import pl.wat.wcy.mwsi.sls.models.DokumentEntity;
import pl.wat.wcy.mwsi.sls.models.SzkodaEntity;

import java.util.List;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public DokumentEntity save(DokumentEntity dokumentEntity) {
        return documentRepository.save(dokumentEntity);
    }

    public List<DokumentEntity> getDocumentByDamage(SzkodaEntity szkodaEntity) {
        return documentRepository.findBySzkoda(szkodaEntity);
    }

}
