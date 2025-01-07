package github.com.rev.plot.graphable;

import github.com.rev.plot.canvas.Canvas;
import github.com.rev.plot.canvas.RefreshListener;
import github.com.rev.plot.graphics.Stylus;
import lombok.Getter;
import lombok.Setter;

import java.awt.Color;

@Setter
@Getter
public abstract class Graphable {
    private boolean visible = true;
    private Color color = Color.BLACK;
    private int layer = 0;

    public abstract void paintImpl(Canvas canvas);

    public final void paint(final Canvas canvas) {
        if (visible) {
            Stylus stylus = canvas.getStylus();
            Color oldColor = stylus.getColor();
            stylus.setColor(color);
            paintImpl(canvas);
            stylus.setColor(oldColor);
        }
    }
}
