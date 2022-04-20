package draw;

import java.awt.BasicStroke;
import java.util.HashMap;

public class StrokeFactory {

    private static final StrokeFactory STROKE_FACTORY = new StrokeFactory();

    private final HashMap<String, BasicStroke> cache = new HashMap<>();

    public static StrokeFactory getStrokeFactory() {
        return STROKE_FACTORY;
    }

    public BasicStroke getStroke(int outlineSize) {
        String strokeKey = String.valueOf(outlineSize);

        if (cache.containsKey(strokeKey)) {
            return cache.get(strokeKey);
        } else {
            BasicStroke newStroke = new BasicStroke(outlineSize);
            cache.put(strokeKey, newStroke);
            return newStroke;
        }
    }

    public BasicStroke getStroke(int outlineSize, int dashSize) {
        String strokeKey = outlineSize + ":" + dashSize;

        if (cache.containsKey(strokeKey)) {
            return cache.get(strokeKey);
        } else {
            BasicStroke newStroke = new BasicStroke(outlineSize, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND, 10, new float[]{dashSize}, 0);
            cache.put(strokeKey, newStroke);
            return newStroke;
        }
    }
}
