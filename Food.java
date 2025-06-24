package snakebyte;

import java.awt.*;
import java.util.Random;
import utils.List;

public class Food {
    public static final int SIZE = Snake.SQUARE;
    private double x, y;
    private final Color foodColor = Color.GREEN;
    private final Snake snake;

    public Food(Snake snake) {
        this.snake = snake;
        setLocation();
    }

    public void draw(Graphics g) {
        g.setColor(foodColor);
        g.fillRect((int)x, (int)y, SIZE, SIZE);
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void setLocation() {
        Random rand = new Random();
        boolean collision;
        List<Point> body = snake.getBody();

        do {
            collision = false;
            x = rand.nextInt(GamePanel.WIDTH / SIZE) * SIZE;
            y = rand.nextInt(GamePanel.HEIGHT / SIZE) * SIZE;

            for (int i = 0; i < body.size(); i++) {
                Point p = body.get(i);
                if (p.getX() == x && p.getY() == y) {
                    collision = true;
                    break;
                }
            }
        } while (collision);
    }
}
