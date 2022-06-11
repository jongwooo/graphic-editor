package util.transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import util.draw.DrawShape;

public class Selector extends Transformer {

  public Selector(DrawShape shape) {
    super(shape);
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

  public void finishTransform(List<DrawShape> shapes) {
    shapes.stream().filter(drawShape -> shape.getBounds().contains(drawShape.getBounds()))
        .forEach(selectedShape -> selectedShape.setSelected(true));
  }
}
