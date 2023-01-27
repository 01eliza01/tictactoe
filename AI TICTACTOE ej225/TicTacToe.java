 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame {


    /**
     * For Some reason it wanted me to do this so the yellow squiggly would go away.
     */
    private static final long serialVersionUID = 1L;

    public static void main(String [] args) {
        
        TicTacToe runnext=new TicTacToe();
        runnext.popupintroscreen();
    }

    private JButton button0,
                    button1, 
                    button2, 
                    button3, 
                    button4, 
                    button5, 
                    button6, 
                    button7, 
                    button8,
                    btn,
                    replay;
    private JFrame introscreen;
    private JPanel boardSize;
    public Font TToeFont = new Font("Arial",Font.PLAIN,30);
    public boolean gameinit = true ;
    public boolean firstmoved=false;
    private TicTacToeClass board;
    
    
    public void popupintroscreen()
    {
        //Creating introduction text and it's wrapper
        
        JTextArea introtext = new JTextArea("Welcome to TicTacToe vs A.I.\nCreated by Eliza Jasaityte \nEnter your name:");
        introtext.setEditable(false);
        introtext.setLineWrap(true);
        JTextField userInput = new JTextField("Enter name here");
        
        
        // ----------------------------------- DECLARING ALL JBUTTONS IN THE INTRO SCREEN AND ADDING ACTION LISTENERS ---------------------------------------------------

        JButton startgamebutton = new JButton("Start Game");
        startgamebutton.setToolTipText("Start a game of Tic-Tac-Toe");
        startgamebutton.setFont(TToeFont);
        startgamebutton.addActionListener(new ActionListener()
        {
           @Override
           public void actionPerformed(ActionEvent gamestart)
           {
               String struser=userInput.getText();
            if(struser!=null && !struser.isEmpty() && gameinit==true){
            JOptionPane.showMessageDialog(null, "Starting game");
            introtext.setText("Place a nought \nAI will place a cross after you "+struser);
            startgamebutton.setEnabled(false);
            TicTacToe();
            gameinit = false;
            
            }
            else if (btn.getModel().isPressed()){
                replay.setEnabled(true);
            }
            
             else {
            JOptionPane.showMessageDialog(null, "Enter user name" , "Error" , JOptionPane.ERROR_MESSAGE);
        }
           }
        });

        JButton reset = new JButton("Reset");
        reset.setToolTipText("Reset game");
        reset.addActionListener(new ActionListener()
        {
            
            public void actionPerformed(ActionEvent resetgame)
            {
                setVisible(false); //you can't see it
                dispose(); //Destroy the JFrame object
                introscreen.dispose();
                TicTacToe runnext=new TicTacToe();
                runnext.popupintroscreen();
            }
        });

        JButton replay = new JButton("Replay");
        replay.setToolTipText("Replay this game");
        replay.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent replaygame)
            {
                resetBoard();
            }
        });

        JButton quitgamebutton = new JButton("Quit Game");
        quitgamebutton.setToolTipText("Quit the game :(");
        quitgamebutton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent onquitgamebuttonclick)
            {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit the game?" , "Really quit?" , JOptionPane.YES_NO_OPTION );
                if(response == JOptionPane.YES_OPTION)
                {
                System.exit(0);
            } 
            }
        });
        this.setSize(800,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("A.I. Tic Tac Toe");
        JPanel gamebuttons = new JPanel();
        gamebuttons.setSize(800,800);
        
        gamebuttons.setLayout(new GridLayout(3,3));
        
        // Buttons 0-2 Make up positions A1-A3
        
    
        gamebuttons.add(introtext);
        
        gamebuttons.add(userInput);
        gamebuttons.add(reset);
        gamebuttons.add(replay);
        gamebuttons.add(startgamebutton);
        gamebuttons.add(quitgamebutton);
        
        introscreen = new JFrame("Tic Tac Toe");
        introscreen.setSize(400,400);
        introscreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        introscreen.setLocationRelativeTo(null);
        introscreen.add(gamebuttons);
        introscreen.setVisible(true);
        //Creating the screen itself and setting it visible
        
    }
    
        
    public int TicTacToe() {
        // Set up the grid
        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("A.I. Tic Tac Toe");
        JPanel boardSize = new JPanel();
        boardSize.setSize(300,300);
        boardSize.setLayout(new GridLayout(3,3));
        
        // Buttons 0-2 Make up positions A1-A3
        button0 = createButton("A1");
        button1 = createButton("A2");
        button2 = createButton("A3");
        // Buttons 3-5 Make up positions B1-B3
        button3 = createButton("B1");
        button4 = createButton("B2");
        button5 = createButton("B3");
     // Buttons 6-8 Make up positions C1-C3
        button6 = createButton("C1");
        button7 = createButton("C2");
        button8 = createButton("C3");
        boardSize.add(button0);
        boardSize.add(button1);
        boardSize.add(button2);
        boardSize.add(button3);
        boardSize.add(button4);
        boardSize.add(button5);
        boardSize.add(button6);
        boardSize.add(button7);
        boardSize.add(button8);
        this.add(boardSize);
        this.setVisible(true);

        // Start the game
        board = new TicTacToeClass();
        int gamer = 1 ;
            return gamer  ;
    }
    
    private JButton createButton(String square) {
        JButton btn = new JButton();
        btn.setPreferredSize(new Dimension(50, 50));
        Font f = new Font("Dialog", Font.PLAIN, 72);
        btn.setFont(f);
    // Using lambda notation to simplify logic.
        btn.addActionListener(e -> btnClick(e, square));
        return btn;
    }
       
    

    private void btnClick(ActionEvent e, String square) {
        
    
        if (board.getSquare(square) != 0)
            return;
       
        JButton btn = (JButton)e.getSource();
        btn.setText("O");
        board.playMove(square, 1);
        
        // If isGameOver = 3, Its a tie.
        if (board.isGameOver() == 3) {
            JOptionPane.showMessageDialog(null, "It appears we are equally matched", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetBoard();
            return;
        }

        // If isGameOver = 1, somehow you beat the minimax 
        if (board.isGameOver() == 1) {
            JOptionPane.showMessageDialog(null,"Impressive!", "You Won!", JOptionPane.INFORMATION_MESSAGE);
            resetBoard();
            return;
        }
        String aiMove = board.getNextMove();
        board.playMove(aiMove,2);
        
        switch (aiMove){
            case "A1": button0.setText("X");
                break;
            case "A2": button1.setText("X");
                break;
            case "A3": button2.setText("X");
                break;
            case "B1": button3.setText("X");
                break;
            case "B2": button4.setText("X");
                break;
            case "B3": button5.setText("X");
                break;
            case "C1": button6.setText("X");
                break;
            case "C2": button7.setText("X");
                break;
            case "C3": button8.setText("X");
                break;
        }

        if (board.isGameOver() == 2) {
            JOptionPane.showMessageDialog(null,
                "Score 1 for A.I.! ", "You lose!",
                JOptionPane.INFORMATION_MESSAGE);
            resetBoard();
            return;
        }
    }
    private void resetBoard() {
        board.reset();
        button0.setText("");
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
    }
    
}


 class TicTacToeClass {
    
    // These are all the possible win combinations.
    private int winningCombos [][] = {{0, 1, 2},{3, 4, 5},{6, 7, 8},{0, 3, 6},{1, 4, 7},{2, 5, 8},{0, 4, 8},{2, 4, 6}};

    public TicTacToeClass() {
        this.reset();
    }
    
    private int board [];
    
    public void reset() {
        board = new int[] {2, 2, 2, 2, 2, 2, 2, 2, 2};
    }

    private int getSquare(int index) {
        if (index < 0 || index > 8)
            throw new IllegalArgumentException("Error! Must be between 0-8");

        return board[index];
    }

    public int getSquare(String square) {
        int index = boardIndex(square);
        if (index == -1)
            throw new IllegalArgumentException("Error! Illegal move!");
        switch (getSquare(index)){
            case 3:
                return 1;
            case 5:
                return 2;
            default:
                return 0;
        }
    }

    private int boardIndex(String square) {
        switch (square) {
            case "A1":
                return 0;
            case "A2":
                return 1;
            case "A3":
                return 2;
            case "B1":
                return 3;
            case "B2":
                return 4;
            case "B3":
                return 5;
            case "C1":
                return 6;
            case "C2":
                return 7;
            case "C3":
                return 8;
            default:
                return -1;
        }
    }

    private String matchPositionToIndex(int index) {
        switch (index) {
            case 0:
                return "A1";
            case 1:
                return "A2";
            case 2:
                return "A3";
            case 3:
                return "B1";
            case 4:
                return "B2";
            case 5:
                return "B3";
            case 6:
                return "C1";
            case 7:
                return "C2";
            case 8:
                return "C3";
            default:
                return "";
        }
    }
    public void playMove(String square, int player) {
        int index = boardIndex(square);
        if (index == -1)
            throw new IllegalArgumentException("Error. Not a valid position.");
        this.isValidMove(index, player);
    }

    private void isValidMove(int index, int player) {
        if (index < 0 | index > 8)
            throw new IllegalArgumentException("Error! Must be in squares 0 through 8");
        if (player <1 | player > 2)
            throw new IllegalArgumentException("Error! This is a single-player A.I assisted game. 2 Player max");
        if (board[index] != 2)
            throw new IllegalArgumentException("Error! This is not a valid position to take");
        if (player == 1)
            board[index] = 3;
        else
            board[index] = 5;
    }
    // Checking for who wins. If p = 27 then we win, and if p = 125 the algorithm wins.
    public int isGameOver() {
        for (int i = 0; i < 8; i++) {
            int j = getWinnerComboScore(i);
            if (j == 27)
                return 1;     
            if (j == 125)
                return 2;      
        }
        // Here we check if there are any playable positions left. If not, its  a tie.
        int noWinner = 0;
        for (int i = 0; i < 9; i++)
            if (board[i] == 2)
                noWinner++;
        if (noWinner == 0)
            return 3;          
        return 0;              
    }
    // Matches winning combos to the current board map.
    public String isHumanWinner(int player) {
        if (player < 1 || player > 2)
            throw new IllegalArgumentException("player must be 1 or 2");

        for (int i = 0; i < 8; i++) {
            int j = getWinnerComboScore(i);
            if (   (player == 1 && j == 18) || (player == 2 && j == 50) ) {
                if (board[winningCombos[i][0]] == 2)
                    return matchPositionToIndex(winningCombos[i][0]);
                if (board[winningCombos[i][1]] == 2)
                    return matchPositionToIndex(winningCombos[i][1]);
                if (board[winningCombos[i][2]] == 2)
                    return matchPositionToIndex(winningCombos[i][2]);
            }
        }
        return "";
    }
    private int getWinnerComboScore(int combo) {
        return board[winningCombos[combo][0]] * board[winningCombos[combo][1]] * board[winningCombos[combo][2]];
    }
    
    public String getNextMove() {
        String bestMove;
        // Find the optimal move to use to win.
        bestMove = this.isHumanWinner(2);
        if (bestMove != "")
            return bestMove;
        // If the human player has an optimal move to win, intercept.
        bestMove = this.isHumanWinner(1);
        if (bestMove != "")
            return bestMove;
        // Checking for potential winning combos.
        if (board[4] == 2)
            return "B2";
        if (board[0] == 2)
            return "A1";
        if (board[2] == 2)
            return "A3";
        if (board[6] == 2)
            return "C1";
        if (board[8] == 2)
            return "C3";
        if (board[1] == 2)
            return "A2";
        if (board[3] == 2)
            return "B1";
        if (board[5] == 2)
            return "B3";
        if (board[7] == 2)
            return "C2";
        return "";    
    }
    public String toString() {
        return " " + getPlayerAt(board[0]) + " | " + getPlayerAt(board[1]) + " | " + getPlayerAt(board[2]) + "\n-----------\n" +" " +
               getPlayerAt(board[3]) + " | " +getPlayerAt(board[4]) + " | " + getPlayerAt(board[5]) + "\n-----------\n" +" " +
               getPlayerAt(board[6]) + " | " + getPlayerAt(board[7]) + " | " + getPlayerAt(board[8]);
    }
    private String getPlayerAt(int status) {
        if (status == 3)
            return "O";
        if (status == 5)
            return "X";
        return " ";
    }
}