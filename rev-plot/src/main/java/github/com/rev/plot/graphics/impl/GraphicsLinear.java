package github.com.rev.plot.graphics.impl;

import github.com.rev.plot.canvas.ScreenCoordinateMapper;
import github.com.rev.plot.graphics.Stylus;
import rev.pe.math.linear.matrix.Mat2;
import rev.pe.math.linear.vec.Vec2;

public final class GraphicsLinear extends Stylus {
    //basis vectors
    private final Vec2 e1;
    private final Vec2 e2;

    private final Mat2 bases;
    private final Mat2 basesInv;

    public GraphicsLinear(final ScreenCoordinateMapper coordMapper, final Vec2 e1, final Vec2 e2) {
        super(coordMapper);
        this.e1 = Vec2.normalize(e1);
        this.e2 = Vec2.normalize(e2);
        bases = new Mat2(this.e1.x, this.e2.x, this.e1.y, this.e2.y);
        basesInv = bases.inverse();
    }

    @Override
    public Vec2 transform(final Vec2 point) {
        return bases.mult(point);
    }
}
