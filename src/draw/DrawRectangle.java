package draw;

import java.awt.Point;
import java.awt.Rectangle;

public class DrawRectangle extends DrawShape {
    private static final long serialVersionUID = 1L;

    public DrawRectangle() {
        super(new Rectangle());
    }

    @Override
    public void startDraw(Point startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void setCoordinate(Point currentPoint) {
        Rectangle rectangle = (Rectangle) shape;
        rectangle.setFrameFromDiagonal(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
    }

    @Override
    public DrawShape newShape() {
        return new DrawRectangle();
    }
}
