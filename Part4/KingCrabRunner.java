package Part4;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class KingCrabRunner {
    public static void main(String[] args)
    {
        //默认构造一个带有有界grid的world
        ActorWorld world = new ActorWorld();
        //得到各种的actor类型
        world.add(new Location(7, 5), new Rock());
        world.add(new Location(5, 4), new Rock());
        world.add(new Location(5, 7), new Rock());
        world.add(new Location(7, 3), new Rock());
        world.add(new Location(6, 0), new Rock());
        world.add(new Location(6, 3), new Rock());
        world.add(new Location(7, 8), new Flower());
        world.add(new Location(2, 2), new Flower());
        world.add(new Location(3, 5), new Flower());
        world.add(new Location(3, 8), new Flower());
        world.add(new Location(6, 5), new Bug());
        world.add(new Location(5, 3), new Bug());
        //得到三个不同位置的kingCrab
        world.add(new Location(4, 5), new KingCrab());
        world.add(new Location(6, 1), new KingCrab());
        world.add(new Location(7, 4), new KingCrab());
        //得到界面展示
        world.show();
    }
}
