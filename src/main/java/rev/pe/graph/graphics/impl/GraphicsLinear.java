package rev.pe.graph.graphics.impl;

import lombok.Getter;
import lombok.Setter;
import rev.pe.graph.graphics.GraphicsTransformative;
import rev.pe.math.linear.matrix.Mat2;
import rev.pe.math.linear.vec.Vec2;

import java.awt.*;

public class GraphicsLinear extends GraphicsTransformative
{
    //basis vectors
    public final Vec2 e1;
    public final Vec2 e2;

    public final Mat2 bases;
    public final Mat2 basesInv;

    public GraphicsLinear(Vec2 e1, Vec2 e2) {
        this.e1=Vec2.normalize(e1);
        this.e2=Vec2.normalize(e2);
        bases = new Mat2(this.e1.x, this.e2.x, this.e1.y, this.e2.y);
        basesInv = bases.inverse();
    }

    @Override
    public Vec2 transform(Vec2 point) {
        return bases.mult(point);
    }
}
