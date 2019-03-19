package Part4;

import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class RockHoundRunner {

    public static void main(String[] args) {
      //默认构造一个带有有界grid的world
        ActorWorld world = new ActorWorld();
        //添加很多的石头观察RockHound是否能真的”吃“掉他们
        world.add(new Location(3, 3), new Rock(Color.green));
        world.add(new Location(3, 4), new Rock(Color.PINK));
        world.add(new Location(5, 4), new Rock(Color.BLUE));
        world.add(new Location(2, 8), new Rock(Color.BLUE));
        world.add(new Location(5, 5), new Rock(Color.PINK));
        world.add(new Location(1, 5), new Rock(Color.RED));
        world.add(new Location(7, 2), new Rock(Color.YELLOW));
        world.add(new Location(7, 8), new Rock(Color.green));
        //将RockHound添加到world里面去
        world.add(new Location(4, 4), new RockHound());
        world.add(new Location(5, 8), new RockHound());
        //显示GUI
        world.show();

    }

}
