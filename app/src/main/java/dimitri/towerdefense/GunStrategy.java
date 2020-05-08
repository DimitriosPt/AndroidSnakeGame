package dimitri.towerdefense;

import java.util.List;

class GunStrategy implements AttackStrategy {


    @Override
    public void attack(Tower tower, List<Enemy> enemies) {
        for (Enemy enemy : enemies) {

            // double xDistance = (double) (tower.getLocation().x - enemy.getLocation().x);
            //double yDistance = (double) (tower.getLocation().y - enemy.getLocation().y);
            //double distanceFromTowerToEnemy =
            //       Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
            if (tower.canAttack() && tower.isInRange(enemies)) {
                enemy.setHealth(enemy.getHealth() - tower.getDamage());
                break;
            }

            tower.setTimeOfLastAttack(System.currentTimeMillis());
        }
        tower.setTimeOfLastAttack(System.currentTimeMillis());
    }
    @Override
    public void drawProjectiles() {

    }
}
