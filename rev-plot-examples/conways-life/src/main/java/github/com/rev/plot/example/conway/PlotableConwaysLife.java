package github.com.rev.plot.example.conway;

import github.com.rev.plot.canvas.Canvas;
import github.com.rev.plot.plotable.Plotable;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public final class PlotableConwaysLife extends Plotable {
    private final ConwaysLife cLife;

    private final PlotableCells aliveCells;
    private final PlotableCells deadCells;

    public PlotableConwaysLife(final ConwaysLife cLife) {
        this.cLife = cLife;
        this.aliveCells = new PlotableCells(cLife.getLiveCells(), Color.BLACK);
        this.deadCells = new PlotableCells(cLife.getDeadCells(), Color.WHITE);
    }


    @Override
    protected List<Rectangle2D> getRectanglesImpl(Rectangle2D bounds) {
        List<Rectangle2D> rectangles = new LinkedList<>();
        rectangles.addAll(aliveCells.getRectangles(bounds));
        rectangles.addAll(deadCells.getRectangles(bounds));
        return rectangles;
    }

    private static final class PlotableCells extends Plotable {

        private final Set<Pair<Integer, Integer>> cells;

        private PlotableCells(final Set<Pair<Integer, Integer>> cells, final Color color) {
            this.cells = cells;
            setColor(color);
        }

        @Override
        protected List<Rectangle2D> getRectanglesImpl(Rectangle2D bounds) {
            List<Rectangle2D> rectangles = new LinkedList<>();
            for (Pair<Integer, Integer> cell : cells) {
                rectangles.add(new Rectangle2D.Double(cell.getLeft(), cell.getRight(), 1, 1));
            }
            return rectangles;
        }
    }
}
