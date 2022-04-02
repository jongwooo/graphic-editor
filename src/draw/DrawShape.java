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
    private BasicStroke currentStroke;
    private int outlineSize, dashSize;

    public DrawShape(Shape shape) {
        this.shape = shape;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(fillColor);
        graphics2D.setStroke(currentStroke);
        graphics2D.fill(shape);
        graphics2D.setColor(outlineColor);
        graphics2D.draw(shape);
    }

    public void setOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setOutlineSize(int outlineSize) {
        this.outlineSize = outlineSize;
        setCurrentStroke();
    }

    public void setDashSize(int dashSize) {
        this.dashSize = dashSize;
        setCurrentStroke();
    }

    private void setCurrentStroke() {
        currentStroke = (dashSize == 0) ?
                new BasicStroke(outlineSize) :
                new BasicStroke(outlineSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, new float[]{dashSize}, 0);
    }

    public abstract void startDraw(Point startPoint);
    public abstract void setCoordinate(Point currentPoint);
    public abstract DrawShape newShape();
}
