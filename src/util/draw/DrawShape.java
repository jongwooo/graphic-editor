package util.draw;

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
import util.draw.stroke.CustomStroke;
import util.draw.stroke.StrokeFactory;

public abstract class DrawShape implements Cloneable, Serializable {

  private static final long serialVersionUID = 1L;

  protected Shape shape;
  protected Point startPoint;
  private final DrawAnchor anchor;
  private Anchor currentAnchor;
  private boolean selected;
  private Color outlineColor, fillColor;
  private int outlineSize, dashSize;
  private CustomStroke customStroke;
  private final StrokeFactory strokeFactory;

  public DrawShape(Shape shape) {
    this.shape = shape;
    anchor = new DrawAnchor();
    currentAnchor = null;
    selected = false;
    outlineColor = Constant.DEFAULT_OUTLINE_COLOR;
    fillColor = Constant.DEFAULT_FILL_COLOR;
    outlineSize = Constant.DEFAULT_OUTLINE_SIZE;
    dashSize = Constant.DEFAULT_DASH_SIZE;
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

    if (selected) {
      anchor.createAnchors(shape.getBounds());
      anchor.draw(graphics2D);
    }
  }

  public boolean contains(Point currentPoint) {
    if (currentAnchor != null) {
      return true;
    }
    return shape.intersects(new Double(currentPoint.x, currentPoint.y, 2, 2));
  }

  public Rectangle getBounds() {
    return shape.getBounds();
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

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  public DrawShape setOutlineColor(Color outlineColor) {
    this.outlineColor = outlineColor;
    return this;
  }

  public DrawShape setFillColor(Color fillColor) {
    this.fillColor = fillColor;
    return this;
  }

  public int getOutlineSize() {
    return outlineSize;
  }

  public DrawShape setOutlineSize(int outlineSize) {
    this.outlineSize = outlineSize;
    return this;
  }

  public int getDashSize() {
    return dashSize;
  }

  public DrawShape setDashSize(int dashSize) {
    this.dashSize = dashSize;
    return this;
  }

  public void setStroke() {
    customStroke = strokeFactory.getStroke(outlineSize, dashSize);
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

  public void transform(AffineTransform affineTransform) {
    shape = shape instanceof Path2D.Float ? new Path2D.Float(shape, affineTransform)
        : new Path2D.Double(shape, affineTransform);
  }

  @Override
  public DrawShape clone() {
    try {
      return (DrawShape) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

  public abstract void setStartPoint(Point startPoint);

  public abstract void setCurrentPoint(Point currentPoint);

  public abstract DrawShape newShape();
}
