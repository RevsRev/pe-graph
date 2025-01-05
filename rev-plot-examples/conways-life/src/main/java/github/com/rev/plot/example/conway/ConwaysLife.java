package github.com.rev.plot.example.conway;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

public final class ConwaysLife {
    @Getter
    private final Set<Pair<Integer, Integer>> liveCells;
    @Getter
    private final Set<Pair<Integer, Integer>> deadCells = new HashSet<>();

    public ConwaysLife(final Set<Pair<Integer, Integer>> initialConfiguration) {
        this.liveCells = initialConfiguration;
    }

    private void update() {
        Set<Pair<Integer,Integer>> nextAlive = new HashSet<>();
        Set<Pair<Integer,Integer>> nextDead = new HashSet<>();
        for (Pair<Integer, Integer> cell : liveCells) {
            AtomicReference<Integer> liveNeighbours = new AtomicReference<>(0);
            iterateNeighbours(cell, c -> {
                if (liveCells.contains(c)) {
                    liveNeighbours.updateAndGet(v -> v + 1);
                } else if (resurrect(c)) {
                    nextAlive.add(c);
                }
                return null;
            });

            if (liveNeighbours.get() >= 2 && liveNeighbours.get() <= 3) {
                nextAlive.add(cell);
            } else {
                nextDead.add(cell);
            }
        }
        liveCells.clear();
        liveCells.addAll(nextAlive);
        deadCells.clear();
        deadCells.addAll(nextDead);
    }

    private boolean resurrect(final Pair<Integer,Integer> cell) {
        if (liveCells.contains(cell)) {
            return false;
        }

        AtomicReference<Integer> liveNeighbours = new AtomicReference<>(0);
        iterateNeighbours(cell, c -> {
            if (liveCells.contains(c)) {
                liveNeighbours.updateAndGet(v -> v + 1);
            }
            return null;
        });
        return liveNeighbours.get() == 3;
    }

    private <V> void iterateNeighbours(final Pair<Integer,Integer> cell,
                                       final Function<Pair<Integer,Integer>, V> iterateFunc) {
        int x = cell.getLeft();
        int y = cell.getRight();
        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
                if (i==0 && j == 0) {
                    continue;
                }
                Pair<Integer,Integer> neighbour = Pair.of(x + i, y + j);
                iterateFunc.apply(neighbour);
            }
        }
    }

    public static class LifeRunner implements Runnable {
        public static final int SLEEP_MILLIS = 500;
        private boolean stop = false;
        private final ConwaysLife life;

        LifeRunner(ConwaysLife life) {
            this.life = life;
        }

        @Override
        public void run() {
            while (!stop) {
                life.update();
                try {
                    Thread.sleep(SLEEP_MILLIS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public void start() {
            Thread t = new Thread(this);
            t.start();
        }
        public void stop() {
            stop = true;
        }
    }
}
