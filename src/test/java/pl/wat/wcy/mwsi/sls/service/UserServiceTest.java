package pl.wat.wcy.mwsi.sls.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.wat.wcy.mwsi.sls.models.OsobaEntity;
import pl.wat.wcy.mwsi.sls.models.myEnum.Role;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void addUser_whenIsNew() {
        //given
        OsobaEntity testowy = new OsobaEntity();
        testowy.setImie("ImieTest");
        testowy.setNazwisko("NazwiskoTest");
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        testowy.setDataUrodzenia(date);
        testowy.setPlec("M");
        testowy.setNarodowosc(616);
        testowy.setPesel("68031503036");
        testowy.setRola("KLIENT");
        testowy.setMail("testowy.testowy@sls.pl");
        testowy.setTelefon("555666222");
        testowy.setNrKonta("19249000058660725421376736");
        testowy.setStan((byte) 1);
        testowy.setLogin("testowy");
        testowy.setHaslo("$2a$10$t1y/aASms5PmsowF5Z06qeofQeXrYhTJU838cWSNPDbdLKcFP99WS");

        //when
        boolean result = userService.addUser(testowy);

        //then
        Assert.assertTrue(result);
        Assert.assertNotNull(userService.getUserById(testowy.getIdOsoba()));

        //delete
        userService.deleteUser(testowy);
    }

    @Test
    public void addUser_whenLoginIsUsed() {
        //given
        OsobaEntity osobaEntity = new OsobaEntity();
        osobaEntity.setImie("ImieTest");
        osobaEntity.setNazwisko("NazwiskoTest");
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        osobaEntity.setDataUrodzenia(date);
        osobaEntity.setPlec("M");
        osobaEntity.setNarodowosc(616);
        osobaEntity.setPesel("68031503036");
        osobaEntity.setRola("KLIENT");
        osobaEntity.setMail("testowy.testowy@sls.pl");
        osobaEntity.setTelefon("555666222");
        osobaEntity.setNrKonta("19249000058660725421376736");
        osobaEntity.setStan((byte) 1);
        osobaEntity.setLogin("Adam1");
        osobaEntity.setHaslo("$2a$10$t1y/aASms5PmsowF5Z06qeofQeXrYhTJU838cWSNPDbdLKcFP99WS");

        //when
        boolean result = userService.addUser(osobaEntity);

        //then
        Assert.assertFalse(result);
        Assert.assertNull(userService.getUserById(osobaEntity.getIdOsoba()));
    }

    @Test
    public void deleteUser(){
        //given
        String login = "klsfjgsdg";
        OsobaEntity osobaEntity = new OsobaEntity();
        osobaEntity.setImie("ImieTest");
        osobaEntity.setNazwisko("NazwiskoTest");
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        osobaEntity.setDataUrodzenia(date);
        osobaEntity.setPlec("M");
        osobaEntity.setNarodowosc(616);
        osobaEntity.setPesel("68031503036");
        osobaEntity.setRola("KLIENT");
        osobaEntity.setMail("testowy.testowy@sls.pl");
        osobaEntity.setTelefon("555666222");
        osobaEntity.setNrKonta("19249000058660725421376736");
        osobaEntity.setStan((byte) 1);
        osobaEntity.setLogin(login);
        osobaEntity.setHaslo("$2a$10$t1y/aASms5PmsowF5Z06qeofQeXrYhTJU838cWSNPDbdLKcFP99WS");
        userService.addUser(osobaEntity);

        //when
        userService.deleteUser(osobaEntity);

        //then
        Assert.assertNull(userService.getUserByLogin(login));
    }

    @Test
    public void getLTechTest(){
        //given
        List<OsobaEntity> osobaEntityList;

        //when
        osobaEntityList = userService.getLTech();

        //then
        for(OsobaEntity o: osobaEntityList){
            Assert.assertEquals(o.getRola(), Role.LTECHNICAL.getRole());
        }
    }

    @Test
    public void getLSubTest(){
        //given
        List<OsobaEntity> osobaEntityList;

        //when
        osobaEntityList = userService.getLSub();

        //then
        for(OsobaEntity o: osobaEntityList){
            Assert.assertEquals(o.getRola(), Role.LSUBSTANTIVE.getRole());
        }
    }

    @Test
    public void getUserById(){
        //given
        String login = "oaiHOHhlolJfj";
        OsobaEntity osobaEntity = new OsobaEntity();
        osobaEntity.setImie("ImieTest");
        osobaEntity.setNazwisko("NazwiskoTest");
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        osobaEntity.setDataUrodzenia(date);
        osobaEntity.setPlec("M");
        osobaEntity.setNarodowosc(616);
        osobaEntity.setPesel("68031503036");
        osobaEntity.setRola("KLIENT");
        osobaEntity.setMail("testowy.testowy@sls.pl");
        osobaEntity.setTelefon("555666222");
        osobaEntity.setNrKonta("19249000058660725421376736");
        osobaEntity.setStan((byte) 1);
        osobaEntity.setLogin(login);
        osobaEntity.setHaslo("$2a$10$t1y/aASms5PmsowF5Z06qeofQeXrYhTJU838cWSNPDbdLKcFP99WS");
        userService.addUser(osobaEntity);

        //when
        OsobaEntity result = userService.getUserById(osobaEntity.getIdOsoba());

        //then
        Assert.assertEquals(result.getIdOsoba(), osobaEntity.getIdOsoba());

        //delete
        userService.deleteUser(osobaEntity);
    }

    @Test
    public void getUserByLogin(){
        //given
        String login = "HHhldshglsdZF";
        OsobaEntity osobaEntity = new OsobaEntity();
        osobaEntity.setImie("ImieTest");
        osobaEntity.setNazwisko("NazwiskoTest");
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        osobaEntity.setDataUrodzenia(date);
        osobaEntity.setPlec("M");
        osobaEntity.setNarodowosc(616);
        osobaEntity.setPesel("68031503036");
        osobaEntity.setRola("KLIENT");
        osobaEntity.setMail("testowy.testowy@sls.pl");
        osobaEntity.setTelefon("555666222");
        osobaEntity.setNrKonta("19249000058660725421376736");
        osobaEntity.setStan((byte) 1);
        osobaEntity.setLogin(login);
        osobaEntity.setHaslo("$2a$10$t1y/aASms5PmsowF5Z06qeofQeXrYhTJU838cWSNPDbdLKcFP99WS");
        userService.addUser(osobaEntity);

        //when
        OsobaEntity result = userService.getUserByLogin(osobaEntity.getLogin());

        //then
        Assert.assertEquals(result.getLogin(), osobaEntity.getLogin());

        //delete
        userService.deleteUser(osobaEntity);
    }

}
