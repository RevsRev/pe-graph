package github.com.rev.plot.ui;

import github.com.rev.plot.canvas.Canvas;
import github.com.rev.plot.canvas.ScreenCoordinateMapper;
import github.com.rev.plot.geom.Curve;
import github.com.rev.plot.graphics.Stylus;
import github.com.rev.plot.plotable.Plotable;
import lombok.Getter;
import lombok.Setter;
import rev.pe.math.linear.vec.Vec2;

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
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;


public final class GraphContent extends JPanel implements MouseWheelListener, MouseMotionListener, MouseListener {
    @Getter @Setter
    private int width;
    @Getter @Setter
    private int height;
    private Point previousDragPos = null;
    private Point currentDragPos = null;

    private final Canvas canvas;
    private final Rectangle2D window;
    private final ScreenCoordinateMapper coordMapper;
    private final Stylus stylus;

    public GraphContent(final LayoutManager layoutManager,
                        final int width,
                        final int height,
                        final Canvas canvas) {
        super(layoutManager);
        this.width = width;
        this.height = height;
        this.canvas = canvas;

        //TODO - Still need to do a bit more refactoring here...
        this.window = canvas.getCanvasCalc().getBounds2D();
        this.coordMapper = new ScreenCoordinateMapper(window);
        stylus = new Stylus(coordMapper);

        rescale(width, height);

        addMouseMotionListener(this);
        addMouseListener(this);
        addMouseWheelListener(this);
    }

    public void rescale(final double widthScale, final double heightScale) {
        coordMapper.setWidthScale(widthScale);
        coordMapper.setHeightScale(heightScale);
    }

    @Override
    public void paint(final Graphics g) {
        //TODO - Reimplement this logic
        //        if (!repaint) {
//            return;
//        }
//        repaint = false;
//
//        stylus.setG(g);
//
//        if (paintGraphables) {
//            paintGraphables();
//        }

        if (g == null) {
            return;
        }

        stylus.setG(g);
        stylus.drawCurves(canvas.getCurves());
        stylus.drawPoints(canvas.getPoints());
        stylus.drawRectangles(canvas.getRectangles());
    }


    @Override
    public void mouseWheelMoved(final MouseWheelEvent e) {

    }

    @Override
    public void mouseDragged(final MouseEvent e) {
        currentDragPos = e.getPoint();

        if (previousDragPos != null && currentDragPos != null) {

            Vec2 p = new Vec2(previousDragPos.x, previousDragPos.y);
            Vec2 c = new Vec2(currentDragPos.x, currentDragPos.y);

            coordMapper.mapToCanvas(p);
            coordMapper.mapToCanvas(c);

            Vec2 displacement = new Vec2(p.x - c.x, p.y - c.y);

            canvas.drag(displacement);

            window.setRect(new Rectangle2D.Double(window.getX() + displacement.x,
                    window.getY() + displacement.y,
                    window.getWidth(),
                    window.getHeight()));
        }

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
