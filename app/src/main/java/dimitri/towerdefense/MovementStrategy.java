package dimitri.towerdefense;

public interface MovementStrategy {
    static final int NORTH = 0;
    static final int EAST = 90;
    static final int SOUTH = 180;
    static final int WEST = 270;

    public void move(MoveableGameObject self);

}
