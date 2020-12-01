/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.utilities;

import java.util.Random;

/**
 *
 * @author Hau Huong
 */
public class GenerateCaptcha {
    static public String generateCapt() {
        Random rand = new Random();
        String str = "ABCDEFGHIKabcdefgh123456789";
        int size = 6;
        String captcha = "";
        for (int i = 0; i < size; i++) {
            captcha += str.charAt(rand.nextInt(str.length()));
        }
        return captcha;
    }
}
