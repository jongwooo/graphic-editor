package utils.transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import utils.draw.DrawShape;

public class Rotator extends Transformer {

  private Point previousPoint;

  public Rotator(List<DrawShape> shapeList) {
    super(shapeList);
  }

  @Override
  public void setPoint(Point point) {
    previousPoint = point;
  }

  @Override
  public void transform(Graphics2D graphics2D, Point currentPoint) {
    graphics2D.setXORMode(graphics2D.getBackground());
    shapeList.stream()
        .filter(shape -> shape.getCurrentAnchor() != null)
        .findFirst().ifPresent(rotateShape -> {
          double computedAngle =
              computeAngle(rotateShape.getCenterPoint(), previousPoint, currentPoint);

          shapeList.forEach(shape -> {
            shape.draw(graphics2D);
            shape.rotate(computedAngle, shape.getCenterPoint());
            shape.draw(graphics2D);
          });
          previousPoint = currentPoint;
        });
  }

  private double computeAngle(Point rotatePoint, Point previousPoint, Point currentPoint) {
    double startAngle = Math.toDegrees(
        Math.atan2(rotatePoint.x - previousPoint.x, rotatePoint.y - previousPoint.y));
    double endAngle = Math.toDegrees(
        Math.atan2(rotatePoint.x - currentPoint.x, rotatePoint.y - currentPoint.y));
    return Math.toRadians(
        (startAngle - endAngle) < 0 ? startAngle - endAngle + 360 : startAngle - endAngle);
  }
}
