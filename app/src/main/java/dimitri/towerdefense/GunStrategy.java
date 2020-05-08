package dimitri.towerdefense;

import java.util.List;

class GunStrategy implements AttackStrategy {


    @Override
    public void attack(Tower tower, List<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (tower.canAttack() && tower.isInRange(enemy)) {
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
