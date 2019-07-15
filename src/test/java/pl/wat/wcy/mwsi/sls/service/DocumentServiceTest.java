package pl.wat.wcy.mwsi.sls.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.wat.wcy.mwsi.sls.models.DokumentEntity;
import pl.wat.wcy.mwsi.sls.models.OsobaEntity;
import pl.wat.wcy.mwsi.sls.models.SzkodaEntity;

import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentServiceTest {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private UserService userService;
    @Autowired
    private DamageService damageService;

    @Test
    public void addDocument(){
        //given
        OsobaEntity osobaEntity = userService.getUserById(1);
        SzkodaEntity szkodaEntity = damageService.getDamagesById(1);

        DokumentEntity dokumentEntity = new DokumentEntity();
        dokumentEntity.setTyp("test");
        dokumentEntity.setDataUtworzenia(new Timestamp(System.currentTimeMillis()));
        dokumentEntity.setZawartosc("Test");
        dokumentEntity.setOsoba(osobaEntity);
        dokumentEntity.setSzkoda(szkodaEntity);

        //when
        documentService.addDocument(dokumentEntity);

        //then
        Assert.assertNotNull(documentService.getDocumentByDamage(szkodaEntity));

        //delete
        documentService.deleteDocument(dokumentEntity);
    }

    @Test
    public void deleteDocument(){
        //given
        OsobaEntity osobaEntity = userService.getUserById(1);
        SzkodaEntity szkodaEntity = damageService.getDamagesById(1);

        DokumentEntity dokumentEntity = new DokumentEntity();
        dokumentEntity.setTyp("test");
        dokumentEntity.setDataUtworzenia(new Timestamp(System.currentTimeMillis()));
        dokumentEntity.setZawartosc("Test");
        dokumentEntity.setOsoba(osobaEntity);
        dokumentEntity.setSzkoda(szkodaEntity);
        documentService.addDocument(dokumentEntity);

        //when
        documentService.deleteDocument(dokumentEntity);

        //then
        Assert.assertNull(documentService.getDocumentById(dokumentEntity.getIdDokument()));
    }

    @Test
    public void getDocumentByDamage(){
        //given
        List<DokumentEntity> dokumentEntityList;
        SzkodaEntity szkodaEntity = damageService.getDamagesById(1);

        //when
        dokumentEntityList = documentService.getDocumentByDamage(szkodaEntity);

        //then
        for(DokumentEntity d: dokumentEntityList){
            Assert.assertEquals(d.getSzkoda(),szkodaEntity);
        }
    }

}
