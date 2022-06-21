package utils.draw.shape;

import java.awt.Point;
import java.awt.geom.Ellipse2D;
import utils.draw.DrawShape;

public class DrawEllipse extends DrawShape {

  private static final long serialVersionUID = 1L;

  private final Ellipse2D ellipse2D;

  public DrawEllipse() {
    super(new Ellipse2D.Double());
    ellipse2D = (Ellipse2D) shape;
  }

  @Override
  public void setStartPoint(Point startPoint) {
    this.startPoint = startPoint;
  }

  @Override
  public void setCurrentPoint(Point currentPoint) {
    ellipse2D.setFrameFromDiagonal(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
  }
}
