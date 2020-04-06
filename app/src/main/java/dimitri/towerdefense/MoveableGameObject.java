package dimitri.towerdefense;

public abstract class MoveableGameObject extends GameObject {
    private int heading;
    private int speed;
    static final int NORTH = 0;
    static final int EAST = 90;
    static final int SOUTH = 180;
    static final int WEST = 270;

    public MoveableGameObject(int speed) {
        this.speed = speed;
    }

    abstract void move();

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public int getSpeed() {
        return speed;
    }
}