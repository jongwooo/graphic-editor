package utils.draw.group;

import constants.anchor.Anchor;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import utils.draw.DrawShape;
import utils.anchor.AnchorList;
import utils.dto.ScaleDto;

public class DrawGroup extends DrawShape {

  private static final long serialVersionUID = 1L;

  private List<DrawShape> childShapes;
  private final AnchorList anchorList;
  private Anchor currentAnchor;

  public DrawGroup() {
    super(new Rectangle());
    childShapes = new ArrayList<>();
    anchorList = new AnchorList(new ArrayList<>());
    currentAnchor = null;
  }

  public void addChildShape(DrawShape childShape) {
    childShapes.add(0, childShape);
    shape = childShapes.size() == 1 ?
        childShape.getBounds() : shape.getBounds().createUnion(childShape.getBounds());
  }

  public void addAll(List<DrawShape> childShapes) {
    this.childShapes = childShapes;
  }

  public List<DrawShape> getChildShapes() {
    return childShapes;
  }

  @Override
  public void draw(Graphics2D graphics2D) {
    childShapes.forEach(shape -> shape.draw(graphics2D));

    if (selected) {
      anchorList.createAnchors(shape.getBounds());
      anchorList.draw(graphics2D);
    }
  }

  public boolean contains(Point currentPoint) {
    if (currentAnchor != null) {
      return true;
    }
    return childShapes.stream().anyMatch(childShape -> childShape.contains(currentPoint));
  }

  @Override
  public boolean isRotateAnchor() {
    return currentAnchor == Anchor.RR;
  }

  @Override
  public boolean isResizeAnchor() {
    return Arrays.stream(Anchor.values())
        .filter(drawAnchor -> drawAnchor != Anchor.RR)
        .anyMatch(drawAnchor -> drawAnchor == currentAnchor);
  }

  @Override
  public Anchor getCurrentAnchor() {
    return currentAnchor;
  }

  @Override
  public Anchor getCurrentAnchor(Point currentPoint) {
    List<Ellipse2D> anchors = anchorList.getAnchorList();
    currentAnchor = anchors.stream()
        .filter(anchor -> anchor.contains(currentPoint))
        .findFirst().map(anchor -> Anchor.values()[anchors.indexOf(anchor)])
        .orElse(null);
    return currentAnchor;
  }

  @Override
  public void setOutlineColor(Color outlineColor) {
    childShapes.forEach(childShape -> childShape.setOutlineColor(outlineColor));
  }

  @Override
  public void setFillColor(Color fillColor) {
    childShapes.forEach(childShape -> childShape.setFillColor(fillColor));
  }

  @Override
  public void setOutlineSize(int outlineSize) {
    childShapes.forEach(childShape -> {
      childShape.setOutlineSize(outlineSize);
      childShape.setStroke();
    });
  }

  @Override
  public void setDashSize(int dashSize) {
    childShapes.forEach(childShape -> {
      childShape.setDashSize(dashSize);
      childShape.setStroke();
    });
  }

  @Override
  public void move(double translateX, double translateY) {
    super.move(translateX, translateY);
    childShapes.forEach(childShape -> childShape.move(translateX, translateY));
  }

  @Override
  public void resize(ScaleDto dto) {
    super.resize(dto);
    childShapes.forEach(childShape -> childShape.resize(dto));
  }

  @Override
  public DrawShape clone() {
    List<DrawShape> cloneList = childShapes.stream()
        .map(DrawShape::clone)
        .collect(Collectors.toList());
    DrawGroup cloneShape = (DrawGroup) super.clone();
    cloneShape.addAll(cloneList);
    return cloneShape;
  }

  @Override
  public void rotate(double rotateAngle, Point rotatePoint) {
    super.rotate(rotateAngle, rotatePoint);
    childShapes.forEach(childShape -> childShape.rotate(rotateAngle, rotatePoint));
  }

  @Override
  public void setStartPoint(Point startPoint) {
    childShapes.forEach(childShape -> childShape.setStartPoint(startPoint));
  }

  @Override
  public void setCurrentPoint(Point currentPoint) {
    childShapes.forEach(childShape -> childShape.setCurrentPoint(currentPoint));
  }
}
