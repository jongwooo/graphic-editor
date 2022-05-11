package draw;

import java.awt.Point;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Float;

public class DrawPencil extends DrawShape {

    private static final long serialVersionUID = 1L;

    private final Path2D.Float path2D;

    public DrawPencil() {
        super(new Float());
        path2D = (Path2D.Float) shape;
    }

    @Override
    public void setStartPoint(Point startPoint) {
        path2D.moveTo(startPoint.x, startPoint.y);
    }

    @Override
    public void setCurrentPoint(Point currentPoint) {
        path2D.lineTo(currentPoint.x, currentPoint.y);
    }

    @Override
    public DrawShape newShape() {
        return new DrawPencil();
    }
}
