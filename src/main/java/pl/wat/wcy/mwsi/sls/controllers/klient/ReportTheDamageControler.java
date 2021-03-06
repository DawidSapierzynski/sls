package pl.wat.wcy.mwsi.sls.controllers.klient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.wat.wcy.mwsi.sls.models.OsobaEntity;
import pl.wat.wcy.mwsi.sls.models.PolisaEntity;
import pl.wat.wcy.mwsi.sls.models.SzkodaEntity;
import pl.wat.wcy.mwsi.sls.models.myEnum.ConditionDamage;
import pl.wat.wcy.mwsi.sls.models.DokumentEntity;
import pl.wat.wcy.mwsi.sls.models.myEnum.TypeDocument;
import pl.wat.wcy.mwsi.sls.service.DamageService;
import pl.wat.wcy.mwsi.sls.service.DocumentService;
import pl.wat.wcy.mwsi.sls.service.PolicyService;
import pl.wat.wcy.mwsi.sls.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

@Scope(SCOPE_SESSION)
@Controller
public class ReportTheDamageControler {

    @Autowired
    private PolicyService policyService;

    @Autowired
    private DamageService damageService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/reportTheDamage", method = RequestMethod.GET)
    public String getReportTheDamage(HttpServletRequest request, Model model) {
        OsobaEntity osoba = userService.getLoggedUser();
        List<PolisaEntity> list = policyService.getActivePolicy(osoba);
        model.addAttribute("list", list);

        return "reportTheDamage";
    }

    @RequestMapping(value = "/reportTheDamage", params = {"typeDamage", "descriptionDamage", "numberOfPolicy"}, method = RequestMethod.POST)
    public String getReportTheDamage(HttpServletRequest request, Model model, @RequestParam(value = "typeDamage") String typeDamage, @RequestParam(value = "descriptionDamage") String descriptionDamage, @RequestParam(value = "numberOfPolicy") String numberOfPolicy) {
        OsobaEntity osoba = userService.getLoggedUser();

        if (typeDamage.equals("") || descriptionDamage.equals("")) {
            model.addAttribute("error", "Wypelnij pola");
        } else {
            PolisaEntity polisaEntity = policyService.getPolicyByNumber(numberOfPolicy);

            SzkodaEntity szkodaEntity = new SzkodaEntity();
            szkodaEntity.setPolisa(polisaEntity);
            szkodaEntity.setStan(ConditionDamage.ZGLOSZONO.getCondition());
            szkodaEntity.setPoszkodowany(osoba);
            szkodaEntity.setDataOtworzenia(new Timestamp(System.currentTimeMillis()));
            szkodaEntity.setTypUszkodzenia(typeDamage);
            szkodaEntity.setCzyAnulowano((byte) 0);
            szkodaEntity.setCzyZamknieto((byte) 0);
            damageService.save(szkodaEntity);

            DokumentEntity dokumentEntity = new DokumentEntity();
            String contentsOfTheDocument = TypeDocument.ZGLOSZENIESZKODY.getType().toUpperCase() +
                    "\nNr polisy: " +
                    szkodaEntity.getPolisa().getNumer() +
                    "\nOpis szkody:\n" +
                    descriptionDamage;
            dokumentEntity.setOsoba(osoba);
            dokumentEntity.setDataUtworzenia(new Timestamp(System.currentTimeMillis()));
            dokumentEntity.setTyp(TypeDocument.ZGLOSZENIESZKODY.getType());
            dokumentEntity.setZawartosc(contentsOfTheDocument);
            dokumentEntity.setSzkoda(szkodaEntity);
            documentService.addDocument(dokumentEntity);

            model.addAttribute("success", "Zgloszono");
        }
        List<PolisaEntity> list = policyService.getActivePolicy(osoba);
        model.addAttribute("list", list);
        return "reportTheDamage";
    }

}
