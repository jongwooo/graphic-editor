package utils.anchor;

import constants.Constant;
import constants.anchor.Anchor;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnchorList implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<Ellipse2D> anchorList;

  public AnchorList(List<Ellipse2D> anchorList) {
    this.anchorList = anchorList;
  }

  public List<Ellipse2D> getAnchorList() {
    return anchorList;
  }

  public void createAnchors(Rectangle bound) {
    anchorList = Arrays.stream(Anchor.values())
        .map(anchor -> anchor.getAnchor(bound))
        .collect(Collectors.toList());
  }

  public void draw(Graphics2D graphics2D) {
    anchorList.forEach(anchor -> {
      graphics2D.setColor(graphics2D.getBackground());
      graphics2D.fill(anchor);
      graphics2D.setColor(Constant.DEFAULT_OUTLINE_COLOR);
      graphics2D.setStroke(Constant.DEFAULT_STROKE);
      graphics2D.draw(anchor);
    });
  }
}
