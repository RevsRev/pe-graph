package github.com.rev.plot.canvas;

import github.com.rev.plot.graphable.Graphable;
import github.com.rev.plot.graphics.Stylus;
import lombok.Getter;
import lombok.Setter;
import rev.pe.math.linear.vec.Vec2;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Canvas implements RefreshListener {
    @Getter @Setter
    private boolean repaint = true;

    @Getter
    private Stylus stylus;
    @Getter
    private final Rectangle2D canvasCalc;

    @Getter @Setter
    private boolean paintGraphables = true;

    private final List<Graphable> graphables = new ArrayList<>();

    //TODO - Remove redundant parameter
    public Canvas(final Rectangle2D canvasCalc, final Rectangle2D canvasWindow) {
        this.canvasCalc = canvasCalc;
    }

    public void paint(final Graphics2D g) {
        if (!repaint) {
            return;
        }
        repaint = false;

        stylus.setG(g);

        if (paintGraphables) {
            paintGraphables();
        }
    }

    private void paintGraphables() {
        for (Graphable graphable : graphables) {
            graphable.paint(this);
        }
    }

    public void addGraphable(final Graphable graphable) {
        graphables.add(graphable);
        graphables.sort(Comparator.comparingInt(Graphable::getLayer));
    }
    public void removeGraphable(final Graphable graphable) {
        graphables.remove(graphable);
    }

    public void drag(final Vec2 displacement) {

        canvasCalc.setRect(new Rectangle2D.Double(canvasCalc.getX() + displacement.x,
                canvasCalc.getY() + displacement.y,
                canvasCalc.getWidth(),
                canvasCalc.getHeight()));

        repaint = true;
    }

    @Override
    public void onRefresh() {
        repaint = true;
    }

    public void initStylus(ScreenCoordinateMapper coordMapper) {
        stylus = new Stylus(coordMapper);
    }
}
