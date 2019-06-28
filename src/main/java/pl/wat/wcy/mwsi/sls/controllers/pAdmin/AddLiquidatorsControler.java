package pl.wat.wcy.mwsi.sls.controllers.pAdmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    private UserService userService;


    @RequestMapping(value = "/addLiquidators", method = RequestMethod.GET)
    public String getReportTheDamage(HttpSession session, Model model) {

        getDamageAndLiquidators(model);
        return "addLiquidators";

    }


    @RequestMapping(value = "/addLiquidators", params = {"option", "numberOfDamage", "lTech", "lSub"}, method = RequestMethod.POST)
    public String getReportTheDamage(HttpSession session, Model model, @RequestParam(value = "option") String option, @RequestParam(value = "lTech") long lTech, @RequestParam(value = "lSub") long lSub, @RequestParam(value = "numberOfDamage") long numberOfDamage) {
        SzkodaEntity szkodaEntity = damageService.getDamagesById(numberOfDamage);

        if (szkodaEntity != null) {
            if (option.equals("Podglad")) {
                model.addAttribute("selectDamage", szkodaEntity);
            } else if (option.equals("Przypisz")) {
                szkodaEntity.setDataRozpoczeciaLikwidacji(new Timestamp(System.currentTimeMillis()));
                szkodaEntity.setLikwidatorMerytoryczny(userService.getUserById(lSub));
                szkodaEntity.setLikwidatorTechniczny(userService.getUserById(lTech));
                damageService.save(szkodaEntity);

                model.addAttribute("success","Przypisano");
            }
        } else {
            model.addAttribute("error","Brak szkody.");
        }

        getDamageAndLiquidators(model);
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
}
