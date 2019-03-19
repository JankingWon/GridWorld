package info.gridworld.maze;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import java.awt.Color;

/**
 * This class runs a world that contains maze bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class MazeBugRunner
{
    public static void main(String[] args)
    {
        //构造一个默认的10 * 10 有界网格构成的world
        ActorWorld world = new ActorWorld(); 
        //添加MazeBug
        world.add(new Location(0,0), new MazeBug());
        //添加石头
        world.add(new Location(1,1),new Rock());
        //我觉得要加个终点比较好
        world.add(new Location(9,9),new Rock(Color.RED));
        //显示界面
        world.show();
    }
}