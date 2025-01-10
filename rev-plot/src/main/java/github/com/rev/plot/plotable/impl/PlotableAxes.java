package github.com.rev.plot.plotable.impl;

import github.com.rev.plot.geom.Curve;
import github.com.rev.plot.plotable.Plotable;
import rev.pe.math.linear.vec.Vec2;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

public class PlotableAxes extends Plotable {

    @Override
    public List<Curve> getCurvesImpl(final Rectangle2D bounds) {
        List<Curve> curves = new LinkedList<>();

        double xMin = Math.floor(bounds.getMinX());
        double xMax = Math.ceil(bounds.getMaxX());
        double yMin = Math.floor(bounds.getMinY());
        double yMax = Math.ceil(bounds.getMaxY());

        Vec2 start = new Vec2(xMin, bounds.getMinY());
        Vec2 end = new Vec2(xMin, bounds.getMaxY());
        for (double x = xMin; x < xMax; x += 1) {
            start.x = x;
            end.x = x;
            Curve c = new Curve();
            c.addPoint(new Point2D.Double(start.x, start.y));
            c.addPoint(new Point2D.Double(end.x, end.y));
            curves.add(c);
        }

        start = new Vec2(bounds.getMinX(), yMin);
        end = new Vec2(bounds.getMaxX(), yMin);
        for (double y = yMin; y < yMax; y += 1) {
            start.y = y;
            end.y = y;
            Curve c = new Curve();
            c.addPoint(new Point2D.Double(start.x, start.y));
            c.addPoint(new Point2D.Double(end.x, end.y));
            curves.add(c);
        }
        return curves;
    }
}
