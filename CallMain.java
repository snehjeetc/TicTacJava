package tictactoe;
import java.util.Scanner;

public class CallMain{
    public static void main(String[] args){
        TicTacToeGame obj = new TicTacToeGame();
        obj.createBoard();
        Scanner sc =new Scanner(System.in);
        obj.setUserCharacter(sc);
        if( obj.toss(sc) ){
            System.out.println("You won, choose the position first!");
            obj.choosePosition(sc);
        }
        else{
            System.out.println("You lost, computer will choose the position first!");
        }

    }
}
