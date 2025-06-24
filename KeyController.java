package snakebyte;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter {
    private final Snake snake;

    public KeyController(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            snake.setDirection(Direction.UP);
            snake.setMoving(true);
        } else if (key == KeyEvent.VK_DOWN) {
            snake.setDirection(Direction.DOWN);
            snake.setMoving(true);
        } else if (key == KeyEvent.VK_LEFT) {
            snake.setDirection(Direction.LEFT);
            snake.setMoving(true);
        } else if (key == KeyEvent.VK_RIGHT) {
            snake.setDirection(Direction.RIGHT);
            snake.setMoving(true);
        }
    }
}