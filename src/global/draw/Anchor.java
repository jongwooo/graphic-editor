package global.draw;

import global.Constant;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.util.function.Function;

public enum Anchor {
    NW(Constant.NW_CURSOR, bound -> new Point(bound.x, bound.y)),
    WW(Constant.WW_CURSOR, bound -> new Point(bound.x, bound.y + bound.height / 2)),
    SW(Constant.SW_CURSOR, bound -> new Point(bound.x, bound.y + bound.height)),
    SS(Constant.SS_CURSOR, bound -> new Point(bound.x + bound.width / 2, bound.y + bound.height)),
    SE(Constant.SE_CURSOR, bound -> new Point(bound.x + bound.width, bound.y + bound.height)),
    EE(Constant.EE_CURSOR, bound -> new Point(bound.x + bound.width, bound.y + bound.height / 2)),
    NE(Constant.NE_CURSOR, bound -> new Point(bound.x + bound.width, bound.y)),
    NN(Constant.NN_CURSOR, bound -> new Point(bound.x + bound.width / 2, bound.y)),
    RR(Constant.ROTATE_CURSOR,
            bound -> new Point(bound.x + bound.width / 2, bound.y - Constant.ROTATE_BAR_HEIGHT));

    private final Cursor cursorStyle;
    private final Function<Rectangle, Point> getAnchorPoint;

    Anchor(Cursor cursorStyle, Function<Rectangle, Point> getAnchorPoint) {
        this.cursorStyle = cursorStyle;
        this.getAnchorPoint = getAnchorPoint;
    }

    public Cursor getCursorStyle() {
        return cursorStyle;
    }

    public Ellipse2D getAnchor(Rectangle bound) {
        Ellipse2D anchor = new Double();
        Point anchorPoint = getAnchorPoint.apply(bound);
        int originX = anchorPoint.x - Constant.ANCHOR_WIDTH / 2;
        int originY = anchorPoint.y - Constant.ANCHOR_HEIGHT / 2;
        anchor.setFrame(originX, originY, Constant.ANCHOR_WIDTH, Constant.ANCHOR_HEIGHT);
        return anchor;
    }
}
