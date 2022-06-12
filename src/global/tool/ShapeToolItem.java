package global.tool;

import util.draw.DrawEllipse;
import util.draw.DrawLine;
import util.draw.DrawPencil;
import util.draw.DrawPolygon;
import util.draw.DrawRectangle;
import util.draw.DrawSelection;
import util.draw.DrawShape;

public enum ShapeToolItem {
  cursor(DrawSelection.class),
  rectangle(DrawRectangle.class),
  ellipse(DrawEllipse.class),
  line(DrawLine.class),
  polygon(DrawPolygon.class),
  pencil(DrawPencil.class);

  private final Class<? extends DrawShape> shapeClass;

  ShapeToolItem(Class<? extends DrawShape> shapeClass) {
    this.shapeClass = shapeClass;
  }

  public Class<? extends DrawShape> getShapeClass() {
    return shapeClass;
  }
}
