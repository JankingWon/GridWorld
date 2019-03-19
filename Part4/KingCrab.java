package Part4;

import java.util.ArrayList;

import com.sun.org.glassfish.gmbal.NameValue;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class KingCrab extends CrabCritter{
    //重写了Critter的processActors方法，用来移动或清除得到的actors列表
    @Override
    public void processActors(ArrayList<Actor> actors)
    {
        //操作已经得到的actor集合
        for (Actor a : actors)
        {
            Grid<Actor> gr = getGrid();
            Location loc = a.getLocation();
            //得到从KingCrab到该actor的方向
            int direction = getLocation().getDirectionToward(loc);
            //得到该actor在该方向上的下一个格子的坐标
            Location next = a.getLocation().getAdjacentLocation(direction);
            //如果该坐标有效且为空就移动到此次
            if (gr.isValid(next) && gr.get(next) == null) {
                a.moveTo(next);
            //否则，该actor将被移除
            }else {
                a.removeSelfFromGrid();
            }
        }
    }
}
