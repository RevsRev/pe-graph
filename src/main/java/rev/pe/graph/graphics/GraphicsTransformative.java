package rev.pe.graph.graphics;

import lombok.Setter;
import rev.pe.math.vec.Vec2;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class GraphicsTransformative
{
    public Graphics g = null;

    public void drawPoint(Vec2 point) {
        if (g!= null) {
            drawPointImpl(point);
        }
    }
    protected abstract void drawPointImpl(Vec2 point);

    public void drawLine(Vec2 start, Vec2 end) {
        if (g != null) {
            drawLineImpl(start, end);
        }
    }
    protected abstract void drawLineImpl(Vec2 start, Vec2 end);

}
