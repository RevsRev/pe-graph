package github.com.rev.plot.example.parabola.plot.graphics;

import github.com.rev.plot.example.parabola.plot.canvas.Canvas;
import lombok.Getter;
import lombok.Setter;
import rev.pe.math.linear.vec.Vec2;

import java.awt.Color;
import java.awt.Graphics;


@Setter
public abstract class GraphicsTransformative
{
    private Graphics g = null;

    @Getter
    private double widthScale = 1.0f;

    @Getter
    private double heightScale = 1.0f;

    @Getter
    private Canvas canvas;

    @Getter
    private boolean erase = false;

    public abstract Vec2 transform(Vec2 point);

    public final void drawPoint(Vec2 point, int size) {
        if (g == null) {
            return;
        }

        Vec2 p = transform(point);
        mapToScreen(p);
        draw(() -> g.fillOval((int)p.x - size/2, (int)p.y - size/2, size,size));
    }

    public final void drawLine(Vec2 start, Vec2 end) {
        if (g == null) {
            return;
        }
        Vec2 s = transform(start);
        Vec2 e = transform(end);
        mapToScreen(s);
        mapToScreen(e);

        draw(() -> g.drawLine((int)s.x, (int)s.y, (int)e.x, (int)e.y));
    }

    public final void fillRectangle(double x, double y, double width, double height) {
        Vec2 bottomLeftCorner = new Vec2(x,y);
        Vec2 topRightCorner = new Vec2(x+width, y+height);
        mapToScreen(bottomLeftCorner);
        mapToScreen(topRightCorner);
        draw(()->g.fillRect((int)bottomLeftCorner.x, (int)bottomLeftCorner.y,
                (int)(topRightCorner.x - bottomLeftCorner.x), (int)(bottomLeftCorner.y-topRightCorner.y)));
    }

    private Runnable getDrawableRunnable(Runnable implementationRunnable) {
        return () ->
        {
            if (erase) {
                g.setColor(canvas.getBackgroundColor());
            }
            implementationRunnable.run();
            if (erase) {
                g.setColor(canvas.getDefaultLineColor());
            }
        };
    }

    private final void draw(Runnable drawImplementation) {
        Runnable drawRunnable = getDrawableRunnable(drawImplementation);
        drawRunnable.run();
    }

    private final void mapToScreen(Vec2 p) {
        canvas.getCoordMapper().mapToScreen(p);
    }

    public void setColor(Color color) {
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
