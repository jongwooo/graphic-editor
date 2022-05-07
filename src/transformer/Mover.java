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

    }

    @Override
    public void transform(Graphics2D graphics2D, Point currentPoint) {

    }

    @Override
    public void finishTransform(ArrayList<DrawShape> shapes) {

    }
}
