package pl.wat.wcy.mwsi.sls.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.wcy.mwsi.sls.repository.UserRepository;
import pl.wat.wcy.mwsi.sls.models.OsobaEntity;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<OsobaEntity> getUsers() {
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

    public List<OsobaEntity> getLTech() {
        return userRepository.findByRola("LTECH");
    }

    public List<OsobaEntity> getLSub() {
        return userRepository.findByRola("LMER");
    }

    public OsobaEntity getUserById(long id) {
        return userRepository.findByIdOsoba(id);
    }

    public OsobaEntity getByLogin(String login){
        return userRepository.findByLogin(login);
    }
}