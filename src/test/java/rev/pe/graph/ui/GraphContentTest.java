package rev.pe.graph.ui;

import rev.pe.graph.canvas.Canvas;
import rev.pe.graph.graphable.Graphable;
import rev.pe.graph.graphable.impl.GraphableAxes;
import rev.pe.graph.graphable.impl.func.GraphableFunc;
import rev.pe.graph.graphics.impl.GraphicsLinear;
import rev.pe.math.linear.vec.Vec2;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GraphContentTest
{

    public static void main(String[] args) {
        graphContentTest();
    }

    public static void graphContentTest() {
        GraphicsLinear graphicsT = new GraphicsLinear(new Vec2(1,0), new Vec2(0,1));
        Canvas canvas = new Canvas(graphicsT);
        canvas.setCanvasCalc(new Rectangle2D.Double(-40,-40,80,80));
        canvas.setCanvasWindow(new Rectangle2D.Double(-20,-20,40,40));
        GraphFrame frame = new GraphFrame(1600, 800, canvas);

        Graphable axes = new GraphableAxes();
        axes.setColor(Color.LIGHT_GRAY);
        canvas.addGraphable(axes);

        canvas.addGraphable(GraphableFunc.factory(GraphableFunc.FuncTypes.PARABOLA));

        GraphFrameUpdateRunnable.startGraphUpdateThread(frame);

        frame.setVisible(true);
    }

}
