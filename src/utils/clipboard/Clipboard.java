package utils.clipboard;

import java.util.List;
import java.util.stream.Collectors;
import utils.draw.DrawShape;

public class Clipboard {

  private List<DrawShape> clipboard;

  public Clipboard(List<DrawShape> clipboard) {
    this.clipboard = clipboard;
  }

  public boolean isEmpty() {
    return clipboard.isEmpty();
  }

  public void add(DrawShape shape) {
    clipboard.add(shape);
  }

  public void addAll(List<DrawShape> selectedShapes) {
    clipboard.addAll(selectedShapes);
  }

  public void clear() {
    clipboard.clear();
  }

  public List<DrawShape> paste() {
    clipboard = clipboard.stream().map(this::move).collect(Collectors.toList());
    return clipboard;
  }

  public DrawShape move(DrawShape shape) {
    DrawShape clonedShape = shape.clone();
    clonedShape.move(8, 8);
    return clonedShape;
  }
}
