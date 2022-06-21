package constants.tool;

import utils.draw.DrawEllipse;
import utils.draw.DrawLine;
import utils.draw.DrawPencil;
import utils.draw.DrawPolygon;
import utils.draw.DrawRectangle;
import utils.draw.selection.DrawSelection;
import utils.draw.DrawShape;

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
