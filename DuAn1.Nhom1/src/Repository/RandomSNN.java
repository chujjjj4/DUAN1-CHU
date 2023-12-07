/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.security.SecureRandom;

/**
 *
 * @author hoanh
 */
public class RandomSNN {

    //az pham vi ky tu
    static final String az = "0123456789ABCDEFGHIJKLMNOPQRTUXYZabcdefghijklmnopqrtuxyz";

    //rd sinh ma ngau nhien
    static SecureRandom rd = new SecureRandom();

    
    //str tao mojt Builder trong voi suc chua da cho  
    public String soNgauNhienString(int n) {
        StringBuilder str = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            str.append(az.charAt(rd.nextInt(az.length())));
        }
        return str.toString();
    }

}
