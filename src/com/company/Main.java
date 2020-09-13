package com.company;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    static String n1 = "";
    static String n2 = "";

    public static void main(String[] args) {
        n1 = args[0];
        n2 = args[1];
        System.out.println(removeZero(karatsuba(n1, n2)));
    }

    public static String removeZero(String n) {
        int aux = 0;
        if (n.length() == 1 && n.equals("0")) {
            return "0";
        }
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) != '0') {
                break;
            }
            aux++;
        }
        return n.substring(aux);
    }

    static String addZeros(String n) {
        return "0" + n;
    }

    static String matchZeros(String n1, String n2) {
        if (n1.length() > n2.length()) {
            while (n1.length() != n2.length()) {
                n2 = addZeros(n2);
            }
        }
        if (n2.length() > n1.length()) {
            while (n1.length() != n2.length()) {
                n1 = addZeros(n1);
            }
        }
        return n1 + "\n" + n2;
    }

    static String karatsuba(String num1, String num2) {
        int lengthNumber = num1.length();

        if (num2.length() > lengthNumber) {
            lengthNumber = num2.length();
        }

        if (lengthNumber < 2) {
            int value = Integer.parseInt(num1) * Integer.parseInt(num2);
            return String.valueOf(value);
        }

        while (num1.length() % 3 != 0) {
            num1 = addZeros(num1);
        }

        while (num2.length() % 3 != 0) {
            num2 = addZeros(num2);
        }


//      AD,
//      AE, BD
//      AF, CD BE
//      BF, CE
//      CF

        String a = num1.substring(0, num1.length() / 3);
        String b = num1.substring(num1.length() / 3, ((num1.length() / 3) * 2));
        String c = num1.substring(((num1.length() / 3) * 2), num1.length());
        String d = num2.substring(0, num2.length() / 3);
        String e = num2.substring(num2.length() / 3, ((num2.length() / 3) * 2));
        String f = num2.substring(((num2.length() / 3) * 2), num2.length());

        String ad = karatsuba(a, d);
        String ae = karatsuba(a, e);
        String af = karatsuba(a, f);
        String bd = karatsuba(b, d);
        String be = karatsuba(b, e);
        String bf = karatsuba(b, f);
        String ce = karatsuba(c, e);
        String cf = karatsuba(c, f);
        String cd = karatsuba(c, d);


        String adShift = shift(ad, (num1.length() / 3) * 4);
        String aeShift = shift(ae, (num1.length() / 3) * 3);
        String bdShift = shift(bd, (num1.length() / 3) * 3);
        String afShift = shift(af, (num1.length() / 3) * 2);
        String cdShift = shift(cd, (num1.length() / 3) * 2);
        String beShift = shift(be, (num1.length() / 3) * 2);
        String bfShift = shift(bf, num1.length() / 3);
        String ceShift = shift(ce, num1.length() / 3);
        String cfShift = shift(cf, 0);

        String adae = sum(adShift, aeShift);
        String adaebd = sum(adae, bdShift);
        String adaebdaf = sum(adaebd, afShift);
        String adaebdafcd = sum(adaebdaf, cdShift);
        String adaebdafcdbe = sum(adaebdafcd, beShift);
        String adaebdafcdbebf = sum(adaebdafcdbe, bfShift);
        String adaebdafcdbebfce = sum(adaebdafcdbebf, ceShift);
        String adaebdafcdbebfcecf = sum(adaebdafcdbebfce, cfShift);

        return adaebdafcdbebfcecf;
    }

    static String completeZero(String n, int qtd) {
        while (n.length() < qtd)
            n = "0" + n;
        return n;
    }

    static String shift(String n, int qtd) {
        int x = 0;
        while (qtd > x) {
            n = n + "0";
            x++;
        }
        return n;
    }

    public static String sum(String n1, String n2) {
        int length = n1.length();
        if (n2.length() > length) {
            length = n2.length();
        }

        n1 = completeZero(n1, length);
        n2 = completeZero(n2, length);

        String result = "";
        int carry = 0;

        for (int i = length - 1; i >= 0; i--) {
            int aux = n1.charAt(i) + n2.charAt(i) - (48 * 2);
            if (carry == 1) {
                aux++;
                carry = 0;
            }
            if (aux >= 10) {
                carry = 1;
                aux -= 10;
            } else {
                carry = 0;
            }

            result = aux + result;

        }
        if (carry == 1) {
            result = "1" + result;
        }

        return result;
    }
}
