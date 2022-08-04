package de.atruvia;

import java.util.Arrays;

public class Uebung1 {
    public static void main(String[]args){
        int zahl1=10;
        int zahl2=2;
        int zahl3= zahl1 * zahl2;
        int zahl4= zahl1 - zahl2;
        int zahl5= zahl4 + zahl3;
        System.out.println(zahl5);
        if(zahl3 > zahl4){
            System.out.println(zahl3 + " ist größer als " + zahl4);
        }else if(zahl3 < zahl4){
            System.out.println(zahl3 + " ist kleiner als " + zahl4);
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(i+1);
        }
        for (int i = 0; i < 10; i++) {
            if(i>5){
                System.out.println(i);
            }
        }
        int zahlen[]=new int[10];
        System.out.println(zahlen.length + Arrays.toString(zahlen));



    }

}
