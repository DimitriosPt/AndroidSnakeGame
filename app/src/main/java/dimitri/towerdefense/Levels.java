package dimitri.towerdefense;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

// Will instantiate all of the objexts that are being passed in
// Will also randomly choose which kind of enemy will get instaniated ONLY ADDS ENEMIES
public abstract  class Levels  {
    Context context= TowerDefense.getContext();
    private List<GameObject> objects;
    private Human human;
    private Human human2;
    private Background  background;
    private int startingEnemyCount;
    private BasicAOETower bae;
    private BasicGunTower bge;
    public Levels(int level)
    {
                    bge= new BasicGunTower();
                    bae= new BasicAOETower();
                    background = new Background();

                    objects= new ArrayList<>();
                     objects.add(background);
                        for(startingEnemyCount=0; startingEnemyCount <10;startingEnemyCount++)
                        {


                        }
                        human = new Human(context, 40, 40, new ArrayList<Enemy.damageResistances>());
                        human2= new Human(context, 20, 20, new ArrayList<Enemy.damageResistances>());
                        objects.add(human);
                        objects.add(human2);

                    //objects.add(background);
                    objects.add(bae);
                    objects.add(bge);
        }

        public List<GameObject> getObjects()
        {
            return objects;
        }
    }
