package github.com.rev.plot.example.parabola.plot.ui;

import github.com.rev.plot.canvas.Canvas;
import github.com.rev.plot.graphable.Graphable;
import github.com.rev.plot.graphable.impl.GraphableAxes;
import github.com.rev.plot.graphable.impl.func.GraphableFunc;
import github.com.rev.plot.ui.GraphFrame;
import github.com.rev.plot.ui.GraphFrameUpdateRunnable;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class GraphContentTest
{

    public static void main(String[] args) {
        graphContentTest();
    }

    public static void graphContentTest() {
        Canvas canvas = new Canvas(new Rectangle2D.Double(-40,-40,80,80),
                new Rectangle2D.Double(-20,-20,40,40));
        GraphFrame frame = new GraphFrame(1600, 800, canvas);

        Graphable axes = new GraphableAxes();
        axes.setColor(Color.LIGHT_GRAY);
        canvas.addGraphable(axes);

        canvas.addGraphable(GraphableFunc.factory(GraphableFunc.FuncTypes.PARABOLA));

        GraphFrameUpdateRunnable.startGraphUpdateThread(frame);

        frame.setVisible(true);
    }

}
