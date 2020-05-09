package dimitri.towerdefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

 class EnemyBuilder {

    private int speed;
    private MovementStrategy movementStrategy;
    private int maxHealth;
    public String enemyType;
    private Point location;
    private int pointValue;
    private ArrayList<Enemy.damageResistances> damageResistances;
    EnemyBuilder()
    {}

    EnemyBuilder pointValue(int pointValue) {
        this.pointValue = pointValue;
        return this;
    }


    EnemyBuilder Location(Point location) {
        this.location = location;
        return this;
    }
    /*
    EnemyBuilder bitmap(Bitmap enemyBitmap) {
        this.enemyBitmap = enemyBitmap;
        return this;
    }
    */

    EnemyBuilder EnemyType(String enemyType)
    {
        this.enemyType=enemyType;
        return this;
    }

    EnemyBuilder EnemyResistances(ArrayList<Enemy.damageResistances> damageResistances)
    {
        this.damageResistances=damageResistances;
        return this;
    }

    EnemyBuilder EnemyHP(int maxHealth)
    {
        this.maxHealth=maxHealth;
        return this;
    }

    EnemyBuilder Speed(int speed)
    {
        this.speed=speed;
        return this;
    }
    EnemyBuilder MovementStrategy(MovementStrategy movementStrategy)
    {
        this.movementStrategy=movementStrategy;
        return this;

    }
    /*
    EnemyBuilder EnemyBitmap(Bitmap enemyBitmap) {
        this.enemyBitmap=enemyBitmap;
        return this;
    }
    */

    Enemy build() {
        switch (enemyType)
        {
            case "Orc":
            {
                return new Orc(TowerDefense.getContext(), maxHealth, speed, damageResistances);
            }
            case "Human":
            {
                return new Human(TowerDefense.getContext(),maxHealth, speed,damageResistances);
            }

            default:
            {
                System.out.println("Enemy Type Not Recognized");
                return new Human(TowerDefense.getContext(),maxHealth, speed,damageResistances);
            }
        }
    }
}