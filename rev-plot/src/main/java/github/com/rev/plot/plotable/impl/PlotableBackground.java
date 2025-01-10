package github.com.rev.plot.plotable.impl;

import github.com.rev.plot.canvas.Canvas;
import github.com.rev.plot.plotable.Plotable;

import java.awt.geom.Rectangle2D;
import java.util.List;


public class PlotableBackground extends Plotable {
    @Override
    protected List<Rectangle2D> getRectanglesImpl(Rectangle2D bounds) {
        return List.of(new Rectangle2D.Double(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight()));
    }
}
