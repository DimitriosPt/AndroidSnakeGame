package dimitri.towerdefense;

import java.util.List;

public class AreaOfEffectAttackStrategy implements AttackStrategy {
    @Override
    public void attack(Tower tower, List<Enemy> enemies) {
        for(Enemy enemy:enemies) {

            if (tower.calculateDistanceToTower(tower, enemy) <= tower.getRange()) {
                enemy.setHealth(enemy.getHealth() - tower.getDamage());
            }

            tower.setTimeOfLastAttack(System.currentTimeMillis());
        }
    }

    @Override
    public void drawProjectiles() {

    }
}
