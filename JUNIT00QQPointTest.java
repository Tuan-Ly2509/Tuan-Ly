package junits;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JUNIT00QQPointTest {

    @Test
    public void testConstructorAndGetters() {
        Point p = new Point(3.0, 4.0);
        assertEquals(3.0, p.getX());
        assertEquals(4.0, p.getY());
    }

    @Test
    public void testSetters() {
        Point p = new Point();
        p.setX(1.5);
        p.setY(2.5);
        assertEquals(1.5, p.getX());
        assertEquals(2.5, p.getY());
    }

    @Test
    public void testDistanceTo() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        assertEquals(5.0, p1.distanceTo(p2), 0.0001);
    }

    @Test
    public void testToString() {
        Point p = new Point(2.5, 3.5);
        assertEquals("(2.5, 3.5)", p.toString());
    }
}
