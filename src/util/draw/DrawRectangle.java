package util.draw;

import java.awt.Point;
import java.awt.Rectangle;

public class DrawRectangle extends DrawShape {

  private static final long serialVersionUID = 1L;

  private final Rectangle rectangle;

  public DrawRectangle() {
    super(new Rectangle());
    rectangle = (Rectangle) shape;
  }

  @Override
  public void setStartPoint(Point startPoint) {
    this.startPoint = startPoint;
  }

  @Override
  public void setCurrentPoint(Point currentPoint) {
    rectangle.setFrameFromDiagonal(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
  }
}
