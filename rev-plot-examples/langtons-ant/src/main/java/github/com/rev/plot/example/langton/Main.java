package github.com.rev.plot.example.langton;

import github.com.rev.plot.canvas.Canvas;
import github.com.rev.plot.plotable.Plotable;
import github.com.rev.plot.plotable.impl.PlotableAxes;
import github.com.rev.plot.plotable.impl.PlotableBackground;
import github.com.rev.plot.ui.GraphFrame;
import github.com.rev.plot.ui.GraphFrameUpdateRunnable;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Main {

    private static int WIDTH = 3600;
    private static int HEIGHT = 1800;

    public static void main(String[] args) {
        Canvas canvas = new Canvas(new Rectangle2D.Double(-200, -200, 400, 400),
                new Rectangle2D.Double(-100, -100, 200, 200));

        Plotable axes = new PlotableAxes();
        axes.setColor(Color.LIGHT_GRAY);
        axes.setLayer(1);
        canvas.addPlotable(axes);

        Plotable background = new PlotableBackground();
        background.setLayer(-1);
        background.setColor(Color.WHITE);
        canvas.addPlotable(background);

        GraphFrame frame = new GraphFrame(WIDTH, HEIGHT, canvas);

        GraphFrameUpdateRunnable.startGraphUpdateThread(frame);

        frame.setVisible(true);

        LangtonsAnt langtonsAnt = new LangtonsAnt();
        PlotableLangtonsAnt graphableLangtonsAnt = new PlotableLangtonsAnt(langtonsAnt);
        canvas.addPlotable(graphableLangtonsAnt);
        langtonsAnt.setRefreshListener(canvas);
        LangtonsAnt.LangtonsAntRunner runner = new LangtonsAnt.LangtonsAntRunner(langtonsAnt);
        runner.start();
        while (frame.isVisible()) {
        }
        runner.stop();
    }
}