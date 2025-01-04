package github.com.rev.plot.graphable.impl.func;

import github.com.rev.plot.graphable.Graphable;
import github.com.rev.plot.graphics.GraphicsTransformative;
import rev.pe.math.linear.vec.Vec2;

import java.awt.geom.Rectangle2D;
import java.util.function.Function;

public final class GraphableFunc extends Graphable {
    private static final int DEFAULT_STEP = 1000;

    private final Function<Double, Double> func;

    public GraphableFunc(final Function<Double, Double> func) {
        this.func = func;
    }

    @Override
    public void paintImpl(final GraphicsTransformative graphicsT, final Rectangle2D canvas) {
        double xMin = canvas.getMinX();
        double xMax = canvas.getMaxX();

        double step = (xMax - xMin) / DEFAULT_STEP;
        Vec2 start = new Vec2(0, 0);
        Vec2 end = new Vec2(0, 0);
        for (double x = xMin; x < xMax; x = x + step) {
            start.x = x;
            start.y = func.apply(x);
            end.x = x + step;
            end.y = func.apply(x + step);
            graphicsT.drawLine(start, end);
        }
    }

    public enum FuncTypes {
        PARABOLA
    }

    public static GraphableFunc factory(final FuncTypes type) {
        switch (type) {
            case PARABOLA:
                return new GraphableFunc(x -> x * x);
            default:
                return null;
        }
    }

}
