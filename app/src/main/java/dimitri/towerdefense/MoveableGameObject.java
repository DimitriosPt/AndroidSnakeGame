package dimitri.towerdefense;

public abstract class MoveableGameObject extends GameObject {
    private int heading;
    private int speed;

    abstract void move(int heading, int speed);
}
