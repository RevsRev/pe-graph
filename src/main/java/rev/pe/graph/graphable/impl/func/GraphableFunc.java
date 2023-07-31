package rev.pe.graph.graphable.impl.func;

import lombok.NonNull;
import rev.pe.graph.graphable.Graphable;
import rev.pe.graph.graphics.GraphicsTransformative;
import rev.pe.math.linear.vec.Vec2;

import java.awt.geom.Rectangle2D;
import java.util.function.Function;

public class GraphableFunc extends Graphable
{
    private final Function<Double, Double> func;

    public GraphableFunc(@NonNull Function<Double, Double> func) {
        this.func = func;
    }

    @Override
    public void paintImpl(GraphicsTransformative graphicsT, Rectangle2D canvas)
    {
        double xMin = canvas.getMinX();
        double xMax = canvas.getMaxX();

        double step = (xMax-xMin)/1000;
        Vec2 start = new Vec2(0,0);
        Vec2 end = new Vec2(0,0);
        for (double x=xMin; x<xMax; x = x + step) {
            start.x = x;
            start.y = func.apply(x);
            end.x = x + step;
            end.y = func.apply(x+step);
            graphicsT.drawLine(start, end);
        }
    }

    public enum FuncTypes {
        PARABOLA
    }

    public static GraphableFunc factory(FuncTypes type) {
        switch(type) {
            case PARABOLA:
                return new GraphableFunc(x -> x*x);
        }
        return null;
    }

}
