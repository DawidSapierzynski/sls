package pl.wat.wcy.mwsi.sls.controllers.lTech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.wat.wcy.mwsi.sls.models.myEnum.ConditionDamage;
import pl.wat.wcy.mwsi.sls.models.DokumentEntity;
import pl.wat.wcy.mwsi.sls.models.myEnum.TypeDocument;
import pl.wat.wcy.mwsi.sls.service.DamageService;
import pl.wat.wcy.mwsi.sls.service.DocumentService;
import pl.wat.wcy.mwsi.sls.service.UserService;
import pl.wat.wcy.mwsi.sls.models.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class AddValuationControler {

    @Autowired
    private DamageService damageService;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addValuation", method = RequestMethod.GET)
    public String getReportTheDamage(HttpSession session, Model model) {
        OsobaEntity osoba = userService.getByLogin(getLogin());
        List<SzkodaEntity> list = damageService.getActiveDamagesByLTechnical(osoba);
        model.addAttribute("list", list);

        return "addValuation";
    }

    @RequestMapping(value = "/addValuation", params = {"info"}, method = RequestMethod.GET)
    public String getReportTheDamage(HttpSession session, Model model, @RequestParam(value = "info", required = true) String info) {
        OsobaEntity osoba = userService.getByLogin(getLogin());
        List<SzkodaEntity> list = damageService.getActiveDamagesByLTechnical(osoba);
        model.addAttribute("list", list);
        model.addAttribute("info", info);

        return "addValuation";
    }

    @RequestMapping(value = "/addValuation", params = {"amountValuation", "justificationValuation", "numberOfDamage"}, method = RequestMethod.POST)
    public String getReportTheDamage(HttpSession session, Model model, @RequestParam(value = "amountValuation", required = true) double amountValuation, @RequestParam(value = "justificationValuation", required = true) String justificationValuation, @RequestParam(value = "numberOfDamage", required = true) long numberOfDamage) {

        OsobaEntity osoba = userService.getByLogin(getLogin());

        SzkodaEntity szkodaEntity = damageService.getDamagesById(numberOfDamage);

        if (szkodaEntity != null) {
            szkodaEntity.setStan(ConditionDamage.WYCENIONO.getCondition());
            szkodaEntity.setWycena(new BigDecimal(amountValuation));
            szkodaEntity.setCzyAnulowano((byte) 0);
            damageService.save(szkodaEntity);

            String contentsOfTheDocument = TypeDocument.WYCENA.getType().toUpperCase() +
                    "\nNr polisy: " +
                    szkodaEntity.getPolisa().getNumer() +
                    "\nNr szkody: " +
                    szkodaEntity.getIdSzkoda() +
                    "\nKwota: " +
                    amountValuation +
                    "\nUzasadnienie:\n" +
                    justificationValuation;

            DokumentEntity dokumentEntity = new DokumentEntity();
            dokumentEntity.setOsoba(osoba);
            dokumentEntity.setSzkoda(szkodaEntity);
            dokumentEntity.setTyp(TypeDocument.WYCENA.getType());
            dokumentEntity.setZawartosc(contentsOfTheDocument);
            dokumentEntity.setDataUtworzenia(new Timestamp(System.currentTimeMillis()));
            documentService.save(dokumentEntity);


            return "redirect:/addValuation?info=Dodano+wycene";
        } else {
            return "redirect:/addValuation?info=Brak+szkody";
        }

    }

    private String getLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
