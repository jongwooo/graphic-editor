package draw;

import java.awt.Point;
import java.awt.geom.Line2D;

public class DrawLine extends DrawShape {
    private final Line2D line2D;

    public DrawLine() {
        super(new Line2D.Double());
        line2D = (Line2D) shape;
    }

    @Override
    public void startDraw(Point startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void setPoint(Point currentPoint) {
        line2D.setLine(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
    }

    @Override
    public DrawShape newShape() {
        return new DrawLine();
    }
}
