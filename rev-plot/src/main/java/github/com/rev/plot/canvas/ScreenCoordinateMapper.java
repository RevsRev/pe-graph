package github.com.rev.plot.canvas;


import lombok.Getter;
import lombok.Setter;
import rev.pe.math.linear.vec.Vec2;

public final class ScreenCoordinateMapper {
    private final Canvas canvas;
    @Getter @Setter
    private double widthScale = 1;
    @Getter @Setter
    private double heightScale = 1;

    public ScreenCoordinateMapper(final Canvas canvas) {
        this.canvas = canvas;
    }

    @SuppressWarnings("checkstyle:LineLength")
    public void mapToScreen(final Vec2 p) {
        p.x = getWidthScale() * (p.x - canvas.getCanvasWindow().getMinX()) / (canvas.getCanvasWindow().getWidth());
        p.y = getHeightScale() * (1 - (p.y - canvas.getCanvasWindow().getMinY()) / (canvas.getCanvasWindow().getHeight()));
    }

    public void mapToCanvas(final Vec2 p) {
        p.x = p.x * canvas.getCanvasWindow().getWidth() / getWidthScale() + canvas.getCanvasWindow().getMinX();
        p.y = canvas.getCanvasWindow().getHeight() * (1 - p.y / getWidthScale()) + canvas.getCanvasWindow().getMinY();
    }
}
