package rev.pe.graph.graphable.impl;

import rev.pe.graph.graphable.Graphable;
import rev.pe.graph.graphics.GraphicsTransformative;
import rev.pe.math.vec.Vec2;

import java.awt.geom.Rectangle2D;

public class GraphableAxes extends Graphable
{
    @Override
    public void paintImpl(GraphicsTransformative graphicsT, Rectangle2D canvas)
    {
        double xMin = Math.floor(canvas.getMinX());
        double xMax = Math.ceil(canvas.getMaxX());
        double yMin = Math.floor(canvas.getMinY());
        double yMax = Math.ceil(canvas.getMaxY());

        Vec2 start = new Vec2(xMin, canvas.getMinY());
        Vec2 end = new Vec2(xMin, canvas.getMaxY());
        for (double x = xMin; x<xMax; x += 1) {
            start.x = x;
            end.x = x;
            graphicsT.drawLine(start, end);
        }

        start = new Vec2(canvas.getMinX(), yMin);
        end = new Vec2(canvas.getMaxX(), yMin);
        for (double y = yMin; y<yMax; y += 1) {
            start.y = y;
            end.y = y;
            graphicsT.drawLine(start, end);
        }
    }
}
