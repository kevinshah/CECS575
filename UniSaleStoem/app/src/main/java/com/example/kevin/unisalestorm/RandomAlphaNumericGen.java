package com.example.kevin.unisalestorm;

import java.util.*;


/**
 * Created by Kevin on 10/3/15.
 */
public class RandomAlphaNumericGen {

    private static Random random = new Random((new Date()).getTime());

    /**
     * generates an alphanumeric string based on specified length 10.
     * @return random string
     */
    public static String generateRandomString() {
        char[] values = {'a','b','c','d','e','f','g','h','i','j',
                'k','l','m','n','o','p','q','r','s','t',
                'u','v','w','x','y','z','0','1','2','3',
                '4','5','6','7','8','9'};
        String out = "";
        for (int i=0;i<10;i++) {
            int idx=random.nextInt(values.length);
            out += values[idx];
        }
        return out;
    }

    public static String generateRandomString(int num) {
        char[] values = {'a','b','c','d','e','f','g','h','i','j',
                'k','l','m','n','o','p','q','r','s','t',
                'u','v','w','x','y','z','0','1','2','3',
                '4','5','6','7','8','9'};
        String out = "";
        for (int i=0;i<num;i++) {
            int idx=random.nextInt(values.length);
            out += values[idx];
        }
        return out;
    }
}
