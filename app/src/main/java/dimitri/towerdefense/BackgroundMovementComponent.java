package dimitri.towerdefense;

public class BackgroundMovementComponent implements MovementComponent {
    @Override
    public boolean move(long fps, Transform transform) {
        System.out.println("Moving background");
        return true;
    }
}
