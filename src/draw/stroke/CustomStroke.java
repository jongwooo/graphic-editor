package draw.stroke;

import java.awt.BasicStroke;
import java.io.Serializable;

public class CustomStroke extends BasicStroke implements Serializable {

    private static final long serialVersionUID = 1L;

    public CustomStroke(int outlineSize) {
        super(outlineSize);
    }

    public CustomStroke(int outlineSize, int dashSize) {
        super(outlineSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, new float[]{dashSize},
                0);
    }
}
