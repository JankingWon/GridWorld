package Part4;

import java.awt.Color;
import java.util.ArrayList;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
//继承ChameleonCritter类，为了能正常运行，我把project里面的ChameleonCritter.java复制到了这个package了
public class ModifiedChameleonCritter extends ChameleonCritter {
    //定义颜色衰减的RGB值
    private static final double DARKENING_FACTOR = 0.05;
    //这是为了屏蔽Critter的processActors方法
    @Override
    public void processActors(ArrayList<Actor> actors)
    {
        
        int n = actors.size();
        if (n == 0) {
            Color c = getColor();
            //通过衰减RGB分量上的值让颜色暗淡
            int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
            setColor(new Color(red, green, blue));
            return;
        }
        //随机决定变成哪个Actor的颜色
        int r = (int) (Math.random() * n);
        Actor other = actors.get(r);
        setColor(other.getColor());
    }
}
