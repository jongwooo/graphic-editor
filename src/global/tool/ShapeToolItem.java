package global.tool;

import draw.DrawEllipse;
import draw.DrawLine;
import draw.DrawPolygon;
import draw.DrawRectangle;
import draw.DrawShape;

public enum ShapeToolItem {
    rectangle(new DrawRectangle()),
    ellipse(new DrawEllipse()),
    line(new DrawLine()),
    polygon(new DrawPolygon());

    private DrawShape currentShape;

    private ShapeToolItem(DrawShape shape) {
        this.currentShape = shape;
    }

    public DrawShape getShape() {
        return this.currentShape;
    }
}