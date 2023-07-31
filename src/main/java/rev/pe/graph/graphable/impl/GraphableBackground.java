package rev.pe.graph.graphable.impl;

import rev.pe.graph.graphable.Graphable;
import rev.pe.graph.graphics.GraphicsTransformative;

import java.awt.geom.Rectangle2D;

public class GraphableBackground extends Graphable
{
    @Override
    public void paintImpl(GraphicsTransformative graphicsT, Rectangle2D canvas)
    {
        graphicsT.fillRectangle(canvas.getMinX(), canvas.getMaxY(), canvas.getWidth(), canvas.getHeight());
    }
}
