package pl.wat.wcy.mwsi.sls.controllers.lTech;

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
public class ListOfDamagesTechControler {
    @Autowired
    private DamageService damageService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/listOfDamagesTech", method = RequestMethod.GET)
    public String getListOfDamages(HttpServletRequest request, Model model) {
        OsobaEntity osoba = userService.getLoggedUser();
        List<SzkodaEntity> list = damageService.getDamagesByLTechnical(osoba);
        model.addAttribute("list", list);

        return "listOfDamagesTech";

    }
}
