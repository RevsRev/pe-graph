package github.com.rev.plot.example.langton;

import github.com.rev.plot.canvas.Canvas;
import github.com.rev.plot.graphable.Graphable;
import github.com.rev.plot.graphable.impl.GraphableAxes;
import github.com.rev.plot.graphable.impl.GraphableBackground;
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

        Graphable axes = new GraphableAxes();
        axes.setColor(Color.LIGHT_GRAY);
        axes.setLayer(1);
        canvas.addGraphable(axes);

        Graphable background = new GraphableBackground();
        background.setLayer(-1);
        background.setColor(Color.WHITE);
        canvas.addGraphable(background);

        GraphFrame frame = new GraphFrame(WIDTH, HEIGHT, canvas);

        GraphFrameUpdateRunnable.startGraphUpdateThread(frame);

        frame.setVisible(true);

        LangtonsAnt langtonsAnt = new LangtonsAnt();
        GraphableLangtonsAnt graphableLangtonsAnt = new GraphableLangtonsAnt(langtonsAnt);
        canvas.addGraphable(graphableLangtonsAnt);
        langtonsAnt.setRefreshListener(canvas);
        LangtonsAnt.LangtonsAntRunner runner = new LangtonsAnt.LangtonsAntRunner(langtonsAnt);
        runner.start();
        while (frame.isVisible()) {
        }
        runner.stop();
    }
}