package github.com.rev.plot.plotable.impl.func;

import github.com.rev.plot.canvas.Canvas;
import github.com.rev.plot.geom.Curve;
import github.com.rev.plot.plotable.Plotable;
import rev.pe.math.linear.vec.Vec2;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.function.Function;

public final class PlotableFunc extends Plotable {
    private static final int DEFAULT_STEP = 1000;

    private final Function<Double, Double> func;

    public PlotableFunc(final Function<Double, Double> func) {
        this.func = func;
    }

    @Override
    public List<Curve> getCurvesImpl(final Rectangle2D bounds) {
        double xMin = bounds.getMinX();
        double xMax = bounds.getMaxX();

        Curve c = new Curve();

        double step = (xMax - xMin) / DEFAULT_STEP;
        for (double x = xMin; x <= xMax; x = x + step) {
            c.addPoint(new Point2D.Double(x, func.apply(x)));
        }
        return List.of(c);
    }

    public enum FuncTypes {
        PARABOLA
    }

    public static PlotableFunc factory(final FuncTypes type) {
        switch (type) {
            case PARABOLA:
                return new PlotableFunc(x -> x * x);
            default:
                return null;
        }
    }

}
