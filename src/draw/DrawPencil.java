package draw;

import java.awt.Point;
import java.awt.geom.Path2D;

public class DrawPencil extends DrawShape {

    private static final long serialVersionUID = 1L;

    private final Path2D path2D;

    public DrawPencil() {
        super(new Path2D.Double());
        path2D = (Path2D) shape;
    }

    @Override
    public void startDraw(Point startPoint) {
        path2D.moveTo(startPoint.x, startPoint.y);
    }

    @Override
    public void setPoint(Point currentPoint) {
        path2D.lineTo(currentPoint.x, currentPoint.y);
    }

    @Override
    public DrawShape newShape() {
        return new DrawPencil();
    }
}
