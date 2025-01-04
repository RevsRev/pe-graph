package github.com.rev.plot.example.conway;

import github.com.rev.plot.graphable.Graphable;
import github.com.rev.plot.graphics.GraphicsTransformative;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.geom.Rectangle2D;
import java.util.Set;

public final class GraphableConwaysLife extends Graphable {
    private final ConwaysLife cLife;

    public GraphableConwaysLife(final ConwaysLife cLife) {
        this.cLife = cLife;
    }

    @Override
    public void paintImpl(final GraphicsTransformative graphicsTransformative, final Rectangle2D rectangle2D)
    {
        cLife.update();
        Set<Pair<Integer,Integer>> cells = cLife.getCells();
        for (Pair<Integer, Integer> cell : cells) {
            graphicsTransformative.fillRectangle(cell.getLeft(), cell.getRight(), 1, 1);
        }
    }
}
