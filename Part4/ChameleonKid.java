package Part4;

import java.awt.Color;
import java.util.ArrayList;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
//继承ChameleonCritterCritter类
public class ChameleonKid extends ModifiedChameleonCritter{
    //现在需要的到的是Critter前后的actors元素，所以要重写父类getActors方法
    @Override
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        //方向集合为前面和后面
        int[] dirs = { Location.AHEAD, Location.HALF_CIRCLE};
        for (int d : dirs)
        {
            //调用Location的getAdjacentLocation得到指定方向上的坐标
            Location neighborLoc = getLocation().getAdjacentLocation(getDirection() + d);
            //判断该位置是否在网格内且有效
            if (getGrid().isValid(neighborLoc) && getGrid().get(neighborLoc) != null)
                actors.add(getGrid().get(neighborLoc));
        }
        return actors;
    }
    
}
