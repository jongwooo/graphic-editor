package utils.draw;

import java.awt.Point;
import java.awt.geom.Line2D;

public class DrawLine extends DrawShape {

  private static final long serialVersionUID = 1L;

  private final Line2D line2D;

  public DrawLine() {
    super(new Line2D.Double());
    line2D = (Line2D) shape;
  }

  @Override
  public void setStartPoint(Point startPoint) {
    this.startPoint = startPoint;
  }

  @Override
  public void setCurrentPoint(Point currentPoint) {
    line2D.setLine(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
  }
}
