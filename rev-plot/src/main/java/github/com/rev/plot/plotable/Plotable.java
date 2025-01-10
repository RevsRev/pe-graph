package github.com.rev.plot.plotable;

import github.com.rev.plot.geom.Curve;
import lombok.Getter;
import lombok.Setter;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
public abstract class Plotable {
    private boolean visible = true;
    private Color color = Color.BLACK;
    private int layer = 0;

    public final List<Curve> getCurves(final Rectangle2D bounds) {
        return getCurvesImpl(bounds);
    }

    protected List<Curve> getCurvesImpl(final Rectangle2D bounds) {
        return Collections.emptyList();
    }

    public final List<Rectangle2D> getRectangles(final Rectangle2D bounds) {
        return getRectanglesImpl(bounds);
    }

    protected List<Rectangle2D> getRectanglesImpl(final Rectangle2D bounds) {
        return Collections.emptyList();
    }

    public final List<Point> getPoints(final Rectangle2D bounds) {
        return getPointsImpl(bounds);
    }

    protected List<Point> getPointsImpl(final Rectangle2D bounds) {
        return Collections.emptyList();
    }

//    public abstract void paintImpl(Canvas canvas);
//
//    public final void paint(final Canvas canvas) {
//        if (visible) {
//            Stylus stylus = canvas.getStylus();
//            Color oldColor = stylus.getColor();
//            stylus.setColor(color);
//            paintImpl(canvas);
//            stylus.setColor(oldColor);
//        }
//    }
}
