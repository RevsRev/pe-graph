package github.com.rev.plot.example.langton;

import github.com.rev.plot.canvas.Canvas;
import github.com.rev.plot.graphable.Graphable;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.Color;
import java.util.Set;

public final class GraphableLangtonsAnt extends Graphable {
    private final GraphableCells graphableCells;

    public GraphableLangtonsAnt(LangtonsAnt langtonsAnt) {
        this.graphableCells = new GraphableCells(langtonsAnt.getBlackSquares(), Color.BLACK);
    }

    @Override
    public void paintImpl(final Canvas canvas) {
        graphableCells.paint(canvas);
    }

    private static final class GraphableCells extends Graphable {

        private final Set<Pair<Long, Long>> cells;

        private GraphableCells(final Set<Pair<Long, Long>> cells, final Color color) {
            this.cells = cells;
            setColor(color);
        }


        @Override
        public void paintImpl(Canvas canvas) {
            for (Pair<Long, Long> cell : cells) {
                canvas.getStylus().fillRectangle(cell.getLeft(), cell.getRight(), 1, 1);
            }
        }
    }
}
