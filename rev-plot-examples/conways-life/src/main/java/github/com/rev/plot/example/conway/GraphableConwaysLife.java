package github.com.rev.plot.example.conway;

import github.com.rev.plot.canvas.Canvas;
import github.com.rev.plot.graphable.Graphable;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Set;

public final class GraphableConwaysLife extends Graphable {
    private final ConwaysLife cLife;

    public GraphableConwaysLife(final ConwaysLife cLife) {
        this.cLife = cLife;
    }

    @Override
    public void paintImpl(final Canvas canvas) {
        cLife.update();
        Set<Pair<Integer, Integer>> cells = cLife.getCells();
        for (Pair<Integer, Integer> cell : cells) {
            canvas.getStylus().fillRectangle(cell.getLeft(), cell.getRight(), 1, 1);
        }
    }
}
