package dimitri.towerdefense;

abstract class Tower extends StaticGameObject{
    private int range;
    private int damage;
    private int attackSpeed;
    private int cost;

    private enum damageType{
        PHYSICAL, FIRE, FROST, LIGHTNING, RADIANT
    }

    public int getRange() {
        return range;
    }

    public int getDamage() {
        return damage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public int getCost() {
        return cost;
    }

    abstract void attack(int attackSpeed, int damage, int damageType);
}
