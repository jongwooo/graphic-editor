package transformer;

import draw.DrawShape;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Rotator extends Transformer {

    public Rotator(DrawShape currentShape) {
        super(currentShape);
    }

    @Override
    public void startTransform(Point startPoint) {

    }

    @Override
    public void transform(Graphics2D graphics2D, Point currentPoint) {

    }

    @Override
    public void finishTransform(ArrayList<DrawShape> shapes) {

    }
}
