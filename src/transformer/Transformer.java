package transformer;

import draw.DrawShape;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Transformer {

    protected DrawShape shape;
    protected Point previousPoint;

    public Transformer(DrawShape shape) {
        this.shape = shape;
    }

    public abstract void startTransform(Point point);

    public abstract void transform(Graphics2D graphics2D, Point currentPoint);

    public abstract void finishTransform(ArrayList<DrawShape> shapes);
}
