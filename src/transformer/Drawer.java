package transformer;

import draw.DrawPolygon;
import draw.DrawShape;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Drawer extends Transformer {

    public Drawer(DrawShape currentShape) {
        super(currentShape);
    }

    @Override
    public void startTransform(Point startPoint) {
        currentShape.setStartPoint(startPoint);
    }

    @Override
    public void transform(Graphics2D graphics2D, Point currentPoint) {
        graphics2D.setXORMode(graphics2D.getBackground());
        currentShape.draw(graphics2D);
        currentShape.setCurrentPoint(currentPoint);
        currentShape.draw(graphics2D);
    }

    public void keepTransform(Point currentPoint) {
        ((DrawPolygon) currentShape).keepDraw(currentPoint);
    }

    @Override
    public void finishTransform(ArrayList<DrawShape> shapes) {
        shapes.add(currentShape);
    }
}
