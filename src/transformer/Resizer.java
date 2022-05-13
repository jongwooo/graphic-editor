package transformer;

import draw.DrawShape;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import transformer.dto.BoundDto.BoundDtoBuilder;
import transformer.dto.ScaleDto;

public class Resizer extends Transformer {

    private Point previousPoint;

    public Resizer(DrawShape shape) {
        super(shape);
    }

    @Override
    public void setPoint(Point point) {
        previousPoint = point;
    }

    @Override
    public void transform(Graphics2D graphics2D, Point currentPoint) {
        Rectangle bound = shape.getBound();
        ScaleDto dto = shape.getCurrentAnchor()
                .getScale(
                        new BoundDtoBuilder()
                                .boundX(bound.getMinX())
                                .boundY(bound.getMinY())
                                .boundWidth(bound.getWidth())
                                .boundHeight(bound.getHeight())
                                .xFactor((currentPoint.x - previousPoint.x) / bound.getWidth())
                                .yFactor((currentPoint.y - previousPoint.y) / bound.getHeight())
                                .build());
        graphics2D.setXORMode(graphics2D.getBackground());
        shape.draw(graphics2D);
        shape.resize(dto.getTranslateX(), dto.getTranslateY(), dto.getScaleX(), dto.getScaleY());
        shape.draw(graphics2D);
        previousPoint = currentPoint;
    }
}
