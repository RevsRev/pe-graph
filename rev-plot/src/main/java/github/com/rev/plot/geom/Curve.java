package github.com.rev.plot.geom;

import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.LinkedList;

public final class Curve {
    private final LinkedList<Point2D> points = new LinkedList<>();

    public void addPoint(final Point2D... point) {
        for (int i = 0; i < point.length; i++) {
            points.add(point[i]);
        }
    }

    public Iterator<Point2D> iterator() {
        return points.iterator();
    }
}
