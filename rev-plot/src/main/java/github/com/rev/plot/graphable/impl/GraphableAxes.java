package github.com.rev.plot.graphable.impl;

import github.com.rev.plot.canvas.Canvas;
import github.com.rev.plot.graphable.Graphable;
import rev.pe.math.linear.vec.Vec2;

import java.awt.geom.Rectangle2D;

public class GraphableAxes extends Graphable {
    @Override
    public void paintImpl(final Canvas canvas) {
        Rectangle2D canvasCalc = canvas.getCanvasCalc();
        double xMin = Math.floor(canvasCalc.getMinX());
        double xMax = Math.ceil(canvasCalc.getMaxX());
        double yMin = Math.floor(canvasCalc.getMinY());
        double yMax = Math.ceil(canvasCalc.getMaxY());

        Vec2 start = new Vec2(xMin, canvasCalc.getMinY());
        Vec2 end = new Vec2(xMin, canvasCalc.getMaxY());
        for (double x = xMin; x < xMax; x += 1) {
            start.x = x;
            end.x = x;
            canvas.getStylus().drawLine(start, end);
        }

        start = new Vec2(canvasCalc.getMinX(), yMin);
        end = new Vec2(canvasCalc.getMaxX(), yMin);
        for (double y = yMin; y < yMax; y += 1) {
            start.y = y;
            end.y = y;
            canvas.getStylus().drawLine(start, end);
        }
    }
}
