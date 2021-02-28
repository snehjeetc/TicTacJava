package tictactoe;
import java.util.Scanner;
import java.util.Random;

public class TicTacToeGame{

    private static final String player = "Player";
    private static final String computer = "Computer";

    private char[][] board;
    private char playerChar;
    private char computerChar;

    TicTacToeGame(){
        board = new char[3][3];
        playerChar = ' ';
        computerChar = ' ';
    }

    public void createBoard(){
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                board[i][j] = ' ';
    }

    private void putBoardForPosition(){
        int count = 1;
        System.out.println("-------------");
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(j == 1 || j == 3){
                    if( board[i][j] == ' ')
                        System.out.print(" " + count + " ");
                    else
                        System.out.print(" " + board[i][j] + " ");
                }
                else{
                    if( board[i][j] == ' ' )
                        System.out.print("| " + count + " |");
                    else
                        System.out.print("| " + board[i][j] + " |");
                        
                }
                count++;
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public void setUserCharacter(Scanner sc){
        System.out.println("Enter the character (X or O): ");
        playerChar = sc.nextLine().toUpperCase().charAt(0);
        computerChar = (playerChar == 'X') ?  'O' : 'X' ; 
    }
    
    private Pair mapToIndices(int pos){
        if(pos < 1 || pos > 9){
            Pair p = new Pair(-1, -1);
            return p;
        }
        Pair p = new Pair();
        p.first = (pos - 1)/3;
        p.second = (pos - 1)%3;
        return p;
    }

    public boolean checkWinner(String user){
        if(player.equals(user)){
           return checkWinner(playerChar); 
        }
        else
            return checkWinner(computerChar);
    }

    private boolean checkWinner(char filler){
        //Horizontal check
        for(int i=0; i<3; i++){
            if(board[i][0] == board[i][1] && board[i][0] == board[i][2] 
                    && board[i][0] == filler)
                return true;
        }

        //Vertical check
        for(int j=0; j<3; j++){
            if(board[0][j] == board[1][j] && board[0][j] == board[2][j]
                   && board[0][j] == filler)
               return true; 
        }

        //Diagonal checks
        //towards left
        if(board [0][0] == filler){
            boolean res = true;
            for(int i=1; i<3; i++){
                if( board[i][i] != filler){
                    res = false;
                    break;
                }
            }
            if(res == true)
                return true;
        }
        
        //Diagonal check
        //towards right
        if(board [0][2] == filler){
            boolean res = true;
            for(int i=1,j=1; i<3 && j>=0 ; i++,j--){
                if( board[i][j] != filler){
                    res = false;
                    break;
                }
            }
            if(res == true)
                return true;
        }
        return false;
    }

    public void choosePosition(Scanner sc){
        System.out.println("Choose position (showing board): ");
        this.putBoardForPosition();
        System.out.println(": ");
        int pos = sc.nextInt();
        sc.nextLine();
        Pair indices = mapToIndices(pos);
        while( (indices.first == -1) || board[indices.first][indices.second] != ' '){
            if(indices.first == -1)
                System.out.println("Input out of bound"); 
            else
                System.out.println("The position is already filled");
            System.out.println("Enter the position again: ");
            pos = sc.nextInt();
            sc.nextLine();
            indices = mapToIndices(pos);
        }
        board[indices.first][indices.second] = playerChar;
    }

    public boolean toss(Scanner sc){
        System.out.println("Choose 1/0 : ");
        int user = sc.nextInt();
        sc.nextLine();
        Random rand = new Random();
        int ran_num = rand.nextInt(2);
        if( user == ran_num)
            return true;
        else
            return false;
    }
}


class Pair{
    public int first, second;
    Pair(){
        this.first = 0;
        this.second = 0;
    }
    Pair(int x, int y){
        this.first = x;
        this.second = y;
    }
}
