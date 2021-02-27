package tictactoe;
import java.util.Scanner;

public class CallMain{
    public static void main(String[] args){
        TicTacToeGame obj = new TicTacToeGame();
        obj.createBoard();
        Scanner sc =new Scanner(System.in);
        obj.setUserCharacter(sc);

    }
}
