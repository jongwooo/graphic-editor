package draw;

import draw.stroke.StrokeFactory;
import global.Constant;
import global.draw.Anchor;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class DrawAnchor implements Serializable {

    private static final long serialVersionUID = 1L;

    private final ArrayList<Ellipse2D> anchors;
    private final StrokeFactory strokeFactory;

    public DrawAnchor() {
        anchors = new ArrayList<>();
        strokeFactory = StrokeFactory.createStrokeFactory();
    }

    public ArrayList<Ellipse2D> getAnchors() {
        return anchors;
    }

    public void createAnchor(Rectangle bound) {
        Arrays.stream(Anchor.values()).forEach(anchor -> {
            anchors.add(anchor.getAnchor(bound));
        });
    }

    public void draw(Graphics2D graphics2D) {
        anchors.forEach(anchor -> {
            graphics2D.setColor(graphics2D.getBackground());
            graphics2D.fill(anchor);
            graphics2D.setColor(Constant.DEFAULT_OUTLINE_COLOR);
            graphics2D.setStroke(strokeFactory.getStroke(1));
            graphics2D.draw(anchor);
        });
    }
}
