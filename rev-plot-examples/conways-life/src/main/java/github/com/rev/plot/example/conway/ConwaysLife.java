package github.com.rev.plot.example.conway;

import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

public class ConwaysLife {
    @Getter
    private final Set<Pair<Integer, Integer>> cells;

    public ConwaysLife() {
        this(new HashSet());
    }
    public ConwaysLife(Set<Pair<Integer,Integer>> initialConfiguration) {
        this.cells = initialConfiguration;
    }

    public void update() {
        Set<Pair<Integer,Integer>> updatedCells = new HashSet<>();
        Iterator<Pair<Integer,Integer>> cellsIt = cells.iterator();
        while (cellsIt.hasNext()) {
            Pair<Integer,Integer> cell = cellsIt.next();

            AtomicReference<Integer> liveNeighbours = new AtomicReference<>(Integer.valueOf(0));
            iterateNeighbours(cell, c-> {
                if (cells.contains(c)) {
                    liveNeighbours.updateAndGet(v -> v + 1);
                } else if (resurrect(c)) {
                    updatedCells.add(c);
                }
                return null;
            });

            if (liveNeighbours.get() >= 2 && liveNeighbours.get() <= 3) {
                updatedCells.add(cell);
            }
        }
        cells.clear();
        cells.addAll(updatedCells);
    }

    private boolean resurrect(Pair<Integer,Integer> cell) {
        if (cells.contains(cell)) {
            return false;
        }

        AtomicReference<Integer> liveNeighbours = new AtomicReference<>(Integer.valueOf(0));
        iterateNeighbours(cell, c -> {
            if (cells.contains(c)) {
                liveNeighbours.updateAndGet(v -> v + 1);
            }
            return null;
        });
        return liveNeighbours.get() == 3;
    }

    private <V> void iterateNeighbours(Pair<Integer,Integer> cell, Function<Pair<Integer,Integer>, V> iterateFunc) {
        int x = cell.getLeft();
        int y = cell.getRight();
        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
                if (i==0 && j == 0) {
                    continue;
                }
                Pair<Integer,Integer> neighbour = Pair.of(x+i, y+j);
                iterateFunc.apply(neighbour);
            }
        }
    }
}
