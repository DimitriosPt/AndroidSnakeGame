package dimitri.towerdefense;

public class ProjectileMovementComponent implements MovementComponent {
    @Override
    public boolean move(long fps, Transform transform) {
        return true;
    }
}
