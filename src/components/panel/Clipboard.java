package components.panel;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import util.draw.DrawShape;

public class Clipboard {

  private static class InstanceHolder {

    private static final Clipboard INSTANCE = new Clipboard();
  }

  private final List<DrawShape> clipboard;

  private Clipboard() {
    clipboard = new ArrayList<>();
  }

  public static Clipboard getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public boolean isEmpty() {
    return clipboard.isEmpty();
  }

  public void add(DrawShape shape) {
    clipboard.clear();
    clipboard.add(shape);
  }

  public List<DrawShape> paste(Graphics2D graphics2D) {
    return clipboard.stream().map(shape -> move(graphics2D, shape))
        .collect(Collectors.toList());
  }

  public DrawShape move(Graphics2D graphics2D, DrawShape shape) {
    shape.move(8, 8);
    return shape.clone();
  }
}
