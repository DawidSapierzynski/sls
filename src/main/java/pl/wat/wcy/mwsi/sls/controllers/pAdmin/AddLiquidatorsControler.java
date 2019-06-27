package pl.wat.wcy.mwsi.sls.controllers.pAdmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.wat.wcy.mwsi.sls.models.DokumentEntity;
import pl.wat.wcy.mwsi.sls.service.DamageService;
import pl.wat.wcy.mwsi.sls.service.DocumentService;
import pl.wat.wcy.mwsi.sls.service.UserService;
import pl.wat.wcy.mwsi.sls.models.OsobaEntity;
import pl.wat.wcy.mwsi.sls.models.SzkodaEntity;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class AddLiquidatorsControler {

    @Autowired
    private DamageService damageService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/addLiquidators", method = RequestMethod.GET)
    public String getReportTheDamage(HttpSession session, Model model) {

        getDamageAndLiquidators(model);
        return "addLiquidators";

    }


    @RequestMapping(value = "/addLiquidators", params = {"info"}, method = RequestMethod.GET)
    public String getReportTheDamage(HttpSession session, Model model, @RequestParam(value = "info", required = true) String info) {

        getDamageAndLiquidators(model);
        model.addAttribute("info", info);

        return "addLiquidators";
    }

    private void getDamageAndLiquidators(Model model) {
        List<SzkodaEntity> listDamage = damageService.getDamagesByNullLiquidators();
        model.addAttribute("listDamage", listDamage);
        List<OsobaEntity> listTech = userService.getLTech();
        model.addAttribute("listTech", listTech);
        List<OsobaEntity> listSub = userService.getLSub();
        model.addAttribute("listSub", listSub);
    }

    @RequestMapping(value = "/addLiquidators", params = {"option", "numberOfDamage", "lTech", "lSub"}, method = RequestMethod.POST)
    public String getReportTheDamage(HttpSession session, Model model, @RequestParam(value = "option", required = true) String option, @RequestParam(value = "lTech", required = true) long lTech, @RequestParam(value = "lSub", required = true) long lSub, @RequestParam(value = "numberOfDamage", required = true) long numberOfDamage) {

        SzkodaEntity szkodaEntity = damageService.getDamagesById(numberOfDamage);
        if (szkodaEntity != null) {
            if (option.equals("Podglad")) {
                getDamageAndLiquidators(model);
                model.addAttribute("selectDamage", szkodaEntity);
                List<DokumentEntity> listDocument = documentService.getDocumentByDamage(szkodaEntity);
                model.addAttribute("listDocument", listDocument);
            } else if (option.equals("Przypisz")) {
                szkodaEntity.setDataRozpoczeciaLikwidacji(new Timestamp(System.currentTimeMillis()));
                szkodaEntity.setLikwidatorMerytoryczny(userService.getUserById(lSub));
                szkodaEntity.setLikwidatorTechniczny(userService.getUserById(lTech));

                damageService.save(szkodaEntity);

                return "redirect:/addLiquidators?info=Przypisano";
            }
            return "addLiquidators";
        } else {
            return "redirect:/addLiquidators?info=Brak+szkody";
        }
    }
}
