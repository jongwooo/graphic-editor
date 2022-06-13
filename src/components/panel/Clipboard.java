package components.panel;

import java.util.List;
import java.util.stream.Collectors;
import utils.draw.DrawShape;

public class Clipboard {

  private final List<DrawShape> clipboard;

  public Clipboard(List<DrawShape> clipboard) {
    this.clipboard = clipboard;
  }

  public boolean isEmpty() {
    return clipboard.isEmpty();
  }

  public void add(DrawShape shape) {
    clipboard.add(shape);
  }

  public void clear() {
    clipboard.clear();
  }

  public List<DrawShape> paste() {
    return clipboard.stream().map(this::move)
        .collect(Collectors.toList());
  }

  public DrawShape move(DrawShape shape) {
    shape.move(8, 8);
    return shape.clone();
  }
}
