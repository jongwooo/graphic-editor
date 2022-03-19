package draw;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

public abstract class DrawShape {
    protected Shape shape;
    protected Point startPoint;

    public DrawShape(Shape shape) {
        this.shape = shape;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.draw(shape);
    }

    public abstract void startDraw(Point startPoint);
    public abstract void setCoordinate(Point currentPoint);
    public abstract DrawShape newShape();
}
