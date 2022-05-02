package global.tool;

import draw.DrawEllipse;
import draw.DrawLine;
import draw.DrawPencil;
import draw.DrawPolygon;
import draw.DrawRectangle;
import draw.DrawShape;

public enum ShapeToolItem {
    cursor(null),
    rectangle(new DrawRectangle()),
    ellipse(new DrawEllipse()),
    line(new DrawLine()),
    polygon(new DrawPolygon()),
    pencil(new DrawPencil());

    private final DrawShape currentShape;

    ShapeToolItem(DrawShape currentShape) {
        this.currentShape = currentShape;
    }

    public DrawShape newShape() {
        return currentShape;
    }
}
