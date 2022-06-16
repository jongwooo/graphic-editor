package utils.transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import utils.draw.DrawPolygon;
import utils.draw.DrawShape;

public class Drawer extends Transformer {

  protected final DrawShape shape;

  public Drawer(List<DrawShape> shapeList) {
    super(shapeList);
    shape = shapeList.get(0);
  }

  @Override
  public void setPoint(Point point) {
    shape.setStartPoint(point);
  }

  @Override
  public void transform(Graphics2D graphics2D, Point currentPoint) {
    graphics2D.setXORMode(graphics2D.getBackground());
    shape.draw(graphics2D);
    shape.setCurrentPoint(currentPoint);
    shape.draw(graphics2D);
  }

  public void keepTransform(Point currentPoint) {
    ((DrawPolygon) shape).keepDraw(currentPoint);
  }

  public void finishTransform(List<DrawShape> shapes) {
    shapes.add(shape);
  }
}
