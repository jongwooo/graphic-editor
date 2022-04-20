package draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;

public abstract class DrawShape implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Shape shape;
    protected Point startPoint;
    private Color outlineColor, fillColor;
    private CustomStroke customStroke;
    private final StrokeFactory strokeFactory;

    public DrawShape(Shape shape) {
        this.shape = shape;
        strokeFactory = StrokeFactory.getStrokeFactory();
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(fillColor);
        graphics2D.fill(shape);
        graphics2D.setColor(outlineColor);
        graphics2D.setStroke(customStroke);
        graphics2D.draw(shape);
    }

    public void updateShapeAttributes(Color outlineColor, Color fillColor, int outlineSize,
            int dashSize) {
        this.outlineColor = outlineColor;
        this.fillColor = fillColor;
        customStroke = (dashSize == 0) ? strokeFactory.getStroke(outlineSize)
                : strokeFactory.getStroke(outlineSize, dashSize);
    }

    public boolean isContainCurrentPoint(Point currentPoint) {
        return shape.getBounds2D().contains(currentPoint);
    }

    public abstract void startDraw(Point startPoint);

    public abstract void setPoint(Point currentPoint);

    public abstract DrawShape newShape();
}
