package rev.pe.graph.graphics.impl;

import lombok.Getter;
import lombok.Setter;
import rev.pe.graph.graphics.GraphicsTransformative;
import rev.pe.math.vec.Mat2;
import rev.pe.math.vec.Vec2;

import java.awt.*;

public class GraphicsLinear extends GraphicsTransformative
{
    //basis vectors
    public final Vec2 e1;
    public final Vec2 e2;

    public final Mat2 bases;
    public final Mat2 basesInv;

    @Getter
    @Setter
    private double scale = 1.0d;

    public GraphicsLinear(Vec2 e1, Vec2 e2) {
        this.e1=Vec2.normalize(e1);
        this.e2=Vec2.normalize(e2);
        bases = new Mat2(this.e1.x, this.e2.x, this.e1.y, this.e2.y);
        basesInv = bases.inverse();
    }

    @Override
    public void drawPointImpl(Vec2 point) {
        Vec2 p = bases.mult(point);
        g.drawOval((int)(scale*p.x), (int)(scale*p.y), 1, 1);
    }

    @Override
    public void drawLineImpl(Vec2 start, Vec2 end) {
        Vec2 s = bases.mult(start);
        Vec2 e = bases.mult(end);
        g.drawLine((int)(scale*s.x), (int)(scale*s.y), (int)(scale*e.x), (int)(scale*e.y));
    }
}
