package transformer;

import draw.DrawPolygon;
import draw.DrawShape;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Drawer extends Transformer {

    public Drawer(DrawShape shape) {
        super(shape);
    }

    @Override
    public void startTransform(Point point) {
        shape.setStartPoint(point);
    }

    @Override
    public void transform(Graphics2D graphics2D, Point currentPoint) {
        graphics2D.setXORMode(graphics2D.getBackground());
        shape.draw(graphics2D);
        shape.setCurrentPoint(currentPoint);
        shape.draw(graphics2D);
    }

    public void keepTransform(Point currentPoint) {
        ((DrawPolygon) shape).keepDraw(currentPoint);
    }

    @Override
    public void finishTransform(ArrayList<DrawShape> shapes) {
        shapes.add(shape);
    }
}
