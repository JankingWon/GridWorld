import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public final class CircleBugRunner {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//magic number
		final int x = 2, y = 4, side = 3;
		//默认构造器构造world
		ActorWorld world = new ActorWorld();
		//构造一个边长为side的CircleBug
		CircleBug bug = new CircleBug(side);
		//将该CircleBug加到world里面
		world.add(new Location(x, y),bug);
		//显示物体
		world.show();

	}
}
