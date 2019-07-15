package pl.wat.wcy.mwsi.sls.controllers.lMer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.wat.wcy.mwsi.sls.service.DamageService;
import pl.wat.wcy.mwsi.sls.service.UserService;
import pl.wat.wcy.mwsi.sls.models.OsobaEntity;
import pl.wat.wcy.mwsi.sls.models.SzkodaEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class ListOfDamagesSubControler {
    @Autowired
    private DamageService damageService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/listOfDamagesSub", method = RequestMethod.GET)
    public String getListOfDamages(HttpServletRequest request, Model model) {
        OsobaEntity osoba = userService.getLoggedUser();
        List<SzkodaEntity> list = damageService.getDamagesByLSubstantive(osoba);
        model.addAttribute("list", list);

        return "listOfDamagesSub";
    }
}
