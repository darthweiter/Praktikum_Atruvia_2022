// Math.random

package de.atruvia.p_tobias_kaufmann;

import java.util.Scanner;

public class HoeherTieferZahlenraten_Einfach {

    public static void main(String[] args) {
        Scanner meinScanner = new Scanner(System.in);       // Scanner initialisieren
        int randomZahl = (int) (Math.random()*100);         // Random nummer Generieren
        int errateneZahl;
        boolean zahlWurdeErraten = false;
        int anazahlVersuche = 1;                            // Versuchcounter

        System.out.println("Es wurde eine zufällige Zahl zwischen 0 und 100 generiert. Versuche doch sie zu erraten.");
        errateneZahl = meinScanner.nextInt();               // Zahl vom Spieler einlesen
        if (errateneZahl == randomZahl){                    // Überprüfung ob Zahl richtig ist
            System.out.println("Du Cheatest doch, beim ersten versuch hat das noch keiner geschafft!");
        }else {
            tippAusgabe(randomZahl,errateneZahl);
            while (!zahlWurdeErraten){                      // Wurde die Zahl richtig erraten
                errateneZahl = meinScanner.nextInt();       // Zahl vom Spieler einlesen
                if (errateneZahl == randomZahl){            // Hat die eingabe gestimmt
                    zahlWurdeErraten = true;
                }else {
                    tippAusgabe(randomZahl,errateneZahl);   // Ausgabe ob zahl zu hoch oder zu tief war
                }
                anazahlVersuche++;                          // Versuchscounter
            }
            System.out.println("Suppi du hast die Zahl nach " + anazahlVersuche + " Versuchen erraten.");
        }
    }

    public static void tippAusgabe(int random, int erraten){
        if (random>erraten){                                // Die Zahl ist zu hoch
            System.out.println("Schade leider falsch, veruche es doch erneute mit einer größeren Zahl.");
        } else {                                            // Die Zahl ist zu niedrig
            System.out.println("Schade leider falsch, veruche es doch erneute mit einer kleineren Zahl.");
        }
    }


}
