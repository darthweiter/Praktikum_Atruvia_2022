// 2 Dimensonales Array

package de.atruvia.p_tobias_kaufmann;

import java.util.Scanner;

public class TicTacToe_Mittel {
    static int spalte;
    static int zeile;
    static String nameSpieler1;
    static String nameSpieler2;

    static char[][] spielfeld = {{' ',' ',' '},
                                 {' ',' ',' '},
                                 {' ',' ',' '}};        // Spielfeld erstellen

    public static void main(String[] args) {

        Scanner meinScanner = new Scanner(System.in);   // Namen der Spieler einlesen
        System.out.println("Spieler 1 wie heißt du?");
        nameSpieler1 = meinScanner.next();
        System.out.println("Spieler 2 wie heißt du?");
        nameSpieler2 = meinScanner.next();

        spielfeldAusgeben();
        for (int i = 0; i < 9; i++) {                   // 9 Durchgänge um Steine zu setzen
            if (i % 2 == 0){
                System.out.println(nameSpieler1 + " du bist dran, wo möchtest du dein kreuz setzen?");
                steinSetzen(1);
                spielfeldAusgeben();
                checkForWinner();
            }else {
                System.out.println(nameSpieler2 + " du bist dran, wo möchtest du dein kreuz setzen?");
                steinSetzen(2);
                spielfeldAusgeben();
                checkForWinner();
            }
        }
        System.out.println("Unentschieden.");
    }

    public static void spielfeldAusgeben(){             // Aktuellen stand des Spielfedes ausgeben
        System.out.println("  1 2 3");
        for (int i = 0; i < 3; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(spielfeld[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void steinSetzen(int spielerNummer){  // Sequenz um einen Stein zu setzten
        Scanner meinScanner = new Scanner(System.in);
        String spielerEingabe = meinScanner.next();
        while (spielerEingabe.length() != 2){           // Falsche eingabe abfangen
            System.out.println("Bitte im Voramt 10 angeben");
            spielerEingabe = meinScanner.next();
        }
        System.out.println(spielerEingabe);
        spalte = Integer.parseInt(String.valueOf(spielerEingabe.toCharArray()[0]))-1;   // Spalte auslesen aus der eingabe
        zeile = Integer.parseInt(String.valueOf(spielerEingabe.toCharArray()[1]))-1;    // Zeile auslesen aus der eingabe

        while (spielfeld[spalte][zeile] != ' '){        // Belegtes Feld abfangen
            spielfeldAusgeben();
            System.out.println("Dieses Feld ist bereits belegt bitte wähle ein anderes!");
            spielerEingabe = meinScanner.next();
            System.out.println(spielerEingabe);
            while (spielerEingabe.length() != 2){       // Falsche eingabe abfangen
                System.out.println("Bitte im Voramt 10 angeben");
                spielerEingabe = meinScanner.next();
            }
            spalte = Integer.parseInt(String.valueOf(spielerEingabe.toCharArray()[0]))-1;   // Spalte auslesen aus der eingabe
            zeile = Integer.parseInt(String.valueOf(spielerEingabe.toCharArray()[1]))-1;    // Zeile auslesen aus der eingabe
        }
        if (spielerNummer == 1){
            spielfeld[spalte][zeile] = 'X';             // X setzen
        }else {
            spielfeld[spalte][zeile] = 'O';             // O setzen
        }
    }

    public static void checkForWinner(){
        for (int i = 0; i < 3; i++) {                   // Alle steine in einer reihe gleich?
            if (spielfeld[0][i] == spielfeld[1][i] && spielfeld[0][i] == spielfeld[2][i] && spielfeld[2][i] != ' '){
                if (spielfeld[0][i] == 'X'){
                    System.out.println("Herzlichen Glückwunsch " + nameSpieler1 + " du hast gewonenn!" );
                    System.exit(1);
                }else {
                    System.out.println("Herzlichen Glückwunsch " + nameSpieler2 + " du hast gewonenn!" );
                    System.exit(1);
                }
            }
        }

        for (int i = 0; i < 3; i++) {                   // Alle steine in einer Spalte gleich
            if (spielfeld[i][0] == spielfeld[i][1] && spielfeld[i][0] == spielfeld[i][2] && spielfeld[i][2] != ' '){
                if (spielfeld[i][0] == 'X'){
                    System.out.println("Herzlichen Glückwunsch " + nameSpieler1 + " du hast gewonenn!" );
                    System.exit(1);
                }else {
                    System.out.println("Herzlichen Glückwunsch " + nameSpieler2 + " du hast gewonenn!" );
                    System.exit(1);
                }
            }
        }

        // Quer gleich

        if (spielfeld[0][0] == spielfeld[1][1] && spielfeld[0][0] == spielfeld[2][2] && spielfeld[2][2] != ' '){
            if (spielfeld[0][0] == 'X'){
                System.out.println("Herzlichen Glückwunsch " + nameSpieler1 + " du hast gewonenn!" );
                System.exit(1);
            }else {
                System.out.println("Herzlichen Glückwunsch " + nameSpieler2 + " du hast gewonenn!" );
                System.exit(1);
            }
        }

        if (spielfeld[2][0] == spielfeld[1][1] && spielfeld[2][0] == spielfeld[0][2] && spielfeld[0][2] != ' '){
            if (spielfeld[1][1] == 'X'){
                System.out.println("Herzlichen Glückwunsch " + nameSpieler1 + " du hast gewonenn!" );
                System.exit(1);
            }else {
                System.out.println("Herzlichen Glückwunsch " + nameSpieler2 + " du hast gewonenn!" );
                System.exit(1);
            }
        }
    }
}
