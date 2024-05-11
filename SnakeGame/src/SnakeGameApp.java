import javax.swing.*;
import javax.swing.SwingUtilities;

public class SnakeGameApp {

    private GamePanel gamePanel;
    private JFrame mainFrame;


    SnakeGameApp(){

        initializeGamePanel();


        initializeMainFrame();

    }

    private void initializeGamePanel() {
        gamePanel = new GamePanel();

    }

    private void initializeMainFrame(){

        mainFrame = new JFrame();
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Snake Game");
        mainFrame.add(gamePanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
    }


    
    public static void main(String[] args){

        SwingUtilities.invokeLater(()->{

            new SnakeGameApp();

        });
    }
}