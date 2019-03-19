package Part4;

import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

public class BlusterCritterRunner {

    public static void main(String[] args) {
        //默认构造一个带有有界grid的world
        ActorWorld world = new ActorWorld();
        //构造一系列的Critter
        Critter aCritter = new Critter();
        Critter bCritter = new Critter();
        Critter cCritter = new Critter();
        Critter dCritter = new Critter();
        Critter eCritter = new Critter();
        Critter fCritter = new Critter();
        Critter gCritter = new Critter();
        Critter hCritter = new Critter();
        //为了观察到颜色的变化，设置不同颜色的KingCrab
        aCritter.setColor(Color.green);
        bCritter.setColor(Color.pink);
        cCritter.setColor(Color.LIGHT_GRAY);
        dCritter.setColor(Color.yellow);
        eCritter.setColor(Color.GRAY);
        fCritter.setColor(Color.red);
        gCritter.setColor(Color.cyan);
        hCritter.setColor(Color.black);
        //把这些KingCrab放到world里面去
        world.add(new Location(3, 3), aCritter);
        world.add(new Location(3, 4), bCritter);
        world.add(new Location(5, 4), cCritter);
        world.add(new Location(2, 8), dCritter);
        world.add(new Location(5, 5), eCritter);
        world.add(new Location(1, 5), fCritter);
        world.add(new Location(7, 2), gCritter);
        world.add(new Location(7, 8), hCritter);
        world.add(new Location(4, 4), new BlusterCritter(2));
        world.add(new Location(5, 8), new BlusterCritter(6));
        //显示GUI
        world.show();
    }

}
