package github.com.rev.plot.graphics;

import github.com.rev.plot.canvas.ScreenCoordinateMapper;
import github.com.rev.plot.coord.Mapping;
import github.com.rev.plot.coord.impl.LinearMapping;
import github.com.rev.plot.geom.Curve;
import lombok.Getter;
import lombok.Setter;
import rev.pe.math.linear.vec.Vec2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.List;


@Setter
public final class Stylus {
    private Graphics g = null;

    private final ScreenCoordinateMapper coordinateMapper;
    private final Mapping mapping;

    @Getter @Setter
    private Color backgroundColor = Color.WHITE;
    @Getter @Setter
    private Color defaultLineColor = Color.BLACK;

    @Getter
    private boolean erase = false;

    public Stylus(final ScreenCoordinateMapper coordinateMapper) {
        this.coordinateMapper = coordinateMapper;
        this.mapping = LinearMapping.DEFAULT;
    }

    public void drawPoints(final List<Point2D> points) {
        for (Point2D point : points) {
            drawPoint(point);
        }
    }
    private void drawPoint(final Point2D point) {
        drawPoint(new Vec2(point.getX(), point.getY()), 5);
    }
    private void drawPoint(final Vec2 point, final int size) {
        Vec2 p = mapping.apply(point);
        mapToScreen(p);
        draw(() -> g.fillOval((int) p.x - size / 2, (int) p.y - size / 2, size, size));
    }

    public void drawCurves(final List<Curve> curves) {
        for (Curve curve : curves) {
            drawCurve(curve);
        }
    }

    private void drawCurve(final Curve curve) {
        Iterator<Point2D> it = curve.iterator();
        Point2D start = it.next();
        Point2D end = null;
        while (it.hasNext()) {
            end = it.next();
            drawLine(start, end);
            start = end;
        }
    }
    private void drawLine(final Point2D start, final Point2D end) {
        drawLine(new Vec2(start.getX(), start.getY()), new Vec2(end.getX(), end.getY()));
    }
    private void drawLine(final Vec2 start, final Vec2 end) {
        Vec2 s = mapping.apply(start);
        Vec2 e = mapping.apply(end);
        mapToScreen(s);
        mapToScreen(e);

        draw(() -> g.drawLine((int) s.x, (int) s.y, (int) e.x, (int) e.y));
    }

    public void drawRectangles(final List<Rectangle2D> rectangles) {
        for (Rectangle2D rectangle : rectangles) {
            drawRectangle(rectangle);
        }
    }

    private void drawRectangle(final Rectangle2D rectangle) {
        fillRectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }
    private void fillRectangle(final double x,
                              final double y,
                              final double width,
                              final double height) {
        Vec2 bottomLeftCorner = new Vec2(x, y);
        Vec2 topRightCorner = new Vec2(x + width, y + height);
        mapToScreen(bottomLeftCorner);
        mapToScreen(topRightCorner);
        draw(() -> g.fillRect((int) bottomLeftCorner.x, (int) bottomLeftCorner.y,
                (int) (topRightCorner.x - bottomLeftCorner.x), (int) (bottomLeftCorner.y - topRightCorner.y)));
    }

    private Runnable getDrawableRunnable(final Runnable implementationRunnable) {
        return () -> {
            if (erase) {
                g.setColor(backgroundColor);
            }
            implementationRunnable.run();
            if (erase) {
                g.setColor(defaultLineColor);
            }
        };
    }

    private void draw(final Runnable drawImplementation) {
        Runnable drawRunnable = getDrawableRunnable(drawImplementation);
        drawRunnable.run();
    }

    private void mapToScreen(final Vec2 p) {
        coordinateMapper.mapToScreen(p);
    }

    public void setColor(final Color color) {
        if (g != null) {
            g.setColor(color);
        }
    }
    public Color getColor() {
        if (g != null) {
            return g.getColor();
        }
        return null;
    }
}
