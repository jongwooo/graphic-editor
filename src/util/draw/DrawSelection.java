package util.draw;

import java.awt.Point;
import java.awt.Rectangle;

public class DrawSelection extends DrawShape {

  private final Rectangle selection;

  public DrawSelection() {
    super(new Rectangle());
    selection = (Rectangle) shape;
  }

  @Override
  public void setStartPoint(Point startPoint) {
    this.startPoint = startPoint;
  }

  @Override
  public void setCurrentPoint(Point currentPoint) {
    selection.setFrameFromDiagonal(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
  }
}
