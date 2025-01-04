package github.com.rev.plot.example.parabola.plot.ui;

import github.com.rev.plot.example.parabola.plot.canvas.Canvas;
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


public class GraphContent extends JPanel implements MouseWheelListener, MouseMotionListener, MouseListener
{
    @Getter @Setter
    private int width = 1600;
    @Getter @Setter
    private int height = 800;
    private Point previousDragPos = null;
    private Point currentDragPos = null;

    private final Canvas canvas;

    public GraphContent(LayoutManager layoutManager, int width, int height, Canvas canvas) {
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
    public void paint(Graphics g) {
        canvas.paint((Graphics2D)g);
    }


    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        currentDragPos = e.getPoint();
        canvas.drag(currentDragPos, previousDragPos);
        previousDragPos = currentDragPos;
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        currentDragPos = null;
        previousDragPos = null;
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}
