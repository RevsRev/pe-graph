package github.com.rev.plot.canvas;


import rev.pe.math.linear.vec.Vec2;

public class ScreenCoordinateMapper
{
    private final Canvas canvas;
    public double widthScale = 1;
    public double heightScale = 1;

    public ScreenCoordinateMapper(Canvas canvas) {
        this.canvas = canvas;
    }

    public void mapToScreen(Vec2 p) {
        p.x = widthScale * (p.x- canvas.getCanvasWindow().getMinX())/(canvas.getCanvasWindow().getWidth());
        p.y = heightScale * (1 - (p.y- canvas.getCanvasWindow().getMinY())/(canvas.getCanvasWindow().getHeight()));
    }

    public void mapToCanvas(Vec2 p) {
        p.x = p.x* canvas.getCanvasWindow().getWidth()/widthScale + canvas.getCanvasWindow().getMinX();
        p.y = canvas.getCanvasWindow().getHeight() * (1-p.y/widthScale) + canvas.getCanvasWindow().getMinY();
    }
}
