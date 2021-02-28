package tictactoe;
import java.util.Scanner;

public class CallMain{
    public static void main(String[] args){
        TicTacToeGame obj = new TicTacToeGame();
        Scanner sc =new Scanner(System.in);
        char ch = 'Y';
        while(ch == 'Y'){
            obj.reset();
            int res = 0;
            obj.setUserCharacter(sc);
            if( obj.toss(sc) ){
                System.out.println("You won, choose the position first!");
                while(obj.anyMoveLeft()){
                    obj.choosePosition(sc);
                    res = obj.checkWinner();
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
                    res = obj.checkWinner();
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
            if( res == 0){
                System.out.println("Tied!");
                obj.putBoardState();
            }
            System.out.println("Do you want to play another game? (y/n)");
            ch = sc.nextLine().toUpperCase().charAt(0);
        }
        sc.close();
    }
}
