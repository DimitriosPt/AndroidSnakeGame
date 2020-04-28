package dimitri.towerdefense;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.Random;

class ParticleSystem {
    float duration;
    ArrayList<Particle> particles;
    Random random = new Random();
    boolean isRunning = false;


    void init(int numParticles) {
        particles = new ArrayList<>();
// Create the particles
        for (int i = 0; i < numParticles; i++) {
            float angle = random.nextInt(360);
            angle = angle * (float) Math.PI / 180.f; //convert to radians
            float speed = (random.nextInt(20) + 1);

            PointF velocityVector = new PointF(
                    (float) Math.cos(angle) * speed,
                    (float) Math.sin(angle) * speed);

            particles.add(new Particle(velocityVector));
        }
    }

    void update(long fps){
        duration -= (1f/fps);
        for(Particle p : particles){
            p.update();
        }
        if (duration < 0)
        {
            isRunning = false;
        }
    }


    void emitParticles(PointF startPosition){
        isRunning = true;
        duration = 1f;
        for(Particle p : particles){
            p.setPosition(startPosition);
        }
    }

    void draw(Canvas canvas, Paint paint) {
        for (Particle p : particles) {
            paint.setARGB(255,
                    random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(256));
            // Uncomment the next line to have plain white particles
//paint.setColor(Color.argb(255,255,255,255));
            canvas.drawRect(p.getLocation().x,
                    p.getLocation().y,
                    p.getLocation().x + 25,
                    p.getLocation().y + 25, paint);
        }
    }
}