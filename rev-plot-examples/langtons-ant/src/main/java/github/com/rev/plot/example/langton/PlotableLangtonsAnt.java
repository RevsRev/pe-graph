package github.com.rev.plot.example.langton;

import github.com.rev.plot.plotable.Plotable;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public final class PlotableLangtonsAnt extends Plotable {
    private final PlotableCells graphableCells;

    public PlotableLangtonsAnt(LangtonsAnt langtonsAnt) {
        this.graphableCells = new PlotableCells(langtonsAnt.getBlackSquares(), Color.BLACK);
    }

    @Override
    protected List<Rectangle2D> getRectanglesImpl(Rectangle2D bounds) {
        return graphableCells.getRectangles(bounds);
    }

    private static final class PlotableCells extends Plotable {

        private final Set<Pair<Long, Long>> cells;

        private PlotableCells(final Set<Pair<Long, Long>> cells, final Color color) {
            this.cells = cells;
            setColor(color);
        }

        @Override
        protected List<Rectangle2D> getRectanglesImpl(Rectangle2D bounds) {
            List<Rectangle2D> rectangles = new LinkedList<>();
            for (Pair<Long, Long> cell : cells) {
                rectangles.add(new Rectangle2D.Double(cell.getLeft(), cell.getRight(), 1 ,1));
            }
            return rectangles;
        }
    }
}
