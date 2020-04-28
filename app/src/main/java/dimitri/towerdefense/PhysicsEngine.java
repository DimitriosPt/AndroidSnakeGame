package dimitri.towerdefense;

class PhysicsEngine {
    // This signature and much more will
//change later in the project
    boolean update(long fps, ParticleSystem particleSystem){
        if(particleSystem.isRunning){
            particleSystem.update(fps);
        }
        return false;
    }
// Collision detection method will go here
}