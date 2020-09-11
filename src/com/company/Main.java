package com.company;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    static String n1 = "10";
    static String n2 = "10";

    public static void main(String[] args) {
//        n1 = args[0];
//        n2 = args[1];

        removeZero(n1);
        removeZero(n2);

        System.out.println(karatsuba(n1,n2));
    }

    static String karatsuba(String num1, String num2) {
        int numberLength = num1.length();
        int[] abc = new int[3];
        int[] def = new int[3];
        int x = 0;
        int n1 = Integer.parseInt(num1);
        int n2 = Integer.parseInt(num2);

        if (num2.length() > num1.length()) {
            numberLength = num2.length();
        }

        while (n1 > 0) {
            abc[x] = n1 % 1000;
            n1 -= abc[abc.length - 1];
            n1 /= 1000;
            x++;
        }

        x = 0;

        while (n2 > 0) {
            def[x] = n2 % 1000;
            n2 -= def[def.length - 1];
            n2 /= 1000;
            x++;
        }

        num1 = completeZero(num1, numberLength);
        num2 = completeZero(num2, numberLength);

        String a = String.valueOf(abc[0]);
        String b = String.valueOf(abc[1]);
        String c = String.valueOf(abc[2]);
        String d = String.valueOf(def[0]);
        String e = String.valueOf(def[1]);
        String f = String.valueOf(def[2]);

        return "A: " + a + "B: +";
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
}
