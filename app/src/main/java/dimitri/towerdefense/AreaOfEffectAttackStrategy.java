package dimitri.towerdefense;

import java.util.List;

public class AreaOfEffectAttackStrategy implements AttackStrategy {
    @Override
    public void attack(Tower tower, List<Enemy> enemies) {
        for(Enemy enemy:enemies) {
            double xDistance = (double) (tower.getLocation().x - enemy.getLocation().x);
            double yDistance = (double) (tower.getLocation().y - enemy.getLocation().y);
            double distanceFromTowerToEnemy =
                    Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));

            if (distanceFromTowerToEnemy <= tower.getRange()) {
                enemy.setHealth(enemy.getHealth() - tower.getDamage());
            }

            tower.setTimeOfLastAttack(System.currentTimeMillis());
        }
    }

    @Override
    public void drawProjectiles() {

    }
}
