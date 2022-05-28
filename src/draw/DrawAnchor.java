package draw;

import global.Constant;
import global.draw.Anchor;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DrawAnchor implements Serializable {

  private static final long serialVersionUID = 1L;

  private final List<Ellipse2D> anchors;

  public DrawAnchor() {
    anchors = new ArrayList<>();
  }

  public List<Ellipse2D> getAnchors() {
    return anchors;
  }

  public void createAnchors(Rectangle bound) {
    Arrays.stream(Anchor.values()).forEach(anchor -> anchors.add(anchor.getAnchor(bound)));
  }

  public void draw(Graphics2D graphics2D) {
    anchors.forEach(anchor -> {
      graphics2D.setColor(graphics2D.getBackground());
      graphics2D.fill(anchor);
      graphics2D.setColor(Constant.DEFAULT_OUTLINE_COLOR);
      graphics2D.setStroke(Constant.DEFAULT_STROKE);
      graphics2D.draw(anchor);
    });
  }
}
