package utils.draw.stroke;

import java.awt.BasicStroke;
import java.io.Serializable;
import java.util.HashMap;

public class StrokeFactory implements Serializable {

  private static final long serialVersionUID = 1L;

  private static class InstanceHolder {

    private static final StrokeFactory INSTANCE = new StrokeFactory();
  }

  private final HashMap<String, CustomStroke> cache;

  private StrokeFactory() {
    cache = new HashMap<>();
  }

  public static StrokeFactory getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public CustomStroke getStroke(int outlineSize) {
    String strokeKey = String.valueOf(outlineSize);

    if (cache.containsKey(strokeKey)) {
      return cache.get(strokeKey);
    } else {
      CustomStroke newStroke = new CustomStroke(outlineSize);
      cache.put(strokeKey, newStroke);
      return newStroke;
    }
  }

  public CustomStroke getStroke(int outlineSize, int dashSize) {
    if (dashSize == 0) {
      return getStroke(outlineSize);
    }

    String strokeKey = outlineSize + ":" + dashSize;

    if (cache.containsKey(strokeKey)) {
      return cache.get(strokeKey);
    } else {
      CustomStroke newStroke = new CustomStroke(outlineSize, BasicStroke.CAP_ROUND,
          BasicStroke.JOIN_ROUND, 10, new float[]{dashSize},
          0);
      cache.put(strokeKey, newStroke);
      return newStroke;
    }
  }
}
