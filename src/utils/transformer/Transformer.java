package utils.transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import utils.draw.DrawShape;

public abstract class Transformer {

  protected DrawShape shape;

  public Transformer(DrawShape shape) {
    this.shape = shape;
  }

  public abstract void setPoint(Point point);

  public abstract void transform(Graphics2D graphics2D, Point currentPoint);
}
