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
            while(obj.anyMoveLeft()){
                obj.choosePosition(sc);
                int res = obj.checkWinner();
                if(res == 1){
                    System.out.println("Congrats, you won!");
                    obj.putBoardState();
                    break;
                }
                obj.computerMove();
                res = obj.checkWinner();
                if( res == -1){
                    System.out.println("Defeated, computer wins in style!");
                    obj.putBoardState();
                    break;
                }
            }
        }
        else{
            System.out.println("You lost, computer will choose the position first!");
            while(obj.anyMoveLeft()){
                obj.computerMove();
                int res = obj.checkWinner();
                if( res == -1){
                    System.out.println("Defeated, computer wins in style!");
                    obj.putBoardState();
                    break;
                }
                obj.choosePosition(sc);
                res = obj.checkWinner();
                if( res == 1){
                    System.out.println("Congrats, you won!");
                    obj.putBoardState();
                    break;
                }
            }
        }
        sc.close();
    }
}
