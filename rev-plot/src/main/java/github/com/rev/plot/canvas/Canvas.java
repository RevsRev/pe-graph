package github.com.rev.plot.canvas;

import github.com.rev.plot.graphable.Graphable;
import github.com.rev.plot.graphable.impl.GraphableBackground;
import github.com.rev.plot.graphics.GraphicsTransformative;
import lombok.Getter;
import lombok.Setter;
import rev.pe.math.linear.vec.Vec2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Canvas {

    private static final Rectangle2D.Double DEFAULT_CANVAS_CALC = new Rectangle2D.Double(-10, -10, 20, 20);
    private static final Rectangle2D.Double DEFAULT_CANVAS_WINDOW = new Rectangle2D.Double(-5, -5, 10, 10);

    private boolean refresh = true;

    @Getter
    private final GraphicsTransformative graphicsT;

    @Getter
    private final ScreenCoordinateMapper coordMapper = new ScreenCoordinateMapper(this);

    @Getter @Setter
    private Rectangle2D canvasCalc = DEFAULT_CANVAS_CALC;
    @Getter @Setter
    private Rectangle2D canvasWindow = DEFAULT_CANVAS_WINDOW;

    private int paintBackground = 0; //at start up we need to paint this twice (gross)

    @Getter @Setter
    private Color backgroundColor = Color.WHITE;
    @Getter @Setter
    private Color defaultLineColor = Color.BLACK;

    private final GraphableBackground background = new GraphableBackground();

    @Getter @Setter
    private boolean paintGraphables = true;

    private final List<Graphable> graphables = new ArrayList<>();

    public Canvas(final GraphicsTransformative graphicsT) {
        this.graphicsT = graphicsT;
        graphicsT.setCanvas(this);
    }

    public void rescale(final double widthScale, final double heightScale) {
        coordMapper.setWidthScale(widthScale);
        coordMapper.setHeightScale(heightScale);
    }

    public void paint(final Graphics2D g) {
        if (!refresh) {
            return;
        }

        graphicsT.setG(g);

        if (paintBackground < 2) {
            background.setColor(backgroundColor);
            background.paint(graphicsT, canvasCalc);
            paintBackground += 1;
        }

        if (paintGraphables) {
            paintGraphables();
        }
    }

    private void paintGraphables() {
        for (Graphable graphable : graphables) {
            graphable.paint(graphicsT, canvasCalc);
        }
    }

    public void addGraphable(final Graphable graphable) {
        graphables.add(graphable);
        graphables.sort(Comparator.comparingInt(Graphable::getLayer));
    }
    public void removeGraphable(final Graphable graphable) {
        graphables.remove(graphable);
    }

    public void drag(final Point previous, final Point current) {
        if (previous == null || current == null) {
            return;
        }

        Vec2 p = new Vec2(previous.x, previous.y);
        Vec2 c = new Vec2(current.x, current.y);
        coordMapper.mapToCanvas(p);
        coordMapper.mapToCanvas(c);
        Vec2 displacement = new Vec2(c.x - p.x, c.y - p.y);
        canvasCalc = new Rectangle2D.Double(canvasCalc.getX() + displacement.x,
                canvasCalc.getY() + displacement.y,
                canvasCalc.getWidth(),
                canvasCalc.getHeight());
        canvasWindow = new Rectangle2D.Double(canvasWindow.getX() + displacement.x,
                canvasWindow.getY() + displacement.y,
                canvasWindow.getWidth(),
                canvasWindow.getHeight());
    }
}
