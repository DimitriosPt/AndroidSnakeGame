package dimitri.towerdefense;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SingleTargetAttackStrategy implements AttackStrategy {
    @Override
    public void attack(Tower tower, List<Enemy> enemies) {
        //if there are no enemies in range, do nothing
        try
        {
            //I am sorting the list of enemies passed in by their distance to the tower,
            // meaning the enemy at index 0 is the absolute closest to the tower
            //there are a few ways to get the same results without having to do a lot of sorting
            //which is inefficient but this seemed like a more educational approach
            sortEnemiesByDistanceToTower(tower, enemies);

            if(calculateDistanceToTower(tower,enemies.get(0)) <= tower.getRange()){
                enemies.get(0).setHealth(enemies.get(0).getHealth() - tower.getDamage());
            }
            tower.setTimeOfLastAttack(System.currentTimeMillis());
        }
        catch(Exception ignored)
        {

        }

    }

    @Override
    public void drawProjectiles() {

    }



    public void sortEnemiesByDistanceToTower(final Tower tower, List<Enemy> enemies)
    {
        Collections.sort(enemies, new Comparator<Enemy>() {

            @Override
            public int compare(Enemy o1, Enemy o2) {
                return calculateDistanceToTower(tower, o1) < calculateDistanceToTower(tower, o2) ? -1
                        : calculateDistanceToTower(tower, o2) < calculateDistanceToTower(tower, o1) ? -1
                        : 0;
            }
        });
    }
}
