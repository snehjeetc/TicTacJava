package tictactoe;

public class TicTacToeGame{
    private char[] board;

    TicTacToeGame(){
        board = new char[10];
    }

    public void createBoard(){
        for(int i=0; i<10; i++)
            board[i] = ' ';
    }


}
