package pl.wat.wcy.mwsi.sls;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class BcryptGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String password = "Tadeusz1";

        String hashedPassword = passwordEncoder.encode(password);

        System.out.println(hashedPassword);
        passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.matches(password, hashedPassword));

    }
}