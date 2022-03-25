package draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

public abstract class DrawShape {
    protected Shape shape;
    protected Point startPoint;
    private Color outlineColor, fillColor;

    public DrawShape(Shape shape) {
        this.shape = shape;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(fillColor);
        graphics2D.fill(shape);
        graphics2D.setColor(outlineColor);
        graphics2D.draw(shape);
    }

    public abstract void startDraw(Point startPoint);
    public abstract void setCoordinate(Point currentPoint);
    public abstract DrawShape newShape();

    public void setOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
}
