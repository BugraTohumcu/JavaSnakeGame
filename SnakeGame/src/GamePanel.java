import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {

    //Map
    private  final int BOARD_WIDTH = 800;
    private final int BOARD_HEIGHT = 800;
    final int UNIT_SIZE = 25;

    //Fruit
    private Random random;
    int fruitX;
    int fruitY;
    int fruitEaten=0;

    //Snake
    private int tailCounter = 1;
    int snakeXStartingPoint = BOARD_WIDTH / 2 - (10*UNIT_SIZE) ;
    int snakeYStartingPoint = BOARD_HEIGHT / 2 - UNIT_SIZE ;
    private final int[] snakeX = new int[UNIT_SIZE];
    private final int[] snakeY = new int[UNIT_SIZE];


    //Initialization for snakeMove Class
    char RIGHT = 'D';
    char LEFT = 'A';
    char UP = 'W'; 
    char DOWN = 'S';
    char direction = RIGHT;
    char lastDirection = LEFT;

    //Timer 
    private Timer timer ;
    private final int delay = 75;

    //Font
    private final Font textFont = new Font("MV boli",Font.BOLD, 40);



    //Game over 
    boolean gameEnd;
    private JButton playAgainButton;

    GamePanel() {

        initializePlayAgainButton();
        generateFruit();
        initializeGamePanel(); 
    }

    public void initializeGamePanel(){
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setFocusable(true);
        this.setBackground(Color.black);
        this.add(playAgainButton);
        this.addKeyListener(new SnakeMove(this));
        this.setLayout(null);
        timer = new Timer(delay,new SnakeMove(this));
        timer.start();
    }

    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawFruit(g);
        drawSnake(g);
        drawScore(g);
        //drawMap(g);
        if(gameEnd) gameOver(g);

    }

   // Assuming this is within your GamePanel class

   public void gameRestart() {
    // Reset other game state variables
    snakeXStartingPoint = BOARD_WIDTH / 2 - (10 * UNIT_SIZE);
    snakeYStartingPoint = BOARD_HEIGHT / 2 - UNIT_SIZE;
    tailCounter = 1;
    fruitEaten = 0;
    direction = RIGHT; // Reset direction to default
    lastDirection = LEFT; // Reset last direction to default
    gameEnd = false;
    generateFruit();

    playAgainButton.setVisible(false);
    timer.start();
}



    public void initializePlayAgainButton(){

        playAgainButton= new JButton();
        playAgainButton.setText("Play Again");
        playAgainButton.setBorder(null);
        playAgainButton.setBackground(Color.black);
        playAgainButton.setForeground(Color.red);
        playAgainButton.setVisible(false);
        playAgainButton.setFocusable(false);
        playAgainButton.setBounds((BOARD_WIDTH-300)/2,(BOARD_HEIGHT-150)/2,300,100);
        playAgainButton.setFont(textFont);
        playAgainButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==playAgainButton) {
                     gameRestart();
                }
            }
        });

    }


    private void drawScore(Graphics g) {
        g.setColor(Color.red);
        g.setFont(textFont);
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: "+fruitEaten, (BOARD_WIDTH - metrics.stringWidth("Score: "+fruitEaten))/2, g.getFont().getSize());
    }


    private void gameOver(Graphics g) {

        String gameOverMessage = "Game Over";
        g.setColor(Color.red);
        g.setFont(textFont);
        FontMetrics metrics = getFontMetrics(g.getFont()); 
        g.drawString(gameOverMessage,(BOARD_WIDTH - metrics.stringWidth(gameOverMessage))/2 , (BOARD_HEIGHT - metrics.stringWidth(gameOverMessage))/2);
        playAgainButton.setVisible(true);

    }

    public void checkCollision(){
         gameEnd =  (snakeXStartingPoint >= BOARD_WIDTH - UNIT_SIZE) ||
         (snakeXStartingPoint < UNIT_SIZE) ||
         (snakeYStartingPoint >= BOARD_HEIGHT - UNIT_SIZE) ||
         (snakeYStartingPoint < UNIT_SIZE);


        for(int i = 1; i < tailCounter ; i++) {
            if(snakeXStartingPoint == snakeX[i] && snakeYStartingPoint == snakeY[i]) gameEnd = true;
        }

        if(gameEnd){
            timer.stop();
        }
     
    }
    private void generateFruit() {

        int xUnits = BOARD_WIDTH / UNIT_SIZE;
        int yUnits = BOARD_HEIGHT / UNIT_SIZE;
        random = new Random();
        fruitX = random.nextInt(xUnits) * UNIT_SIZE;
        fruitY = random.nextInt(yUnits) * UNIT_SIZE;
    }

    public  void eatFruit(){
        generateFruit();
        fruitEaten++;
        tailCounter++;
    }

    private void drawFruit(Graphics g) {

        g.setColor(Color.red);
        g.fillOval(fruitX, fruitY, UNIT_SIZE, UNIT_SIZE);
    }

    // Draw units for a better view

    // private void drawMap(Graphics g) {
    //     for (int i = 0; i < BOARD_WIDTH; i++) {
    //         g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, BOARD_WIDTH);
    //     }

    //     for (int i = 0; i < BOARD_HEIGHT; i++) {
    //         g.drawLine(0, i * UNIT_SIZE, BOARD_WIDTH, i * UNIT_SIZE);
    //     }
    // }

    private void drawSnake(Graphics g) {
        g.setColor(Color.blue);
        for (int i = tailCounter; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
            g.fillRect(snakeX[i], snakeY[i], UNIT_SIZE, UNIT_SIZE);
        }
        snakeX[0] = snakeXStartingPoint;
        snakeY[0] = snakeYStartingPoint;
        g.setColor(Color.pink);
        g.fillRect(snakeX[0], snakeY[0], UNIT_SIZE, UNIT_SIZE);
        
    }
    
    

}
