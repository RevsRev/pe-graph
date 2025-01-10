package github.com.rev.plot.example.parabola;

import github.com.rev.plot.canvas.Canvas;
import github.com.rev.plot.plotable.Plotable;
import github.com.rev.plot.plotable.impl.PlotableAxes;
import github.com.rev.plot.plotable.impl.func.PlotableFunc;
import github.com.rev.plot.ui.GraphFrame;
import github.com.rev.plot.ui.GraphFrameUpdateRunnable;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public final class Main {
    public static void main(String[] args) {
        Canvas canvas = new Canvas(new Rectangle2D.Double(-40,-40,80,80),
                new Rectangle2D.Double(-20, -20, 40, 40));
        GraphFrame frame = new GraphFrame(1600, 800, canvas);

        Plotable axes = new PlotableAxes();
        axes.setColor(Color.LIGHT_GRAY);
        canvas.addPlotable(axes);

        canvas.addPlotable(PlotableFunc.factory(PlotableFunc.FuncTypes.PARABOLA));
        frame.setVisible(true);
        GraphFrameUpdateRunnable.startGraphUpdateThread(frame);
    }
}