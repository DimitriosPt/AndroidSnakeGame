package dimitri.towerdefense;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class SnakeComposite extends GameObject{

    private enum Heading {
        UP, RIGHT, DOWN, LEFT
    }

    private Heading heading = Heading.RIGHT;

    GameObject snakeHead = new SnakeBodyPart();
    private List<GameObject> bodyParts = new ArrayList<>();

    public void addBodyPart(GameObject gameObject){
        bodyParts.add(gameObject);
    }
    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        snakeHead.draw(canvas, paint);

        for(GameObject gameObject : bodyParts)
        {
            gameObject.draw(canvas, paint);
        }
    }

    @Override
    public void spawn()
    {
//        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
//        int blockSize = displayMetrics.widthPixels / 40;
//        // How many blocks of the same size will fit into the height
//        int mNumBlocksHigh = displayMetrics.heightPixels / blockSize;
        // Reset the heading
        heading = Heading.RIGHT;

        // Delete the old contents of the ArrayList
        bodyParts.clear();

        // Start with a single snake segment
        //bodyParts.add(new Point(  20, mNumBlocksHigh / 2));
    }

    public void move()
    {
        //move bodyparts
        for (int i = bodyParts.size(); i > 0; i--)
        {
            //sets the position of a body part equal to that of the
            //part in front of it to give the illusion of movement
            bodyParts.get(i).setLocation(bodyParts.get(i).getLocation());
        }

        //move head
        Point currentHeadLocation = snakeHead.getLocation();
        switch (heading) {
            case UP:
                snakeHead.setLocation(new Point(currentHeadLocation.x, currentHeadLocation.y--));
                break;

            case RIGHT:
                snakeHead.setLocation(new Point(currentHeadLocation.x++, currentHeadLocation.y));
                break;

            case DOWN:
                snakeHead.setLocation(new Point(currentHeadLocation.x, currentHeadLocation.y++));
                break;

            case LEFT:
                snakeHead.setLocation(new Point(currentHeadLocation.x--, currentHeadLocation.y--));
                break;
        }
    }
}
