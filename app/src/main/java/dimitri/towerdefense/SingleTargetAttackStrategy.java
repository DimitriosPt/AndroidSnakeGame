package dimitri.towerdefense;

import java.util.List;

class SingleTargetAttackStrategy implements AttackStrategy {


    @Override
    public void attack(Tower tower, List<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (tower.canAttack() && tower.isInRange(enemy)) {
                enemy.setCurrentHealth(enemy.getCurrentHealth() - tower.getDamage());
                tower.setTimeOfLastAttack(System.currentTimeMillis());
                break;
            }


        }
    }
    @Override
    public void drawProjectiles() {

    }
}
