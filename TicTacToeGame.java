package tictactoe;
import java.util.Scanner;

public class TicTacToeGame{
    private char[] board;

    TicTacToeGame(){
        board = new char[10];
    }

    public void createBoard(){
        for(int i=0; i<10; i++)
            board[i] = ' ';
    }


    public void takeInputFromUser(Scanner sc){
        System.out.println("Enter the character (X or O): ");
        char input = sc.nextLine().charAt(0);
    }
}
