package dimitri.towerdefense;

import android.graphics.PointF;
import android.graphics.RectF;

import java.util.ArrayList;

class PhysicsEngine {
        // This signature and much more will change later in the project
        boolean update(long fps, ArrayList<GameObject> objects,
        GameState gs, ParticleSystem ps){

        // Update all the game objects
        for (GameObject object : objects) {
        if (object.checkActive()) {
        object.update(fps, objects
        .get(Level.PLAYER_INDEX).getTransform());
        }
        }

        if(ps.isRunning){
        ps.update(fps);
        }

        return detectCollisions(gs, objects,  ps);
        }


// Collision detection will go here
    private boolean detectCollisions(GameState mGameState, ArrayList<GameObject> objects, ParticleSystem ps) {
        boolean playerHit = false;
        for (GameObject gameObject : objects) {

            if (gameObject.checkActive()) {
                // The ist object is active so worth checking

                for (GameObject secondGameObject : objects) {

                    if (secondGameObject.checkActive()) {

                        // The 2nd object is active so worth checking
                        if (RectF.intersects(gameObject.getTransform().getHitbox(), secondGameObject.getTransform().getHitbox())) {
                            // There has been a collision - but does it matter
    //                        switch (gameObject.getTag() + " with " + secondGameObject.getTag()) {
    //                            case "Player with Alien Laser":
    //                                playerHit = true;
    //                                mGameState.loseLife(se);
    //
    //                                break;
    //
    //                            case "Player with Alien":
    //                                playerHit = true;
    //                                mGameState.loseLife(se);
    //
    //                                break;
    //
    //                            case "Player Laser with Alien":
    //                                mGameState.increaseScore();
    //                                // Respawn the alien
    //                                ps.emitParticles(
    //                                        new PointF(
    //                                                secondGameObject.getTransform().getLocation().x,
    //                                                secondGameObject.getTransform().getLocation().y
    //
    //                                        )
    //                                );
    //                                secondGameObject.setInactive();
    //                                secondGameObject.spawn(objects.get(Level.PLAYER_INDEX).getTransform());
    //                                gameObject.setInactive();
    //                                se.playAlienExplode();
    //
    //                                break;
    //
    //                            default:
    //                                break;
                        }

                    }
                }
            }
        }

            return playerHit;
    }
}