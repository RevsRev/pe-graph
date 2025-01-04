package github.com.rev.plot.graphable;

import github.com.rev.plot.graphics.GraphicsTransformative;
import lombok.Getter;
import lombok.Setter;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

@Setter
@Getter
public abstract class Graphable
{
    private boolean visible = true;

    private Color color = Color.BLACK;

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
