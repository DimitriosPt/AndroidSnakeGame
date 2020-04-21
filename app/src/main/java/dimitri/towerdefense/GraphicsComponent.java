package dimitri.towerdefense;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

public interface GraphicsComponent {

    void initialize(ObjectSpec spec, PointF size);

    void draw(Canvas canvas, Paint paint, Transform transform);
}
