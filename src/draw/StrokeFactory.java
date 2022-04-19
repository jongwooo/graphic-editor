package draw;

import java.awt.BasicStroke;
import java.util.HashMap;

public class StrokeFactory {

    private static final StrokeFactory STROKE_FACTORY = new StrokeFactory();

    private final HashMap<String, BasicStroke> cache = new HashMap<>();

    public static StrokeFactory getStrokeFactory() {
        return STROKE_FACTORY;
    }

    public BasicStroke getStroke(String strokeKey) {
        if (cache.containsKey(strokeKey)) {
            return cache.get(strokeKey);
        } else {
            BasicStroke newStroke;

            if (strokeKey.contains(":")) {
                String[] split = strokeKey.split(":");
                newStroke = new BasicStroke(Integer.parseInt(split[0]), BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10, new float[]{Integer.parseInt(split[1])}, 0);
            } else {
                newStroke = new BasicStroke(Integer.parseInt(strokeKey));
            }

            cache.put(strokeKey, newStroke);
            return newStroke;
        }
    }
}
