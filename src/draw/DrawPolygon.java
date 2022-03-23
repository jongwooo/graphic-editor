package draw;

import java.awt.Point;
import java.awt.Polygon;

public class DrawPolygon extends DrawShape {
    public DrawPolygon() {
        super(new Polygon());
    }

    @Override
    public void startDraw(Point startPoint) {

    }

    @Override
    public void setCoordinate(Point currentPoint) {

    }

    @Override
    public DrawShape newShape() {
        return new DrawPolygon();
    }
}
