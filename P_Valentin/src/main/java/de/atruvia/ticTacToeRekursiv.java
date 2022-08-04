package de.atruvia;

import java.util.Scanner;

public class ticTacToeRekursiv {
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);
        int board[][] = {{0,0,0},{0,0,0},{0,0,0}};
        while(winner(board)==-2){
            logBoard(board);
            board=playerInput(board,scanner1);
            logBoard(board);
            board=compInput(board);


        }
        System.out.println(winner(board));
        switch (winner(board)){
            case 1:
                System.out.println("Du hast gewonnen");
                break;
            case -1:
                System.out.println("Der Computer hat gewonnen");
                break;
            case -2:
                System.out.println("Unentschieden");
        }


    }

    public static int winner(int[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] + board[1][i] + board[2][i]==3||board[0][i] + board[1][i] + board[2][i]==-3) {
                System.out.println("row" + i );
                return board[0][i];
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[i][0] + board[i][1] + board[i][2]==3||board[i][0] + board[i][1] + board[i][2]==-3) {
                System.out.println("column" + i );
                return board[i][0];
            }
        }
        if (board[0][0] + board[1][1] + board[2][2] == 3 ||board[0][0] + board[1][1] + board[2][2] == -3) {
            System.out.println("diagonal1" );
            return board[0][0];
        }
        if (board[0][2] + board[1][1] + board[2][0] ==3||board[0][2] + board[1][1] + board[2][0] ==-3) {
            System.out.println("diagonal2" );
            return board[0][2];
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j]==0){
                    return -2;
                }
            }
        }

        return 0;
    }

    public static void logBoard(int[][] board){
        System.out.println(board[0][0] + " | " + board[0][1] + " | " + board[0][2] + "\n" + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + "\n" + board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
    }

    public static int[][] playerInput(int[][] board,Scanner scanner){
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        board[y][x]=1;
        return board;
    }

    public static int[][] compInput(int[][] board){
        int scores[][]={{0,0,0},{0,0,0},{0,0,0}};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j]==0){
                    int tempBoard[][]=board;
                    tempBoard[i][j]=-1;
                    scores[i][j]=recursiveSearch(tempBoard);
                }
            }
        }
        int bestScore=0;
        boolean initialScore=true;
        int bestScoreIndex[]={0,0};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(scores[i][j]!=0&&(scores[i][j]<bestScore||initialScore)){
                    initialScore=false;
                    bestScore=scores[i][j];
                    bestScoreIndex[0]=i;
                    bestScoreIndex[1]=j;
                }
            }
        }
        board[bestScoreIndex[0]][bestScoreIndex[1]]=-1;
        return board;
    }

    public static int[] playerTwoOfThree(int[][] board){
        // Suche nach Siegposition des Spielers
        return twoOfThree(board, 1);
    }

    public static int[] compTwoOfThree(int[][] board){
        //Suche nach Siegposition des Computers
        return twoOfThree(board, -1);
    }

    public static int[] twoOfThree(int[][] board,int player){
        //horizontal
        for (int i = 0; i < 3; i++) {
            if(board[0][i] + board[1][i] + board[2][i]==2*player){
                for (int j = 0; j < 3; j++) {
                    if(board[j][i]==0){
                        return new int[] {j,i};
                    }
                }
            }
        }
        //vertikal
        for (int i = 0; i < 3; i++) {
            if(board[i][0] + board[i][1] + board[i][2]==2*player){
                for (int j = 0; j < 3; j++) {
                    if(board[i][j]==0){
                        return new int[] {i,j};
                    }
                }
            }
        }
        //diagonale1
        if(board[0][0] + board[1][1] + board[2][2]==2*player){
            for (int i = 0; i < 3; i++) {
                if(board[i][i]==0){
                    return new int[] {i,i};
                }
            }
        }
        //diagonale2
        if(board[0][2] + board[1][1] + board[2][0]==2*player){
            for (int i = 0; i < 3; i++) {
                if(board[i][2-i]==0){
                    return new int[] {i,2-i};
                }
            }
        }
        return new int[] {-1,-1};
    }
    public static boolean arrayEqual(int[] ar1, int[] ar2){
        for (int i = 0; i < ar1.length; i++) {
            if(ar1[i]!=ar2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int recursiveSearch(int[][] board){
        int tempWinner=winner(board);
        if(tempWinner==-2){ //Kein Gewinner steht fest
            int score=0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(board[i][j]==0){
                        int tempBoard[][]=board;
                        tempBoard[i][j]=-1;
                        score=score+recursiveSearch(tempBoard);
                    }
                }
            }
            return score;
        }else{
            return tempWinner;
        }
    }
    public static String[][] convertToXO(int[][] board){
        String boardXO[][]={{" "," "," "},{" "," "," "},{" "," "," "}};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (board[i][j]){
                    case 1:
                        boardXO[i][j]="X";
                        break;
                    case -1:
                        boardXO[i][j]="O";
                }
            }
        }
        return boardXO;
    }
    public static void logBoardXO(int[][] board){
        String boardXO[][]=convertToXO(board);
        System.out.println(boardXO[0][0] + " | " + boardXO[0][1] + " | " + boardXO[0][2] + "\n" + boardXO[1][0] + " | " + boardXO[1][1] + " | " + boardXO[1][2] + "\n" + boardXO[2][0] + " | " + boardXO[2][1] + " | " + boardXO[2][2]);
    }
}
