package github.com.rev.plot.canvas;


import lombok.Getter;
import lombok.Setter;
import rev.pe.math.linear.vec.Vec2;

import java.awt.geom.Rectangle2D;

public final class ScreenCoordinateMapper {
    private final Rectangle2D window;
    @Getter @Setter
    private double widthScale = 1;
    @Getter @Setter
    private double heightScale = 1;

    public ScreenCoordinateMapper(final Rectangle2D window) {
        this.window = window;
    }

    @SuppressWarnings("checkstyle:LineLength")
    public void mapToScreen(final Vec2 p) {
        p.x = getWidthScale() * (p.x - window.getMinX()) / (window.getWidth());
        p.y = getHeightScale() * (1 - (p.y - window.getMinY()) / (window.getHeight()));
    }

    public void mapToCanvas(final Vec2 p) {
        p.x = p.x * window.getWidth() / getWidthScale() + window.getMinX();
        p.y = window.getHeight() * (1 - p.y / getWidthScale()) + window.getMinY();
    }
}
