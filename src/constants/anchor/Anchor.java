package constants.anchor;

import constants.Constant;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.util.function.Function;
import utils.transformer.dto.BoundDto;
import utils.transformer.dto.ScaleDto;

public enum Anchor {
  NW(Constant.NW_CURSOR, bound -> new Point(bound.x, bound.y),
      dto -> ScaleDto.builder()
          .translateX(dto.getBoundX() + dto.getBoundWidth())
          .translateY(dto.getBoundY() + dto.getBoundHeight())
          .scaleX(1 - dto.getXFactor())
          .scaleY(1 - dto.getYFactor())
          .build()),
  WW(Constant.WW_CURSOR, bound -> new Point(bound.x, bound.y + bound.height / 2),
      dto -> ScaleDto.builder()
          .translateX(dto.getBoundX() + dto.getBoundWidth())
          .translateY(0)
          .scaleX(1 - dto.getXFactor())
          .scaleY(1)
          .build()),
  SW(Constant.SW_CURSOR, bound -> new Point(bound.x, bound.y + bound.height),
      dto -> ScaleDto.builder()
          .translateX(dto.getBoundX() + dto.getBoundWidth())
          .translateY(dto.getBoundY())
          .scaleX(1 - dto.getXFactor())
          .scaleY(1 + dto.getYFactor())
          .build()),
  SS(Constant.SS_CURSOR, bound -> new Point(bound.x + bound.width / 2, bound.y + bound.height),
      dto -> ScaleDto.builder()
          .translateX(0)
          .translateY(dto.getBoundY())
          .scaleX(1)
          .scaleY(1 + dto.getYFactor())
          .build()),
  SE(Constant.SE_CURSOR, bound -> new Point(bound.x + bound.width, bound.y + bound.height),
      dto -> ScaleDto.builder()
          .translateX(dto.getBoundX())
          .translateY(dto.getBoundY())
          .scaleX(1 + dto.getXFactor())
          .scaleY(1 + dto.getYFactor())
          .build()),
  EE(Constant.EE_CURSOR, bound -> new Point(bound.x + bound.width, bound.y + bound.height / 2),
      dto -> ScaleDto.builder()
          .translateX(dto.getBoundX())
          .translateY(0)
          .scaleX(1 + dto.getXFactor())
          .scaleY(1)
          .build()),
  NE(Constant.NE_CURSOR, bound -> new Point(bound.x + bound.width, bound.y),
      dto -> ScaleDto.builder()
          .translateX(dto.getBoundX())
          .translateY(dto.getBoundY() + dto.getBoundHeight())
          .scaleX(1 + dto.getXFactor())
          .scaleY(1 - dto.getYFactor())
          .build()),
  NN(Constant.NN_CURSOR, bound -> new Point(bound.x + bound.width / 2, bound.y),
      dto -> ScaleDto.builder()
          .translateX(0)
          .translateY(dto.getBoundY() + dto.getBoundHeight())
          .scaleX(1)
          .scaleY(1 - dto.getYFactor())
          .build()),
  RR(Constant.ROTATE_CURSOR,
      bound -> new Point(bound.x + bound.width / 2, bound.y - Constant.ROTATE_BAR_HEIGHT));

  private final Cursor cursorStyle;
  private final Function<Rectangle, Point> getAnchorPoint;
  private Function<BoundDto, ScaleDto> computeScale;

  Anchor(Cursor cursorStyle, Function<Rectangle, Point> getAnchorPoint) {
    this.cursorStyle = cursorStyle;
    this.getAnchorPoint = getAnchorPoint;
  }

  Anchor(Cursor cursorStyle, Function<Rectangle, Point> getAnchorPoint,
      Function<BoundDto, ScaleDto> computeScale) {
    this(cursorStyle, getAnchorPoint);
    this.computeScale = computeScale;
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

  public ScaleDto computeScale(BoundDto dto) {
    return computeScale.apply(dto);
  }
}
