package github.com.rev.plot.graphable.impl;

import github.com.rev.plot.graphable.Graphable;
import github.com.rev.plot.graphics.GraphicsTransformative;

import java.awt.geom.Rectangle2D;

public class GraphableBackground extends Graphable {
    @Override
    public void paintImpl(final GraphicsTransformative graphicsT, final Rectangle2D canvas) {
        graphicsT.fillRectangle(canvas.getMinX(), canvas.getMaxY(), canvas.getWidth(), canvas.getHeight());
    }
}
