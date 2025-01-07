package github.com.rev.plot.ui;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class GraphFrameUpdateRunnable implements Runnable {
    public static final int STARTUP_TIME = 1000; //Not ideal, but thinking of moving away from Swing in future...
    private final GraphFrame graphFrame;
    private static final int PAINT_TIME_MILLIS = 5;
    private static final int THREAD_SLEEP_TIME_MILLIS = 30;

    public GraphFrameUpdateRunnable(final GraphFrame graphFrame) {
        this.graphFrame = graphFrame;
    }

    public static void startGraphUpdateThread(final GraphFrame graphFrame) {
        GraphFrameUpdateRunnable gRunnable = new GraphFrameUpdateRunnable(graphFrame);
        Thread updateThread = new Thread(gRunnable);
        updateThread.start();
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (true) {
            try {
                if (!graphFrame.isVisible()) {
                    startTime = System.currentTimeMillis();
                }
                if (System.currentTimeMillis() - startTime < STARTUP_TIME) {
                    graphFrame.setRepaint(true);
                }
                if (graphFrame.isVisible() && graphFrame.shouldRepaint()) {
                    graphFrame.repaint(PAINT_TIME_MILLIS);
                }
            } finally {
                try {
                    Thread.sleep(THREAD_SLEEP_TIME_MILLIS);
                } catch (InterruptedException e) {
                    log.error("Exception caught in Update Thread. Restarting.", e);
                    startGraphUpdateThread(graphFrame);
                }
            }
        }
    }
}
