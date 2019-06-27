package pl.wat.wcy.mwsi.sls.controllers.lMer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.wat.wcy.mwsi.sls.models.*;
import pl.wat.wcy.mwsi.sls.models.myEnum.ConditionDamage;
import pl.wat.wcy.mwsi.sls.models.DokumentEntity;
import pl.wat.wcy.mwsi.sls.models.myEnum.TypeDocument;
import pl.wat.wcy.mwsi.sls.service.DamageService;
import pl.wat.wcy.mwsi.sls.service.DocumentService;
import pl.wat.wcy.mwsi.sls.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class ApprovalOfTheValuationControler {

    @Autowired
    private DamageService damageService;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/approvalOfTheValuation", method = RequestMethod.GET)
    public String getReportTheDamage(HttpServletRequest request, Model model) {

        OsobaEntity osoba = userService.getByLogin(getLogin());
        List<SzkodaEntity> listDamage = damageService.getActiveDamagesByLSubstantive(osoba);
        model.addAttribute("listDamage", listDamage);

        return "approvalOfTheValuation";

    }

    @RequestMapping(value = "/approvalOfTheValuation", params = {"info"}, method = RequestMethod.GET)
    public String getReportTheDamage(HttpServletRequest request, Model model, @RequestParam(value = "info", required = true) String info) {
        OsobaEntity osoba = userService.getByLogin(getLogin());
        List<SzkodaEntity> listDamage = damageService.getActiveDamagesByLSubstantive(osoba);
        model.addAttribute("listDamage", listDamage);
        model.addAttribute("info", info);

        return "approvalOfTheValuation";
    }

    @RequestMapping(value = "/approvalOfTheValuation", params = {"option", "numberOfDamage", "substantiation"}, method = RequestMethod.POST)
    public String getReportTheDamage(HttpServletRequest request, Model model, @RequestParam(value = "option", required = true) String option, @RequestParam(value = "substantiation", required = true) String substantiation, @RequestParam(value = "numberOfDamage", required = true) long numberOfDamage) {
        OsobaEntity osoba = userService.getByLogin(getLogin());
        SzkodaEntity szkodaEntity = damageService.getDamagesById(numberOfDamage);

        if (szkodaEntity != null) {
            switch (option) {
                case "Podgląd":
                    List<SzkodaEntity> listDamage = damageService.getActiveDamagesByLSubstantive(osoba);
                    model.addAttribute("listDamage", listDamage);
                    List<DokumentEntity> listDocument = documentService.getDocumentByDamage(szkodaEntity);
                    model.addAttribute("listDocument", listDocument);
                    model.addAttribute("selectDamage", szkodaEntity);

                    break;
                case "Akceptuj wycenę": {
                    szkodaEntity.setStan(ConditionDamage.ZAKONCZONO.getCondition());
                    szkodaEntity.setCzyZamknieto((byte) 1);
                    szkodaEntity.setDataZakonczeniaLikwidacji(new Timestamp(System.currentTimeMillis()));

                    String contentsOfTheDocument = TypeDocument.AKCEPTACJAWYCENY.getType().toUpperCase() +
                            "\nNr polisy: " +
                            szkodaEntity.getPolisa().getNumer() +
                            "\nNr szkody: " +
                            szkodaEntity.getIdSzkoda() +
                            "\nUzasadnienie:\n" +
                            substantiation;
                    DokumentEntity dokumentEntity = generateDocument(osoba, szkodaEntity, contentsOfTheDocument);
                    dokumentEntity.setTyp(TypeDocument.AKCEPTACJAWYCENY.getType());
                    documentService.save(dokumentEntity);

                    return "redirect:/approvalOfTheValuation?info=Akceptowano";

                }
                case "Anuluj wycenę": {
                    szkodaEntity.setStan(ConditionDamage.ANULOWANO.getCondition());
                    szkodaEntity.setCzyAnulowano((byte) 1);

                    String contentsOfTheDocument = TypeDocument.ANULOWANIEWYCENY.getType().toUpperCase() +
                            "\nNr polisy: " +
                            szkodaEntity.getPolisa().getNumer() +
                            "\nNr szkody: " +
                            szkodaEntity.getIdSzkoda() +
                            "\nUzasadnienie:\n" +
                            substantiation;
                    DokumentEntity dokumentEntity = generateDocument(osoba, szkodaEntity, contentsOfTheDocument);
                    dokumentEntity.setTyp(TypeDocument.ANULOWANIEWYCENY.getType());
                    documentService.save(dokumentEntity);

                    return "redirect:/approvalOfTheValuation?info=Anulowano";
                }
            }

            return "approvalOfTheValuation";
        } else {
            return "redirect:/approvalOfTheValuation?info=Brak+szkody";
        }
    }

    private DokumentEntity generateDocument(OsobaEntity osoba, SzkodaEntity szkodaEntity, String substantiation) {
        damageService.save(szkodaEntity);

        DokumentEntity dokumentEntity = new DokumentEntity();
        dokumentEntity.setSzkoda(szkodaEntity);
        dokumentEntity.setOsoba(osoba);
        dokumentEntity.setDataUtworzenia(new Timestamp(System.currentTimeMillis()));
        dokumentEntity.setZawartosc(substantiation);

        return dokumentEntity;
    }

    private String getLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
