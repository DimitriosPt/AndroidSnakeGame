package dimitri.towerdefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.view.SurfaceView;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

// Will instantiate all of the objexts that are being passed in
// Will also randomly choose which kind of enemy will get instaniated ONLY ADDS ENEMIES
public abstract class Levels {
    Context context = TowerDefense.getContext();
    private List<GameObject> objects;
    private EnemyBuilder enemyBuilder;
    private Background background;
    private int startingEnemyCount;
    private BasicAOETower bat;
    private BasicGunTower bgt;
    private MovementStrategy movementStrategy;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Levels(int level) {
        int randomNum;
        ArrayList<Enemy.damageResistances> resistance = new ArrayList<Enemy.damageResistances>();
        resistance.add(Enemy.damageResistances.FIRE);
        movementStrategy= new LevelOneEnemyMovement();
        enemyBuilder = new EnemyBuilder();
        //bgt = new BasicGunTower();
        bat = new BasicAOETower();
        background = new Background();
        objects = new ArrayList<>();
        objects.add(background);

        for (startingEnemyCount = 0; startingEnemyCount < 5; startingEnemyCount++) {
            randomNum = ThreadLocalRandom.current().nextInt(2, 41);
            objects.add(enemyBuilder.Location(new Point(0, 600)).
                    EnemyType("Human").EnemyHP(40)
                    .Speed(randomNum)
                    .EnemyResistances(resistance).
                    MovementStrategy(movementStrategy)
                    .build());
        }

        for (startingEnemyCount = 0; startingEnemyCount < 5; startingEnemyCount++) {
            randomNum = ThreadLocalRandom.current().nextInt(2, 41);
            objects.add(enemyBuilder.Location(new Point(0, 600)).
                    EnemyType("Orc").EnemyHP(40)
                    .Speed(randomNum)
                    .EnemyResistances(resistance).
                            MovementStrategy(movementStrategy)
                    .build());
        }
        //objects.add(bgt);
        objects.add(bat);
    }

    public List<GameObject> getObjects() {
        return objects;
    }
}
