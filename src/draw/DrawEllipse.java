package draw;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class DrawEllipse extends DrawShape{
    private Ellipse2D ellipse2D;

    public DrawEllipse() {
        super(new Ellipse2D.Double());
        ellipse2D = (Ellipse2D) shape;
    }

    @Override
    public void startDraw(Point startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void setPoint(Point currentPoint) {
        ellipse2D.setFrameFromDiagonal(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
    }

    @Override
    public DrawShape newShape() {
        return new DrawEllipse();
    }
}
