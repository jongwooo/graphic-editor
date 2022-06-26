package utils.transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import utils.draw.DrawShape;
import utils.dto.BoundDto;
import utils.dto.ScaleDto;

public class Resizer extends Transformer {

  private Point previousPoint;

  public Resizer(List<DrawShape> shapeList) {
    super(shapeList);
  }

  @Override
  public void setPoint(Point point) {
    previousPoint = point;
  }

  @Override
  public void transform(Graphics2D graphics2D, Point currentPoint) {
    graphics2D.setXORMode(graphics2D.getBackground());
    shapeList.stream()
        .filter(shape -> shape.getCurrentAnchor() != null)
        .findFirst().ifPresent(resizeShape -> {
          Rectangle resizeShapeBound = resizeShape.getBounds();
          double resizeShapeWidth = resizeShapeBound.getWidth();
          double resizeShapeHeight = resizeShapeBound.getHeight();

          if (resizeShapeWidth > 0 && resizeShapeHeight > 0) {
            shapeList.forEach(shape -> {
              Rectangle bound = shape.getBounds();
              ScaleDto dto = resizeShape.getCurrentAnchor().computeScale(
                  BoundDto.builder()
                      .boundX(bound.getMinX())
                      .boundY(bound.getMinY())
                      .boundWidth(bound.getWidth())
                      .boundHeight(bound.getHeight())
                      .xFactor((currentPoint.x - previousPoint.x) / resizeShapeWidth)
                      .yFactor((currentPoint.y - previousPoint.y) / resizeShapeHeight)
                      .build());

              shape.draw(graphics2D);
              shape.resize(dto);
              shape.draw(graphics2D);
            });
            previousPoint = currentPoint;
          }
        });
  }
}
