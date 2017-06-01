package ENSK;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Created by Enathen on 2017-05-31.
 */
public class SaltAndHashPassword {
    private String password;
    private String salt;
    public SaltAndHashPassword(String password){
        this.password = password;
    }
    public String createSalt() {
        SecureRandom random = new SecureRandom();
        String string = "";
        for (int i = 0; i < 50; i++) {
            string += (char)(random.nextInt(58) + 65);
        }

        salt = string;
        System.out.println(salt);
        return string;
    }
    public String createHash(){
        String hash = salt + password;
        System.out.println(hash);
        return String.valueOf(hash(hash));

    }
    private static long hash(String string) {
        long h = 288230376151711717L; // prime
        int len = string.length();

        for (int i = 0; i < len; i++) {
            h = 37*h + string.charAt(i);
        }
        System.out.println(h);
        return h;
    }
    public void setSalt(String salt){
        this.salt = salt;
    }
}
