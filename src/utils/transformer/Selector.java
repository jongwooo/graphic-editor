package utils.transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import utils.draw.DrawShape;

public class Selector extends Transformer {

  private final DrawShape selection;

  public Selector(List<DrawShape> shapeList) {
    super(shapeList);
    selection = shapeList.get(0);
  }

  @Override
  public void setPoint(Point point) {
    selection.setStartPoint(point);
  }

  @Override
  public void transform(Graphics2D graphics2D, Point currentPoint) {
    graphics2D.setXORMode(graphics2D.getBackground());
    selection.draw(graphics2D);
    selection.setCurrentPoint(currentPoint);
    selection.draw(graphics2D);
  }

  public void finishTransform(List<DrawShape> shapes) {
    shapes.stream().filter(drawShape -> selection.getBounds().contains(drawShape.getBounds()))
        .forEach(selectedShape -> selectedShape.setSelected(true));
  }
}
