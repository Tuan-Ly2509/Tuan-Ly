package snakebyte;

import utils.ArrayList;
import utils.List;
import java.awt.*;

public class Snake {
    public static final int SQUARE = 10;
    public static final int STARTING_LENGTH = 10;
    public static final int START_X_LOCATION = 300;
    public static final int START_Y_LOCATION = 300;

    private final Color bodyColor = Color.WHITE;
    private Direction direction = Direction.RIGHT;
    private boolean isMoving = false;
    private List<Point> snakeBody;

    public Snake() {
        snakeBody = make();
    }

    public void draw(Graphics g) {
        g.setColor(bodyColor);
        for (int i = 0; i < snakeBody.size(); i++) {
            Point p = snakeBody.get(i);
            g.drawRect((int) p.getX(), (int) p.getY(), SQUARE, SQUARE);
        }
    }

    public List<Point> getBody() {
        return snakeBody;
    }

    public Point getHeadLocation() {
        return snakeBody.get(0);
    }

    public Point getTailLocation() {
        return snakeBody.get(snakeBody.size() - 1);
    }

    public double getX() {
        return getHeadLocation().getX();
    }

    public double getY() {
        return getHeadLocation().getY();
    }

    public void grow() {
        Point tail = getTailLocation();
        snakeBody.add(new Point(tail.getX(), tail.getY()));
    }

    public List<Point> make() {
        List<Point> body = new ArrayList<>();
        for (int i = 0; i < STARTING_LENGTH; i++) {
            body.add(new Point(START_X_LOCATION - i * SQUARE, START_Y_LOCATION));
        }
        return body;
    }

    public void move() {
        if (!isMoving) return;

        Point head = getHeadLocation();
        double newX = head.getX() + direction.getX() * SQUARE;
        double newY = head.getY() + direction.getY() * SQUARE;

        // Create a new body list with updated head first
        List<Point> newBody = new ArrayList<>();
        newBody.add(new Point(newX, newY));

        // Copy all previous body points except the last one (simulate movement)
        for (int i = 0; i < snakeBody.size() - 1; i++) {
            newBody.add(snakeBody.get(i));
        }

        snakeBody = newBody;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void setDirection(Direction d) {
        if ((direction == Direction.RIGHT && d != Direction.LEFT) ||
            (direction == Direction.LEFT && d != Direction.RIGHT) ||
            (direction == Direction.UP && d != Direction.DOWN) ||
            (direction == Direction.DOWN && d != Direction.UP)) {
            direction = d;
        }
    }

    public Direction getDirection() {
        return direction;
    }
}