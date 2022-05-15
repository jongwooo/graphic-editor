package draw;

import draw.stroke.CustomStroke;
import draw.stroke.StrokeFactory;
import global.Constant;
import global.draw.Anchor;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.Serializable;
import java.util.List;

public abstract class DrawShape implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Shape shape;
    protected Point startPoint;
    private boolean selected;
    private DrawAnchor anchor;
    private Anchor currentAnchor;
    private Color outlineColor, fillColor;
    private CustomStroke customStroke;
    private final StrokeFactory strokeFactory;
    private final AffineTransform affineTransform;

    public DrawShape(Shape shape) {
        this.shape = shape;
        selected = false;
        anchor = null;
        currentAnchor = null;
        affineTransform = new AffineTransform();
        outlineColor = Constant.DEFAULT_OUTLINE_COLOR;
        fillColor = Constant.DEFAULT_FILL_COLOR;
        customStroke = Constant.DEFAULT_STROKE;
        strokeFactory = StrokeFactory.getInstance();
    }

    public void draw(Graphics2D graphics2D) {
        if (!isDefaultFillColor() && !isUnfilledShape()) {
            graphics2D.setColor(fillColor);
            graphics2D.fill(shape);
        }
        graphics2D.setColor(outlineColor);
        graphics2D.setStroke(customStroke);
        graphics2D.draw(shape);
        createAnchors(graphics2D);
    }

    public boolean contains(Point currentPoint) {
        if (currentAnchor != null) {
            return true;
        }
        return shape.intersects(new Double(currentPoint.x, currentPoint.y, 2, 2));
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Rectangle getBound() {
        return shape.getBounds();
    }

    private void createAnchors(Graphics2D graphics2D) {
        anchor = selected ? new DrawAnchor() : null;
        if (anchor != null) {
            anchor.createAnchors(shape.getBounds());
            anchor.draw(graphics2D);
        }
    }

    public boolean isCurrentAnchor(Anchor anchor) {
        return currentAnchor == anchor;
    }

    public Anchor getCurrentAnchor() {
        return currentAnchor;
    }

    public Anchor getCurrentAnchor(Point currentPoint) {
        if (anchor != null) {
            List<Ellipse2D> anchors = anchor.getAnchors();
            currentAnchor = anchors.stream().filter(anchor -> anchor.contains(currentPoint))
                    .findFirst().map(anchor -> Anchor.values()[anchors.indexOf(anchor)])
                    .orElse(null);
            return currentAnchor;
        } else {
            return null;
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
        return shape instanceof Line2D.Double || shape instanceof Path2D.Float;
    }

    private boolean isDefaultFillColor() {
        return fillColor == Constant.DEFAULT_FILL_COLOR;
    }

    public Point getCenterPoint() {
        return new Point((int) shape.getBounds().getCenterX(),
                (int) shape.getBounds().getCenterY());
    }

    private Shape createTransformedShape(AffineTransform at, Shape pSrc) {
        return pSrc instanceof Path2D.Float ? new Path2D.Float(pSrc, at)
                : new Path2D.Double(pSrc, at);
    }

    public void move(Point point) {
        affineTransform.setToTranslation(point.x, point.y);
        shape = createTransformedShape(affineTransform, shape);
    }

    public void resize(double translateX, double translateY, double scaleX, double scaleY) {
        affineTransform.setToTranslation(translateX, translateY);
        affineTransform.scale(scaleX, scaleY);
        affineTransform.translate(-translateX, -translateY);
        shape = createTransformedShape(affineTransform, shape);
    }

    public void rotate(double rotateAngle, Point rotatePoint) {
        affineTransform.setToRotation(rotateAngle, rotatePoint.getX(), rotatePoint.getY());
        shape = createTransformedShape(affineTransform, shape);
    }

    public abstract void setStartPoint(Point startPoint);

    public abstract void setCurrentPoint(Point currentPoint);

    public abstract DrawShape newShape();
}
