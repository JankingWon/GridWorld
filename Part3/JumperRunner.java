//package Part3;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public class JumperRunner {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        final int x = 0, y = 1;
        //默认构造器构造world
        ActorWorld world = new ActorWorld();
        //构造一个jumper
        Jumper jumper = new Jumper();
        //将该jumper加到world里面
        world.add(new Location(x, y),jumper);
        //显示物体
        world.show();
    }

}
