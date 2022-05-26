package transformer.dto;

public class ScaleDto {

  private final double translateX;
  private final double translateY;
  private final double scaleX;
  private final double scaleY;

  public ScaleDto(ScaleDtoBuilder builder) {
    this.translateX = builder.translateX;
    this.translateY = builder.translateY;
    this.scaleX = builder.scaleX;
    this.scaleY = builder.scaleY;
  }

  public double getTranslateX() {
    return translateX;
  }

  public double getTranslateY() {
    return translateY;
  }

  public double getScaleX() {
    return scaleX;
  }

  public double getScaleY() {
    return scaleY;
  }

  public static class ScaleDtoBuilder {

    private double translateX;
    private double translateY;
    private double scaleX;
    private double scaleY;

    public ScaleDtoBuilder translateX(double translateX) {
      this.translateX = translateX;
      return this;
    }

    public ScaleDtoBuilder translateY(double translateY) {
      this.translateY = translateY;
      return this;
    }

    public ScaleDtoBuilder scaleX(double scaleX) {
      this.scaleX = scaleX;
      return this;
    }

    public ScaleDtoBuilder scaleY(double scaleY) {
      this.scaleY = scaleY;
      return this;
    }

    public ScaleDto build() {
      return new ScaleDto(this);
    }
  }
}
