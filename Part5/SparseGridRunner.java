//package Part5;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

/**
 * This class runs a world with additional grid choices.
 */
public class SparseGridRunner
{
    public static void main(String[] args)
    {
        final int x = 2, y = 2;
        ActorWorld world = new ActorWorld();
        world.addGridClass("Part5.SparseBoundedGrid");
        world.addGridClass("Part5.SparseBoundedGrid2");
        world.addGridClass("Part5.UnboundedGrid2");
        world.add(new Location(x, y), new Critter());
        world.show();
    }
}
