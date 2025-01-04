package github.com.rev.plot.ui;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GraphFrameUpdateRunnable implements Runnable
{
    private final GraphFrame graphFrame;
    private static final int THREAD_SLEEP_TIME_MILLIS = 30;

    public GraphFrameUpdateRunnable(GraphFrame graphFrame) {
        this.graphFrame = graphFrame;
    }

    public static void startGraphUpdateThread(GraphFrame graphFrame) {
        GraphFrameUpdateRunnable gRunnable = new GraphFrameUpdateRunnable(graphFrame);
        Thread updateThread = new Thread(gRunnable);
        updateThread.start();
    }

    @Override
    public void run()
    {
        while (true) {
            try {
                if (graphFrame.isVisible()) {
                    graphFrame.repaint();
                }
            }
            finally {
                try
                {
                    Thread.sleep(30);
                } catch (InterruptedException e)
                {
                    log.error("Exception caught in Update Thread. Restarting.", e);
                    startGraphUpdateThread(graphFrame);
                }
            }
        }

    }
}
