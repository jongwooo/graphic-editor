package draw;

import java.awt.Point;
import java.awt.Rectangle;

public class DrawRectangle extends DrawShape {
    private final Rectangle rectangle;

    public DrawRectangle() {
        super(new Rectangle());
        rectangle = (Rectangle) shape;
    }

    @Override
    public void startDraw(Point startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void setPoint(Point currentPoint) {
        rectangle.setFrameFromDiagonal(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
    }

    @Override
    public DrawShape newShape() {
        return new DrawRectangle();
    }
}
