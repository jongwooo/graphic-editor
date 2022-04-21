package draw.stroke;

import java.awt.BasicStroke;
import java.io.Serializable;
import java.util.HashMap;

public class StrokeFactory implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final StrokeFactory STROKE_FACTORY = new StrokeFactory();

    private final HashMap<String, CustomStroke> cache = new HashMap<>();

    public static StrokeFactory getStrokeFactory() {
        return STROKE_FACTORY;
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
