package draw;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class DrawEllipse extends DrawShape{
    private static final long serialVersionUID = 1L;

    public DrawEllipse() {
        super(new Ellipse2D.Double());
    }

    @Override
    public void startDraw(Point startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void endDraw(Point endPoint) {
        Ellipse2D ellipse2D = (Ellipse2D) shape;
        ellipse2D.setFrameFromDiagonal(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }
}
