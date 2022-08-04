package de.atruvia;

import java.util.Scanner;

public class ticTacToeRekursivV3 {
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Du willst beginnen?");
        int board[][] = {{0,0,0},{0,0,0},{0,0,0}};
        if(scanner1.nextBoolean()){
            logBoardXO(board);
            while (winner(board) == -2) {

                board = playerInput(board, scanner1);
                logBoardXO(board);
                if (!(winner(board) == -2)) {
                    break;
                }
                board = compInput(board);
                logBoardXO(board);

            }
        }else {
            while (winner(board) == -2) {

                board = compInput(board);
                logBoardXO(board);
                if (!(winner(board) == -2)) {
                    break;
                }
                board = playerInput(board, scanner1);
                logBoardXO(board);

            }
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

    public static int winner(int [][] board){
        for (int i = 0; i < 3; i++) {
            int sum = board[0][i] + board[1][i] + board[2][i];
            if(sum==3||sum==-3){
                return board[0][i];
            }
        }
        for (int i = 0; i < 3; i++) {
            int sum = board[i][0] + board[i][1] + board[i][2];
            if(sum==3||sum==-3){
                return board[i][0];
            }
        }
        int sum=board[0][0] + board[1][1] + board[2][2];
        if(sum==3||sum==-3){
            return board[0][0];
        }
        sum=board[0][2] + board[1][1] + board[2][0];
        if(sum==3||sum==-3){
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
