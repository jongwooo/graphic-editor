package draw;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class DrawEllipse extends DrawShape{
    public DrawEllipse() {
        super(new Ellipse2D.Double());
    }

    @Override
    public void startDraw(Point startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void setCoordinate(Point currentPoint) {
        Ellipse2D ellipse2D = (Ellipse2D) shape;
        ellipse2D.setFrameFromDiagonal(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
    }

    @Override
    public DrawShape newShape() {
        return new DrawEllipse();
    }
}
