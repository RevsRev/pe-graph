package github.com.rev.plot.ui;

import github.com.rev.plot.canvas.Canvas;
import lombok.Getter;
import lombok.Setter;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


public final class GraphContent extends JPanel implements MouseWheelListener, MouseMotionListener, MouseListener {
    @Getter @Setter
    private int width;
    @Getter @Setter
    private int height;
    private Point previousDragPos = null;
    private Point currentDragPos = null;

    private final Canvas canvas;

    public GraphContent(final LayoutManager layoutManager,
                        final int width,
                        final int height,
                        final Canvas canvas) {
        super(layoutManager);
        this.width = width;
        this.height = height;
        this.canvas = canvas;

        canvas.rescale(width, height);

        addMouseMotionListener(this);
        addMouseListener(this);
        addMouseWheelListener(this);
    }


    @Override
    public void paint(final Graphics g) {
        canvas.paint((Graphics2D) g);
    }


    @Override
    public void mouseWheelMoved(final MouseWheelEvent e) {

    }

    @Override
    public void mouseDragged(final MouseEvent e) {
        currentDragPos = e.getPoint();
        canvas.drag(currentDragPos, previousDragPos);
        previousDragPos = currentDragPos;
    }

    @Override
    public void mouseMoved(final MouseEvent e) {

    }

    @Override
    public void mouseClicked(final MouseEvent e) {

    }

    @Override
    public void mousePressed(final MouseEvent e) {

    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        currentDragPos = null;
        previousDragPos = null;
    }

    @Override
    public void mouseEntered(final MouseEvent e) {

    }

    @Override
    public void mouseExited(final MouseEvent e) {

    }
}
