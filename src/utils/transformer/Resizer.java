package utils.transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import utils.draw.DrawShape;
import utils.transformer.dto.BoundDto;
import utils.transformer.dto.ScaleDto;

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
          Rectangle bound = resizeShape.getBounds();
          if (bound.getWidth() > 0 && bound.getHeight() > 0) {
            ScaleDto dto = resizeShape.getCurrentAnchor().computeScale(
                BoundDto.builder()
                    .boundX(bound.getMinX())
                    .boundY(bound.getMinY())
                    .boundWidth(bound.getWidth())
                    .boundHeight(bound.getHeight())
                    .xFactor((currentPoint.x - previousPoint.x) / bound.getWidth())
                    .yFactor((currentPoint.y - previousPoint.y) / bound.getHeight())
                    .build());

            shapeList.forEach(shape -> {
              shape.draw(graphics2D);
              shape.resize(dto);
              shape.draw(graphics2D);
            });
            previousPoint = currentPoint;
          }
        });
  }
}
