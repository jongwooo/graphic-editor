package global.tool;

public enum DrawingToolItem {
  clear("clearShapes"),
  outline("chooseOutlineColor"),
  fill("chooseFillColor");

  private final String methodName;

  DrawingToolItem(String methodName) {
    this.methodName = methodName;
  }

  public String getMethodName() {
    return methodName;
  }
}
