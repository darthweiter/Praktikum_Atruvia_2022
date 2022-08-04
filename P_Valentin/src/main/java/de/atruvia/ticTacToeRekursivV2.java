package de.atruvia;

import java.util.Scanner;

public class ticTacToeRekursivV2 {
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);
        int board[][] = {{0,0,0},{0,0,0},{0,0,0}};
        logBoardXO(board);
        while(winner(board)==-2){

            board=playerInput(board,scanner1);
            if(! (winner(board)==-2)){
                break;
            }
            logBoardXO(board);
            board=compInput(board);
            logBoardXO(board);


        }
        switch (winner(board)){
            case 1:
                System.out.println("Du hast gewonnen");
                break;
            case -1:
                System.out.println("Der Computer hat gewonnen");
                break;
            case 0:
                System.out.println("Unentschieden");
        }


    }

    public static int winner(int[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] != 0) {
                return board[0][i];
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] != 0) {
                return board[i][0];
            }
        }
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != 0) {
            return board[0][0];
        }
        if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != 0) {
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
        int x = -1;
        int y = -1;
        boolean firstRound=true;
        while(x<0||y<0||x>2||y>2||board[y][x]!=0) {
            if(!firstRound){
                System.out.println("Bitte gebe die Koordinaten unbelegter Felder hintereinander ein.");
            }
            x = scanner.nextInt();
            y = scanner.nextInt();
            firstRound=false;
        }
        board[y][x]=1;
        return board;
    }

    public static int[][] compInput1(int[][] board){
        //Suche nach Siegposition des Computers
        int[] temp = compTwoOfThree(board);
        if(!isMinus1(compTwoOfThree(board))){
            int[] pos = compTwoOfThree(board);
            board[pos[0]][pos[1]]=-1;
            return board;
        }
        // Suche nach Siegposition des Spielers
        else if (!isMinus1(playerTwoOfThree(board))) {
            int[] pos = playerTwoOfThree(board);
            board[pos[0]][pos[1]]=-1;
            return board;
        }

        int[][] priority = {{1,1},{0,0},{0,2},{2,2},{2,0},{0,1},{1,0},{1,2},{2,1}};
        for (int i = 0; i < priority.length; i++) {
            if(board[priority[i][0]][priority[i][1]]==0){
                board[priority[i][0]][priority[i][1]]=-1;
                return board;
            }
        }
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
    public static boolean isMinus1(int[] ar1){
        return ar1[0]==-1;
    }

    public static String[][] convertToXO(int[][] board){
        String boardXO[][]={{"_","_","_"},{"_","_","_"},{"_","_","_"}};
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
        System.out.println(boardXO[0][0] + " | " + boardXO[0][1] + " | " + boardXO[0][2] + "\n" + boardXO[1][0] + " | " + boardXO[1][1] + " | " + boardXO[1][2] + "\n" + boardXO[2][0] + " | " + boardXO[2][1] + " | " + boardXO[2][2] + "\n");
    }

    public static int minimax(int[][] board, int depth, boolean isMax){
        int result = winner(board);
        if(!(result==-2)){
            return result* -10;
        }
        if(isMax){
            int bestScore = -3628801;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(board[i][j]==0){
                        board[i][j]=-1;
                        int score = minimax(board, depth+1,false);
                        board[i][j] = 0;
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = 3628801;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(board[i][j]==0){
                        board[i][j]=1;
                        int score = minimax(board, depth+1,true);
                        board[i][j] = 0;
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }

    }
    public static int[][]compInput(int[][] board){
        int bestScore = -3628801;
        int[] move= {-1,-1};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j]==0) {
                    board[i][j]=-1;
                    int score = minimax(board,0,false);
                    board[i][j]=0;
                    if(score > bestScore){
                        bestScore=score;
                        move[0]=i;
                        move[1]=j;
                    }
                }

            }
        }
        board[move[0]][move[1]]=-1;
        return board;
    }

}