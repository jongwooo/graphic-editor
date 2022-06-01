package util.draw;

import java.awt.Point;
import java.awt.Polygon;

public class DrawPolygon extends DrawShape {

  private static final long serialVersionUID = 1L;

  private final Polygon polygon;

  public DrawPolygon() {
    super(new Polygon());
    polygon = (Polygon) shape;
  }

  @Override
  public void setStartPoint(Point startPoint) {
    polygon.addPoint(startPoint.x, startPoint.y);
  }

  @Override
  public void setCurrentPoint(Point currentPoint) {
    polygon.xpoints[polygon.npoints - 1] = currentPoint.x;
    polygon.ypoints[polygon.npoints - 1] = currentPoint.y;
  }

  public void keepDraw(Point currentPoint) {
    polygon.addPoint(currentPoint.x, currentPoint.y);
  }

  @Override
  public DrawShape newShape() {
    return new DrawPolygon();
  }
}
