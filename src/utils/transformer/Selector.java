package utils.transformer;

import java.util.List;
import utils.draw.DrawShape;

public class Selector extends Drawer {

  public Selector(List<DrawShape> shapeList) {
    super(shapeList);
  }

  @Override
  public void finishTransform(List<DrawShape> shapes) {
    shapes.stream().filter(drawShape -> shape.getBounds().contains(drawShape.getBounds()))
        .forEach(selectedShape -> selectedShape.setSelected(true));
  }
}
