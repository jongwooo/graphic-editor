package draw;

import draw.stroke.CustomStroke;
import draw.stroke.StrokeFactory;
import global.Constant;
import global.draw.Anchor;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class DrawShape implements Serializable {

    private static final long serialVersionUID = 1L;

    protected boolean selected;
    protected Shape shape;
    protected Point startPoint;
    protected DrawAnchor anchor;
    private final ArrayList<Ellipse2D> anchors;
    private Color outlineColor, fillColor;
    private CustomStroke customStroke;
    private final StrokeFactory strokeFactory;

    public DrawShape(Shape shape) {
        this.shape = shape;
        selected = false;
        anchor = new DrawAnchor();
        anchors = anchor.getAnchors();
        outlineColor = Constant.DEFAULT_OUTLINE_COLOR;
        fillColor = Constant.DEFAULT_FILL_COLOR;
        customStroke = Constant.DEFAULT_STROKE;
        strokeFactory = StrokeFactory.createStrokeFactory();
    }

    public void draw(Graphics2D graphics2D) {
        if (!isDefaultFillColor() && !isUnfilledShape()) {
            graphics2D.setColor(fillColor);
            graphics2D.fill(shape);
        }
        graphics2D.setColor(outlineColor);
        graphics2D.setStroke(customStroke);
        graphics2D.draw(shape);

        if (selected) {
            anchor.draw(graphics2D);
        }
    }

    public void setOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setStroke(int outlineSize, int dashSize) {
        customStroke = (dashSize == 0) ? strokeFactory.getStroke(outlineSize)
                : strokeFactory.getStroke(outlineSize, dashSize);
    }

    private boolean isUnfilledShape() {
        return shape instanceof Line2D.Double || shape instanceof Path2D.Double;
    }

    private boolean isDefaultFillColor() {
        return fillColor == Constant.DEFAULT_FILL_COLOR;
    }

    public boolean isContainCurrentPoint(Point currentPoint) {
        if (getCurrentAnchor(currentPoint) != null) {
            return true;
        }
        return shape.intersects(new Double(currentPoint.x, currentPoint.y, 2, 2));
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            anchor.createAnchor(shape.getBounds());
        }
    }

    public Anchor getCurrentAnchor(Point currentPoint) {
        return anchors.stream().filter(anchor -> anchor.contains(currentPoint)).findFirst()
                .map(anchor -> Anchor.values()[anchors.indexOf(anchor)]).orElse(null);
    }

    public abstract void setStartPoint(Point startPoint);

    public abstract void setCurrentPoint(Point currentPoint);

    public abstract DrawShape newShape();
}
