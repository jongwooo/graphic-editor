package draw;

import java.awt.Point;
import java.awt.geom.Line2D;

public class DrawLine extends DrawShape {
    private static final long serialVersionUID = 1L;

    public DrawLine() {
        super(new Line2D.Double());
    }

    @Override
    public void startDraw(Point startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void setCoordinate(Point currentPoint) {
        Line2D line2D = (Line2D) shape;
        line2D.setLine(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
    }

    @Override
    public DrawShape newShape() {
        return new DrawLine();
    }
}
