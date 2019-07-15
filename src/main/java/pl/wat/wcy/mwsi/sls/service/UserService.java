package pl.wat.wcy.mwsi.sls.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.wat.wcy.mwsi.sls.models.myEnum.Role;
import pl.wat.wcy.mwsi.sls.repository.UserRepository;
import pl.wat.wcy.mwsi.sls.models.OsobaEntity;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private List<OsobaEntity> getUsers() {
        return userRepository.findAll();
    }

    public boolean addUser(OsobaEntity user) {
        List<OsobaEntity> list = getUsers();

        for (OsobaEntity u : list) {
            if (u.getLogin().equals(user.getLogin())) {
                return false;
            }
        }

        userRepository.save(user);
        return true;
    }

    public void deleteUser(OsobaEntity user){
        userRepository.delete(user);
    }

    public List<OsobaEntity> getLTech() {
        return userRepository.findByRola(Role.LTECHNICAL.getRole());
    }

    public List<OsobaEntity> getLSub() {
        return userRepository.findByRola(Role.LSUBSTANTIVE.getRole());
    }

    public OsobaEntity getUserById(long id) {
        return userRepository.findByIdOsoba(id);
    }

    public OsobaEntity getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public OsobaEntity getLoggedUser() {
        return getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}