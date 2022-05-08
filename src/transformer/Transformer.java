package transformer;

import draw.DrawShape;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class Transformer {

    protected DrawShape shape;

    public Transformer(DrawShape shape) {
        this.shape = shape;
    }

    public abstract void startTransform(Point point);

    public abstract void transform(Graphics2D graphics2D, Point currentPoint);
}
