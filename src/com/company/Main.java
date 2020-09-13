package com.company;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    static String n1 = "10";
    static String n2 = "10";

    public static void main(String[] args) {
        System.out.println(karatsuba(n1, n2));
        System.out.println("ata");
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

    static String karatsuba(String n1, String n2) {
        int lengthNumber = n1.length();

        if (n2.length() > lengthNumber) {
            lengthNumber = n2.length();
        }

        while (n1.length() % 3 != 0) {
            addZeros(n1);
        }

        while (n2.length() % 3 != 0) {
            addZeros(n2);
        }

        if (n1.length() > n2.length()) {
            n1 = matchZeros(n1, n2);
        }
        if (n2.length() > n1.length()) {
            n2 = matchZeros(n2, n1);
        }

//      AD,
//      AE, BD
//      AF, CD BE
//      BF, CE
//      CF

        String a = n1.substring(0, n1.length() / 3);
        String b = n1.substring(n1.length() / 3, ((n1.length() / 3) * 2));
        String c = n1.substring(((n1.length() / 3) * 2), n1.length());
        String d = n2.substring(0, n2.length() / 3);
        String e = n2.substring(n2.length() / 3, ((n2.length() / 3) * 2));
        String f = n2.substring(((n2.length() / 3) * 2), n2.length());

        String ad = karatsuba(a, d);
        String ae = karatsuba(a, e);
        String af = karatsuba(a, f);
        String bd = karatsuba(b, d);
        String be = karatsuba(b, e);
        String bf = karatsuba(b, f);
        String ce = karatsuba(c, e);
        String cf = karatsuba(c, f);
        String cd = karatsuba(c, d);


        String adShift = shift(ad, (n1.length() / 3) * 4);
        String aeShift = shift(ae, (n1.length() / 3) * 3);
        String bdShift = shift(bd, (n1.length() / 3) * 3);
        String afShift = shift(af, (n1.length() / 3) * 2);
        String cdShift = shift(cd, (n1.length() / 3) * 2);
        String beShift = shift(be, (n1.length() / 3) * 2);
        String bfShift = shift(bf, n1.length() / 3);
        String ceShift = shift(ce, n1.length() / 3);
        String cfShift = shift(cf, 0);

        String adae = sum(adShift, aeShift);
        String adaebd = sum(adae, bd);
        String adaebdaf = sum(adaebd, af);
        String adaebdafcd = sum(adaebdaf, cd);
        String adaebdafcdbe = sum(adaebdafcd, be);
        String adaebdafcdbebf = sum(adaebdafcdbe, bf);
        String adaebdafcdbebfce = sum(adaebdafcdbebf, ce);
        String adaebdafcdbebfcecf = sum(adaebdafcdbebfce, cf);

        return adaebdafcdbebfcecf;
    }

    static String completeZero(String n, int qtd) {
        while (n.length() < qtd)
            n = "0" + n;
        return n;
    }

    static String removeZero(String num) {
        int aux = 0;
        if (num.length() == 1 && num.equalsIgnoreCase("0")) {
            return "0";
        }

        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) != '0') {
                break;
            }
            aux++;
        }

        return num.substring(aux, num.length());
    }

    static String shift(String n, int qtd) {
        int x = 0;
        while (qtd > x) {
            n = n + "0";
            x++;
        }
        return n;
    }

    static void fillZero() {
        while (n1.length() > n2.length()) {
            n2 = "0" + n2;
        }
        while (n2.length() > n1.length()) {
            n1 = "0" + n1;
        }
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

    public static String sub(String num1, String num2) {
        int n = num1.length();

        if (num2.length() > n)
            n = num2.length();
        num1 = completeZero(num1, n);
        num2 = completeZero(num2, n);

        String result = "";
        int carry = 0;

        for (int i = n - 1; i >= 0; i--) {
            int aux2 = num1.charAt(i) - num2.charAt(i);
            if (carry == 1) {
                aux2--;
                carry = 0;
            }
            if (aux2 < 0) {
                carry = 1;
                aux2 += 10;

            } else {
                carry = 0;
            }
            result = aux2 + result;
        }
        return result;
    }

}
