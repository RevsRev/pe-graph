package github.com.rev.plot.coord.impl;

import github.com.rev.plot.coord.Mapping;
import rev.pe.math.linear.matrix.Mat2;
import rev.pe.math.linear.vec.Vec2;

public final class LinearMapping extends Mapping {

    private final Vec2 e1;
    private final Vec2 e2;

    private final Mat2 bases;
    private final Mat2 basesInv;

    public LinearMapping(final Vec2 e1, final Vec2 e2) {
        this.e1 = Vec2.normalize(e1);
        this.e2 = Vec2.normalize(e2);
        bases = new Mat2(this.e1.x, this.e2.x, this.e1.y, this.e2.y);
        basesInv = bases.inverse();
    }

    @Override
    public Vec2 apply(Vec2 vec2) {
        return bases.mult(vec2);
    }

    public static final Mapping DEFAULT = new LinearMapping(new Vec2(1, 0), new Vec2(0, 1));
}
