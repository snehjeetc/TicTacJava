package tictactoe;
import java.util.Scanner;
import java.util.Random;

public class TicTacToeGame{

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

    private boolean anyMoveLeft(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i][j] == ' ')
                    return true;
            }
        }
        return false;
    }

    public int checkWinner(){
        //Horizontal check
        char winner = ' ';
        for(int i=0; i<3; i++){
            if(board[i][0] == board[i][1] && board[i][0] == board[i][2]){
                winner = board[i][0];
                if(winner == playerChar)
                    return 1;
                else if(winner == computerChar)
                    return -1;
            }
        }

        //Vertical check
        for(int j=0; j<3; j++){
            if(board[0][j] == board[1][j] && board[0][j] == board[2][j]){
                winner = board[0][j];
                if(winner == playerChar)
                    return 1;
                else if(winner == computerChar)
                    return -1;
            }
        }

        //Diagonal checks
        //towards left
        boolean res = true;
        for(int i=1; i<3; i++){
            if( board[i][i] != board[0][0] ){
                res = false;
                break;
            }
        }
        if(res == true){
            winner = board[0][0];
            if(winner == playerChar)
                return 1;
            else if(winner == computerChar)
                return -1;
        }
        
        //Diagonal check
        //towards right
        res = true;
        for(int i=1,j=1; i<3 && j>=0 ; i++,j--){
            if( board[i][j] != board[0][2] ){
                res = false;
                break;
            }
        }
        if(res == true){
            winner = board[0][2];
            if(winner == playerChar)
                return 1;
            else if(winner == computerChar)
                return -1;
        }
        return 0;
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

    public void computerMove(){
        Pair nextMove = new Pair(-1, -1);
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i][j]==' '){
                    board[i][j] = computerChar;
                    int res = checkWinner();
                    if(res == -1){
                        nextMove.first = i;
                        nextMove.second = j;
                        board[i][j] = ' ';
                        break;
                    }
                    board[i][j] = ' ';
                }
            }
            if(nextMove.first != -1)
                break;
        }

        if(nextMove.first != -1){
            board[nextMove.first][nextMove.second] = computerChar;
            return;
        }

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i][j] == ' '){
                    board[i][j] = playerChar;
                    int res = checkWinner();
                    if(res == 1){
                        nextMove.first = i;
                        nextMove.second = j;
                        board[i][j] = ' ';
                        break;
                    }
                    board[i][j] = ' ';
                }
            }
            if(nextMove.first != -1)
                break;
        }

        if(nextMove.first != -1){
            board[nextMove.first][nextMove.second] = computerChar;
            return;
        }

        boolean success= this.takeAvailableCorners();
    }

    private boolean takeAvailableCorners(){
        if(board[0][0] == ' '){
            board[0][0] = computerChar;
            return true;
        }
        if(board[0][2] == ' '){
            board[0][2] = computerChar;
            return true;
        }
        if(board[2][0] == ' '){
            board[2][0] = computerChar;
            return true;
        }
        if(board[2][2] == ' '){
            board[2][0] = computerChar;
            return true;
        }
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
