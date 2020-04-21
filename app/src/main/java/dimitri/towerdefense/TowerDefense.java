package dimitri.towerdefense;

import android.app.Application;
import android.content.Context;

public class TowerDefense extends Application {
    private static TowerDefense instance;

    public static TowerDefense getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
        // or return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

}