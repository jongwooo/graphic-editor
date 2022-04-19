package draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

public abstract class DrawShape {

    protected Shape shape;
    protected Point startPoint;
    private Color outlineColor, fillColor;
    private BasicStroke basicStroke;
    private final StrokeFactory strokeFactory;

    public DrawShape(Shape shape) {
        this.shape = shape;
        strokeFactory = StrokeFactory.getStrokeFactory();
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(fillColor);
        graphics2D.fill(shape);
        graphics2D.setColor(outlineColor);
        graphics2D.setStroke(basicStroke);
        graphics2D.draw(shape);
    }

    public void updateShapeAttributes(Color outlineColor, Color fillColor, int outlineSize,
            int dashSize) {
        this.outlineColor = outlineColor;
        this.fillColor = fillColor;
        basicStroke = (dashSize == 0) ? strokeFactory.getStroke(String.valueOf(outlineSize))
                : strokeFactory.getStroke(outlineSize + ":" + dashSize);
    }

    public boolean isContainCurrentPoint(Point currentPoint) {
        return shape.getBounds2D().contains(currentPoint);
    }

    public abstract void startDraw(Point startPoint);

    public abstract void setPoint(Point currentPoint);

    public abstract DrawShape newShape();
}
