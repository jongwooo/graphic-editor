package constants.tool;

import utils.draw.shape.DrawEllipse;
import utils.draw.shape.DrawLine;
import utils.draw.shape.DrawPencil;
import utils.draw.shape.DrawPolygon;
import utils.draw.shape.DrawRectangle;
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
