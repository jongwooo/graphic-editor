package draw;

import java.awt.Point;
import java.awt.geom.GeneralPath;

public class DrawPencil extends DrawShape {

    private static final long serialVersionUID = 1L;

    private final GeneralPath generalPath;

    public DrawPencil() {
        super(new GeneralPath());
        generalPath = (GeneralPath) shape;
    }

    @Override
    public void setStartPoint(Point startPoint) {
        generalPath.moveTo(startPoint.x, startPoint.y);
    }

    @Override
    public void setCurrentPoint(Point currentPoint) {
        generalPath.lineTo(currentPoint.x, currentPoint.y);
    }

    @Override
    public DrawShape newShape() {
        return new DrawPencil();
    }
}
