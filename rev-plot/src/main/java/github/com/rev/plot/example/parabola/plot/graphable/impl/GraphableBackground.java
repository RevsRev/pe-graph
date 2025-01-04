package github.com.rev.plot.example.parabola.plot.graphable.impl;

import github.com.rev.plot.example.parabola.plot.graphable.Graphable;
import github.com.rev.plot.example.parabola.plot.graphics.GraphicsTransformative;

import java.awt.geom.Rectangle2D;

public class GraphableBackground extends Graphable
{
    @Override
    public void paintImpl(GraphicsTransformative graphicsT, Rectangle2D canvas)
    {
        graphicsT.fillRectangle(canvas.getMinX(), canvas.getMaxY(), canvas.getWidth(), canvas.getHeight());
    }
}
