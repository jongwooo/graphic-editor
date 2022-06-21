package utils.draw;

import constants.Constant;
import constants.draw.Anchor;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import utils.anchor.AnchorList;
import utils.draw.shape.DrawLine;
import utils.draw.shape.DrawPencil;
import utils.stroke.CustomStroke;
import utils.stroke.StrokeFactory;
import utils.transformer.dto.ScaleDto;

public abstract class DrawShape implements Cloneable, Serializable {

  private static final long serialVersionUID = 1L;

  protected Shape shape;
  protected Point startPoint;
  private final AffineTransform affineTransform;
  private final AnchorList anchorList;
  private Anchor currentAnchor;
  private boolean selected;
  private Color outlineColor, fillColor;
  private int outlineSize, dashSize;
  private CustomStroke customStroke;
  private final StrokeFactory strokeFactory;

  public DrawShape(Shape shape) {
    this.shape = shape;
    affineTransform = new AffineTransform();
    anchorList = new AnchorList();
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
      anchorList.createAnchors(shape.getBounds());
      anchorList.draw(graphics2D);
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

  public boolean isRotateAnchor() {
    return currentAnchor == Anchor.RR;
  }

  public boolean isResizeAnchor() {
    return Arrays.stream(Anchor.values())
        .filter(drawAnchor -> drawAnchor != Anchor.RR)
        .anyMatch(drawAnchor -> drawAnchor == currentAnchor);
  }

  public Anchor getCurrentAnchor() {
    return currentAnchor;
  }

  public Anchor getCurrentAnchor(Point currentPoint) {
    List<Ellipse2D> anchors = anchorList.getAnchorList();
    currentAnchor = anchors.stream()
        .filter(anchor -> anchor.contains(currentPoint))
        .findFirst().map(anchor -> Anchor.values()[anchors.indexOf(anchor)])
        .orElse(null);
    return currentAnchor;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  public void setOutlineColor(Color outlineColor) {
    this.outlineColor = outlineColor;
  }

  public void setFillColor(Color fillColor) {
    this.fillColor = fillColor;
  }

  public int getOutlineSize() {
    return outlineSize;
  }

  public void setOutlineSize(int outlineSize) {
    this.outlineSize = outlineSize;
  }

  public int getDashSize() {
    return dashSize;
  }

  public void setDashSize(int dashSize) {
    this.dashSize = dashSize;
  }

  public void setStroke() {
    customStroke = strokeFactory.getStroke(outlineSize, dashSize);
  }

  private boolean isUnfilledShape() {
    return this instanceof DrawLine || this instanceof DrawPencil;
  }

  private boolean isDefaultFillColor() {
    return fillColor == Constant.DEFAULT_FILL_COLOR;
  }

  public Point getCenterPoint() {
    return new Point((int) shape.getBounds().getCenterX(),
        (int) shape.getBounds().getCenterY());
  }

  private Shape createTransformedShape(Shape pSrc, AffineTransform at) {
    return pSrc instanceof Path2D.Float ? new Path2D.Float(pSrc, at)
        : new Path2D.Double(pSrc, at);
  }

  public void move(double translateX, double translateY) {
    affineTransform.setToTranslation(translateX, translateY);
    shape = createTransformedShape(shape, affineTransform);
  }

  public void resize(ScaleDto dto) {
    affineTransform.setToTranslation(dto.getTranslateX(), dto.getTranslateY());
    affineTransform.scale(dto.getScaleX(), dto.getScaleY());
    affineTransform.translate(-dto.getTranslateX(), -dto.getTranslateY());
    shape = createTransformedShape(shape, affineTransform);
  }

  public void rotate(double rotateAngle, Point rotatePoint) {
    affineTransform.setToRotation(rotateAngle, rotatePoint.getX(), rotatePoint.getY());
    shape = createTransformedShape(shape, affineTransform);
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
}
