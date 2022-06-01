package util.transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import util.draw.DrawShape;

public abstract class Transformer {

  protected DrawShape shape;
  protected AffineTransform affineTransform;

  public Transformer(DrawShape shape) {
    this.shape = shape;
    affineTransform = new AffineTransform();
  }

  public abstract void setPoint(Point point);

  public abstract void transform(Graphics2D graphics2D, Point currentPoint);
}
