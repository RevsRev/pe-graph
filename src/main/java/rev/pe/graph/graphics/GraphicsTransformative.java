package rev.pe.graph.graphics;

import lombok.Getter;
import lombok.Setter;
import rev.pe.graph.canvas.Canvas;
import rev.pe.math.vec.Vec2;

import java.awt.*;


public abstract class GraphicsTransformative
{
    @Setter
    private Graphics g = null;

    @Getter @Setter
    private double widthScale = 1.0f;

    @Getter @Setter
    private double heightScale = 1.0f;

    @Getter @Setter
    private Canvas canvas;

    @Getter @Setter
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

    private final void draw(Runnable drawRunnable) {
        if (erase) {
            g.setColor(canvas.getBackgroundColor());
        }

        drawRunnable.run();

        if (erase) {
            g.setColor(canvas.getDefaultLineColor());
        }
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
