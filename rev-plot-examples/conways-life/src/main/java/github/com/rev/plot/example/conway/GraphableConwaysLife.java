package github.com.rev.plot.example.conway;

import github.com.rev.plot.canvas.Canvas;
import github.com.rev.plot.graphable.Graphable;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.Color;
import java.util.Set;

public final class GraphableConwaysLife extends Graphable {
    private final ConwaysLife cLife;

    private final GraphableCells aliveCells;
    private final GraphableCells deadCells;

    public GraphableConwaysLife(final ConwaysLife cLife) {
        this.cLife = cLife;
        this.aliveCells = new GraphableCells(cLife.getLiveCells(), Color.BLACK);
        this.deadCells = new GraphableCells(cLife.getDeadCells(), Color.WHITE);
    }

    @Override
    public void paintImpl(final Canvas canvas) {
        aliveCells.paint(canvas);
        deadCells.paint(canvas);
    }

    private static final class GraphableCells extends Graphable {

        private final Set<Pair<Integer, Integer>> cells;

        private GraphableCells(final Set<Pair<Integer, Integer>> cells, final Color color) {
            this.cells = cells;
            setColor(color);
        }


        @Override
        public void paintImpl(Canvas canvas) {
            for (Pair<Integer, Integer> cell : cells) {
                canvas.getStylus().fillRectangle(cell.getLeft(), cell.getRight(), 1, 1);
            }
        }
    }
}
