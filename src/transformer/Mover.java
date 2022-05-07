package transformer;

import draw.DrawShape;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Mover extends Transformer {

    public Mover(DrawShape shape) {
        super(shape);
    }

    @Override
    public void startTransform(Point point) {
        this.previousPoint = point;
    }

    @Override
    public void transform(Graphics2D graphics2D, Point currentPoint) {
        Point nextPoint = new Point(currentPoint.x - previousPoint.x,
                currentPoint.y - previousPoint.y);
        graphics2D.setXORMode(graphics2D.getBackground());
        shape.draw(graphics2D);
        shape.movePoint(nextPoint);
        shape.draw(graphics2D);
        previousPoint = currentPoint;
    }

    @Override
    public void finishTransform(ArrayList<DrawShape> shapes) {

    }
}
