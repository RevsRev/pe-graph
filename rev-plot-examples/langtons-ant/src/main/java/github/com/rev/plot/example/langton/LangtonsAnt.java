package github.com.rev.plot.example.langton;

import github.com.rev.plot.canvas.RefreshListener;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Set;

public final class LangtonsAnt
{
    private final Ant ant = new Ant(MutablePair.of(0L, 0L), (short)0);

    @Setter
    private RefreshListener refreshListener;

    @Getter
    private final Set<Pair<Long, Long>> blackSquares = new HashSet<>();

    public void update()
    {
        Pair<Long, Long> antPosition = ant.getPosition();
        if (blackSquares.contains(antPosition)) {
            ant.rotate((short) 1);
        }
        else {
            ant.rotate((short) -1);
        }
        if (blackSquares.contains(antPosition)) {
            blackSquares.remove(antPosition);
        } else {
            blackSquares.add(new MutablePair<>(antPosition.getLeft(), antPosition.getRight()));
        }
        ant.moveForward();
    }

    public void refresh() {
        if (refreshListener != null) {
            refreshListener.onRefresh();
        }
    }

    public static class Ant
    {
        @Getter
        private final MutablePair<Long,Long> position;
        @Getter
        private short rotation;

        public Ant(final MutablePair<Long, Long> position, short rotation)
        {
            this.position = position;
            this.rotation = rotation;
        }

        public void rotate(short direction)
        {
            rotation = (short)Math.floorMod(rotation + direction, 4);
        }

        public void moveForward()
        {
            switch(rotation)
            {
                case 0:
                    position.setLeft(position.getLeft() + 1);
                    break;
                case 1:
                    position.setRight(position.getRight() + 1);
                    break;
                case 2:
                    position.setLeft(position.getLeft() - 1);
                    break;
                case 3:
                    position.setRight(position.getRight() - 1);
                    break;
            }
        }
    }

    public static class LangtonsAntRunner implements Runnable {
        public static final int SLEEP_MILLIS = 500;
        private boolean stop = false;
        private final LangtonsAnt langtonsAnt;

        LangtonsAntRunner(LangtonsAnt langtonsAnt) {
            this.langtonsAnt = langtonsAnt;
        }

        @Override
        public void run() {
            while (!stop) {
                langtonsAnt.refresh();
                langtonsAnt.update();
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