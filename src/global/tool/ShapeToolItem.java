package global.tool;

import util.draw.DrawEllipse;
import util.draw.DrawLine;
import util.draw.DrawPencil;
import util.draw.DrawPolygon;
import util.draw.DrawRectangle;
import util.draw.DrawShape;

public enum ShapeToolItem {
  cursor(null),
  rectangle(new DrawRectangle()),
  ellipse(new DrawEllipse()),
  line(new DrawLine()),
  polygon(new DrawPolygon()),
  pencil(new DrawPencil());

  private final DrawShape shape;

  ShapeToolItem(DrawShape shape) {
    this.shape = shape;
  }

  public DrawShape newShape() {
    return shape;
  }
}
