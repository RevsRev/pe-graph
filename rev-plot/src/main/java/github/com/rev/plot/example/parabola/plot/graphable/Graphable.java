package github.com.rev.plot.example.parabola.plot.graphable;

import lombok.Getter;
import lombok.Setter;
import github.com.rev.plot.example.parabola.plot.graphics.GraphicsTransformative;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Graphable
{
    @Getter
    @Setter
    private boolean visible = true;

    @Getter @Setter
    private Color color = Color.BLACK;

    @Getter @Setter
    private int layer = 0;

    public void paint(GraphicsTransformative graphicsT, Rectangle2D canvas) {
        if (visible) {
            Color oldColor = graphicsT.getColor();
            graphicsT.setColor(color);
            paintImpl(graphicsT, canvas);
            graphicsT.setColor(oldColor);
        }
    }
    public abstract void paintImpl(GraphicsTransformative graphicsT, Rectangle2D canvas);
}
