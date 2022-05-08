package transformer;

import draw.DrawShape;
import java.awt.Graphics2D;
import java.awt.Point;

public class Rotator extends Transformer {

    private Point previousPoint, rotatePoint;

    public Rotator(DrawShape shape) {
        super(shape);
    }

    @Override
    public void setPoint(Point point) {
        this.previousPoint = point;
        rotatePoint = shape.getCenterPoint();
    }

    @Override
    public void transform(Graphics2D graphics2D, Point currentPoint) {
        graphics2D.setXORMode(graphics2D.getBackground());
        shape.draw(graphics2D);
        shape.rotate(computeAngle(rotatePoint, previousPoint, currentPoint), rotatePoint);
        shape.draw(graphics2D);
        previousPoint = currentPoint;
    }

    private double computeAngle(Point rotatePoint, Point previousPoint, Point nextPoint) {
        double startAngle, endAngle, computedAngle;
        startAngle = Math.toDegrees(
                Math.atan2(rotatePoint.x - previousPoint.x, rotatePoint.y - previousPoint.y));
        endAngle = Math.toDegrees(
                Math.atan2(rotatePoint.x - nextPoint.x, rotatePoint.y - nextPoint.y));
        computedAngle = Math.toRadians(
                (startAngle - endAngle) < 0 ? startAngle - endAngle + 360 : startAngle - endAngle);
        return computedAngle;
    }
}
