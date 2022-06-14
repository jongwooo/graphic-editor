package utils.transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import utils.draw.DrawShape;

public abstract class Transformer {

  protected List<DrawShape> shapeList;

  public Transformer(List<DrawShape> shapeList) {
    this.shapeList = shapeList;
  }

  public abstract void setPoint(Point point);

  public abstract void transform(Graphics2D graphics2D, Point currentPoint);
}
