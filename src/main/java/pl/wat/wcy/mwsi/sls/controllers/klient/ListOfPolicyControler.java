package pl.wat.wcy.mwsi.sls.controllers.klient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.wat.wcy.mwsi.sls.service.PolicyService;
import pl.wat.wcy.mwsi.sls.service.UserService;
import pl.wat.wcy.mwsi.sls.models.OsobaEntity;
import pl.wat.wcy.mwsi.sls.models.PolisaEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ListOfPolicyControler {
    @Autowired
    private PolicyService policyService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/listOfPolicy", method = RequestMethod.GET)
    public String getListOfPolicy(HttpServletRequest request, Model model) {

        OsobaEntity osoba = userService.getByLogin(getLogin());
        List<PolisaEntity> list = policyService.getPolicyByPerson(osoba);
        model.addAttribute("list", list);

        return "listOfPolicy";
    }

    private String getLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
