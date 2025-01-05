package github.com.rev.plot.graphable.impl;

import github.com.rev.plot.canvas.Canvas;
import github.com.rev.plot.graphable.Graphable;

import java.awt.geom.Rectangle2D;


public class GraphableBackground extends Graphable {
    @Override
    public void paintImpl(final Canvas canvas) {
        Rectangle2D canvasCalc = canvas.getCanvasCalc();
        canvas.getStylus().fillRectangle(
                canvasCalc.getMinX(),
                canvasCalc.getMaxY(),
                canvasCalc.getWidth(),
                canvasCalc.getHeight());
    }
}
