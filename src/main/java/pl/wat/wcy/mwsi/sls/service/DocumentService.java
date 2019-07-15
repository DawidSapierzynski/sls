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

    public DokumentEntity addDocument(DokumentEntity dokumentEntity) {
        return documentRepository.save(dokumentEntity);
    }

    public List<DokumentEntity> getDocumentByDamage(SzkodaEntity szkodaEntity) {
        return documentRepository.findBySzkoda(szkodaEntity);
    }

    public void deleteDocument(DokumentEntity dokumentEntity){
        documentRepository.delete(dokumentEntity);
    }

    public DokumentEntity getDocumentById(Long id){
        return documentRepository.findByIdDokument(id);
    }
}
