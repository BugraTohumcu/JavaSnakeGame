import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeMove implements ActionListener, KeyListener {


    private final char UP = 'W';
    private final char LEFT = 'A';
    private final char DOWN = 'S';
    private final char RIGHT = 'D';

    private GamePanel gamePanel;

    public SnakeMove(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (gamePanel.direction) {
            case RIGHT:
                gamePanel.lastDirection = RIGHT;
                gamePanel.snakeXStartingPoint += gamePanel.UNIT_SIZE;
                break;

            case LEFT:
                gamePanel.lastDirection  = LEFT;
                gamePanel.snakeXStartingPoint -= gamePanel.UNIT_SIZE;
                break;

            case UP:
                gamePanel.lastDirection = UP;
                gamePanel.snakeYStartingPoint -= gamePanel.UNIT_SIZE;
                break;

            case DOWN:
            gamePanel.lastDirection = DOWN;
                gamePanel.snakeYStartingPoint += gamePanel.UNIT_SIZE;
                break;

        }

        gamePanel.checkCollision();

        if(gamePanel.snakeXStartingPoint ==gamePanel.fruitX && gamePanel.snakeYStartingPoint ==gamePanel.fruitY){
            gamePanel.eatFruit();
        }

        gamePanel.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gamePanel.gameEnd) { // Check if the game is not over
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    if (gamePanel.lastDirection != DOWN)
                        gamePanel.direction = UP;
                    break;
                case KeyEvent.VK_S:
                    if (gamePanel.lastDirection != UP)
                        gamePanel.direction = DOWN;
                    break;
                case KeyEvent.VK_D:
                    if (gamePanel.lastDirection != LEFT)
                        gamePanel.direction = RIGHT;
                    break;
                case KeyEvent.VK_A:
                    if (gamePanel.lastDirection != RIGHT)
                        gamePanel.direction = LEFT;
                    break;
                default:
                    break;
            }
        }
    }
    


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
