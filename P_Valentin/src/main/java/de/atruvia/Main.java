package de.atruvia;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        // Ganze Zahl
        int i=255;
        // Kommazahl
        double pi = 3.1415926535;
        // Enzelnes Zeichen
        char character = 'a';
        // Zeichenkette
        String buchstabe="abcd";
        //Wahrheitswert
        boolean Freude=true;
        System.out.println("Freude:"+ Freude);

        //arethmetische Rechenoperationen
        int zahl1 = 12;
        int zahl2 = 4;
        int zahl3 = zahl1 + zahl2;
        System.out.println(zahl3);



        //__________START_______________
        String vorname = "Valentin";
        int alter = 18;
        System.out.println("Hello, " + vorname);
        System.out.println("Das Alter ist: \n" + alter);

        int wochentag =3;
        switch (wochentag){
            case 1:
                System.out.println("Mo");
                break;
            case 2:
                System.out.println("Di");
                break;
            case 3:
                System.out.println("Mi");
                break;
            case 4:
                System.out.println("Do");
                break;
            case 5:
                System.out.println("Fr");
                break;
            case 6:
                System.out.println("Sa");
                break;
            case 7:
                System.out.println("Montag");
                break;
        }


    }
}