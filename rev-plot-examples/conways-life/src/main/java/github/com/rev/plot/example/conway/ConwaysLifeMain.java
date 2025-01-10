package github.com.rev.plot.example.conway;


import github.com.rev.plot.canvas.Canvas;
import github.com.rev.plot.plotable.Plotable;
import github.com.rev.plot.plotable.impl.PlotableAxes;
import github.com.rev.plot.plotable.impl.PlotableBackground;
import github.com.rev.plot.ui.GraphFrame;
import github.com.rev.plot.ui.GraphFrameUpdateRunnable;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Set;

public final class ConwaysLifeMain {
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

        ConwaysLife conwaysLife = new ConwaysLife(initialConfiguration());
        PlotableConwaysLife graphableConwaysLife = new PlotableConwaysLife(conwaysLife);
        canvas.addPlotable(graphableConwaysLife);
        conwaysLife.setRefreshListener(canvas);
        ConwaysLife.LifeRunner runner = new ConwaysLife.LifeRunner(conwaysLife);
        runner.start();
        while (frame.isVisible()) {
        }
        runner.stop();

    }

    public static Set<Pair<Integer,Integer>> initialConfiguration() {
        HashSet<Pair<Integer,Integer>> configuration = new HashSet<>();
        configuration.add(Pair.of(1,1));
        configuration.add(Pair.of(2,1));
        configuration.add(Pair.of(3,1));
        configuration.add(Pair.of(4,1));
        configuration.add(Pair.of(5,1));
        configuration.add(Pair.of(6,1));
        configuration.add(Pair.of(1,2));
        configuration.add(Pair.of(2,3));
        configuration.add(Pair.of(3,3));
        configuration.add(Pair.of(4,2));
        configuration.add(Pair.of(5,2));
        configuration.add(Pair.of(6,3));
        return configuration;
    }

}