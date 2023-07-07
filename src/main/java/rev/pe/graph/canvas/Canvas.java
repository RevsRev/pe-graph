package rev.pe.graph.canvas;

import lombok.Getter;
import lombok.Setter;
import rev.pe.graph.graphable.Graphable;
import rev.pe.graph.graphics.GraphicsTransformative;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Canvas
{
    private boolean refresh = true;

    private final GraphicsTransformative graphicsT;

    @Getter @Setter
    private Rectangle2D canvas = new Rectangle2D.Double(-1600, -800, 3200, 1600);

    @Getter @Setter
    private boolean paintGraphables = true;

    Set<Graphable> graphables = new HashSet<>();

    public Canvas(GraphicsTransformative graphicsT) {
        this.graphicsT = graphicsT;
    }

    public final void paint(Graphics2D g) {
        if (!refresh) {
            return;
        }

        graphicsT.g = g;

        if (paintGraphables) {
            paintGraphables();
        }
    }

    public void paintGraphables() {
        Iterator<Graphable> it = graphables.iterator();
        while (it.hasNext()) {
            it.next().paint(graphicsT, canvas);
        }
    }

    public void addGraphable(Graphable graphable) {
        graphables.add(graphable);
    }
    public void removeGraphable(Graphable graphable) {
        graphables.remove(graphable);
    }
}
