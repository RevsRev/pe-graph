package rev.pe.graph.canvas;

import lombok.Getter;
import lombok.Setter;
import rev.pe.graph.graphable.Graphable;
import rev.pe.graph.graphable.impl.GraphableBackground;
import rev.pe.graph.graphics.GraphicsTransformative;
import rev.pe.graph.ui.RefreshListener;
import rev.pe.graph.ui.RefreshParms;
import rev.pe.math.linear.vec.Vec2;

import java.awt.*;
import java.util.List;
import java.awt.geom.Rectangle2D;
import java.util.*;

public class Canvas
{
    private boolean refresh = true;

    @Getter
    private final GraphicsTransformative graphicsT;

    @Getter
    private final ScreenCoordinateMapper coordMapper = new ScreenCoordinateMapper(this);

    @Getter @Setter
    private Rectangle2D canvasCalc = new Rectangle2D.Double(-10, -10, 20, 20);
    @Getter @Setter
    private Rectangle2D canvasWindow = new Rectangle2D.Double(-5,-5,10,10);

    @Getter @Setter
    private int paintBackground = 0; //at start up we need to paint this twice (gross)

    @Getter @Setter
    private Color backgroundColor = Color.WHITE;
    @Getter @Setter
    private Color defaultLineColor = Color.BLACK;

    private final GraphableBackground background = new GraphableBackground();

    @Getter @Setter
    private boolean paintGraphables = true;

    private final List<Graphable> graphables = new ArrayList<>();

    public Canvas(GraphicsTransformative graphicsT) {
        this.graphicsT = graphicsT;
        graphicsT.setCanvas(this);
    }

    public void rescale(double widthScale, double heightScale) {
        coordMapper.widthScale = widthScale;
        coordMapper.heightScale = heightScale;
    }

    public final void paint(Graphics2D g) {
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

    public void paintGraphables() {
        Iterator<Graphable> it = graphables.iterator();
        while (it.hasNext()) {
            it.next().paint(graphicsT, canvasCalc);
        }
    }

    public void addGraphable(Graphable graphable) {
        graphables.add(graphable);
        graphables.sort(Comparator.comparingInt(Graphable::getLayer));
    }
    public void removeGraphable(Graphable graphable) {
        graphables.remove(graphable);
    }

    public void drag(Point previous, Point current) {
        if (previous == null || current == null) {
            return;
        }

        Vec2 p = new Vec2(previous.x, previous.y);
        Vec2 c = new Vec2(current.x, current.y);
        coordMapper.mapToCanvas(p);
        coordMapper.mapToCanvas(c);
        Vec2 displacement = new Vec2(c.x-p.x, c.y-p.y);
        canvasCalc = new Rectangle2D.Double(canvasCalc.getX() + displacement.x, canvasCalc.getY() + displacement.y, canvasCalc.getWidth(), canvasCalc.getHeight());
        canvasWindow = new Rectangle2D.Double(canvasWindow.getX() + displacement.x, canvasWindow.getY() + displacement.y, canvasWindow.getWidth(), canvasWindow.getHeight());
    }
}
