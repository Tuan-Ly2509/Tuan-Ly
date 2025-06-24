package snakebyte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private final Snake snake;
    private final Food food;
    private final Timer timer;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        snake = new Snake();
        food = new Food(snake);

        KeyController controller = new KeyController(snake);
        addKeyListener(controller);
        setFocusable(true);
        requestFocusInWindow();

        timer = new Timer(100, this); // update every 100ms
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.draw(g);
        food.draw(g);
        drawScore(g);
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.CYAN);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Score: " + (snake.getBody().size() - Snake.STARTING_LENGTH), 10, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Point head = snake.getHeadLocation();

        // Wall collision detection
        if (head.getX() < 0 || head.getY() < 0 ||
            head.getX() >= WIDTH || head.getY() >= HEIGHT) {
            gameOver();
            return;
        }

        // Self-collision detection
        for (int i = 1; i < snake.getBody().size(); i++) {
            Point body = snake.getBody().get(i);
            if ((int) head.getX() == (int) body.getX() &&
                (int) head.getY() == (int) body.getY()) {
                gameOver();
                return;
            }
        }

        // Eating food
        if ((int) head.getX() == (int) food.getX() &&
            (int) head.getY() == (int) food.getY()) {
            snake.grow();
            food.setLocation();
        }

        snake.move();
        repaint();
    }

    private void gameOver() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Game Over! Final Score: " +
            (snake.getBody().size() - Snake.STARTING_LENGTH));
        System.exit(0);
    }
}