package github.com.rev.plot.canvas;

import github.com.rev.plot.geom.Curve;
import github.com.rev.plot.plotable.Plotable;
import lombok.Getter;
import lombok.Setter;
import rev.pe.math.linear.vec.Vec2;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public final class Canvas implements RefreshListener {
    @Getter @Setter
    private boolean refresh = true;

    @Getter
    private final Rectangle2D canvasCalc;

    @Getter @Setter
    private boolean paintGraphables = true;

    private final List<Plotable> plotables = new ArrayList<>();

    //TODO - Remove redundant parameter
    public Canvas(final Rectangle2D canvasCalc, final Rectangle2D canvasWindow) {
        this.canvasCalc = canvasCalc;
    }

    public List<Curve> getCurves() {
        List<Curve> curves = new LinkedList<>();
        for (Plotable p: plotables) {
            curves.addAll(p.getCurves(canvasCalc));
        }
        return curves;
    }
    public List<Rectangle2D> getRectangles() {
        List<Rectangle2D> rectangles = new LinkedList<>();
        for (Plotable p: plotables) {
            rectangles.addAll(p.getRectangles(canvasCalc));
        }
        return rectangles;
    }
    public List<Point2D> getPoints() {
        List<Point2D> points = new LinkedList<>();
        for (Plotable p: plotables) {
            points.addAll(p.getPoints(canvasCalc));
        }
        return points;
    }

    public void addPlotable(final Plotable plotable) {
        plotables.add(plotable);
        plotables.sort(Comparator.comparingInt(Plotable::getLayer));
    }
    public void removeGraphable(final Plotable plotable) {
        plotables.remove(plotable);
    }

    public void drag(final Vec2 displacement) {

        canvasCalc.setRect(new Rectangle2D.Double(canvasCalc.getX() + displacement.x,
                canvasCalc.getY() + displacement.y,
                canvasCalc.getWidth(),
                canvasCalc.getHeight()));

        refresh = true;
    }

    @Override
    public void onRefresh() {
        refresh = true;
    }
}
