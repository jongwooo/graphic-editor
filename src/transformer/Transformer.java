package transformer;

import draw.DrawShape;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Transformer {

    protected DrawShape currentShape;

    public Transformer(DrawShape currentShape) {
        this.currentShape = currentShape;
    }

    public abstract void startTransform(Point startPoint);
    public abstract void transform(Graphics2D graphics2D, Point currentPoint);
    public abstract void finishTransform(ArrayList<DrawShape> shapes);
}
