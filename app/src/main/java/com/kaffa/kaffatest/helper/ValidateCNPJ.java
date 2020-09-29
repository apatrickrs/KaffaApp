package com.kaffa.kaffatest.helper;

import java.util.InputMismatchException;

public class ValidateCNPJ {

    public static boolean isCNPJ(String CNPJ) {
        // CNPJ's error formed by a sequence of equal numbers is considered
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
                CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
                CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
                CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
                CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
                (CNPJ.length() != 14)) {
            return(false);
        }

        char dig13, dig14;
        int sm, i, r, num, weight;

        // "try" - protects the code for any type conversion errors (int)
        try {
            //Calculation of 1st. Verifying digit
            sm = 0;
            weight = 2;
            for (i = 11; i >= 0; i --) {
                // converts the i-th character of the CNPJ into a number:
                // for example, transforms the character '0' into the integer 0
                // (48 is the position of '0' in the ASCII table)
                num = (int) (CNPJ.charAt(i) - 48);
                sm += (num * weight);
                weight += 1;

                if (weight == 10) {
                    weight = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char)((11-r) + 48);
            }

            //Calculation of the 2nd. Verifying digit
            sm = 0;
            weight = 2;
            for (i=12; i>=0; i--) {
                num = (int)(CNPJ.charAt(i)- 48);
                sm = sm + (num * weight);
                weight += 1;

                if (weight == 10) {
                    weight = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char)((11-r) + 48);
            }

            //Checks whether the calculated digits match the entered digits.
            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))) {
                return (true);
            } else {
                return(false);
            }
        } catch (InputMismatchException erro) {
            return(false);
        }
    }
}
