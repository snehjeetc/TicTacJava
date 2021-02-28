package tictactoe;
import java.util.Scanner;

public class TicTacToeGame{
    private char[] board;
    private char userChar;
    private char computerChar;

    TicTacToeGame(){
        board = new char[10];
        userChar = ' ';
        computerChar = ' ';
    }

    public void createBoard(){
        for(int i=0; i<10; i++)
            board[i] = ' ';
    }

    private void putBoardForPosition(){
        int count = 1;
        System.out.println("------------");
        for(int i=1; i<=3; i++){
            for(int j=1; j<=3; j++){
                if(j == 1 || j == 3){
                    if( board[count] == ' ')
                        System.out.print(" " + count + " ");
                    else
                        System.out.print(" " + board[count] + " ");
                }
                else{
                    if( board[count] == ' ' )
                        System.out.print("| " + count + " |");
                    else
                        System.out.print("| " + board[count] + " |");
                        
                }
                count++;
            }
            System.out.println();
            System.out.println("------------");
        }
    }

    public void setUserCharacter(Scanner sc){
        System.out.println("Enter the character (X or O): ");
        userChar = sc.nextLine().toUpperCase().charAt(0);
        computerChar = (userChar == 'X') ?  'O' : 'X' ; 
    }
    
    public void choosePosition(Scanner sc){
        System.out.println("Choose position (showing board): ");
        this.putBoardForPosition();
        System.out.println(": ");
        int pos = sc.nextInt();
        sc.nextLine();
        while( (pos < 1 || pos > 9) || board[pos] != ' '){
            if(pos < 1 || pos > 9)
                System.out.println("Input out of bound"); 
            else
                System.out.println("The position is already filled");
            System.out.println("Enter the position again: ");
            pos = sc.nextInt();
            sc.nextLine();
        }
        board[pos] = userChar;
    }
}
