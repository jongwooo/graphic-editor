package transformer.dto;

public class BoundDto {

    private final double boundX;
    private final double boundY;
    private final double boundWidth;
    private final double boundHeight;
    private final double ratioX;
    private final double ratioY;

    public BoundDto(BoundDtoBuilder builder) {
        this.boundX = builder.boundX;
        this.boundY = builder.boundY;
        this.boundWidth = builder.boundWidth;
        this.boundHeight = builder.boundHeight;
        this.ratioX = builder.ratioX;
        this.ratioY = builder.ratioY;
    }

    public double getX() {
        return boundX;
    }

    public double getY() {
        return boundY;
    }

    public double getWidth() {
        return boundWidth;
    }

    public double getHeight() {
        return boundHeight;
    }

    public double getRatioX() {
        return ratioX;
    }

    public double getRatioY() {
        return ratioY;
    }

    public static class BoundDtoBuilder {

        private double boundX;
        private double boundY;
        private double boundWidth;
        private double boundHeight;
        private double ratioX;
        private double ratioY;

        public BoundDtoBuilder boundX(double boundX) {
            this.boundX = boundX;
            return this;
        }

        public BoundDtoBuilder boundY(double boundY) {
            this.boundY = boundY;
            return this;
        }

        public BoundDtoBuilder boundWidth(double boundWidth) {
            this.boundWidth = boundWidth;
            return this;
        }

        public BoundDtoBuilder boundHeight(double boundHeight) {
            this.boundHeight = boundHeight;
            return this;
        }

        public BoundDtoBuilder ratioX(double ratioX) {
            this.ratioX = ratioX;
            return this;
        }

        public BoundDtoBuilder ratioY(double ratioY) {
            this.ratioY = ratioY;
            return this;
        }

        public BoundDto build() {
            return new BoundDto(this);
        }
    }
}
