package utils.transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import utils.draw.DrawShape;

public class Mover extends Transformer {

  private Point previousPoint;

  public Mover(List<DrawShape> shapeList) {
    super(shapeList);
  }

  @Override
  public void setPoint(Point point) {
    previousPoint = point;
  }

  @Override
  public void transform(Graphics2D graphics2D, Point currentPoint) {
    graphics2D.setXORMode(graphics2D.getBackground());
    shapeList.forEach(shape -> {
      shape.draw(graphics2D);
      shape.move(currentPoint.x - previousPoint.x, currentPoint.y - previousPoint.y);
      shape.draw(graphics2D);
    });
    previousPoint = currentPoint;
  }
}
