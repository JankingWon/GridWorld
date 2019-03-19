import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
/**
 * @author janking
 *
 */
public final class DancingBugRunner {
	public static void main(String[] args) {
		//magic number
		final int x = 2, y = 4;
		//默认构造器构造world
		ActorWorld world = new ActorWorld();
		//保存DanceBug的每次旋转次数的数组
		int[] turn = new int[5];
		for(int i = 0; i < 5; i++) {
			turn[i] = i;
		}
		//用上面构造的数组构造一个DanceBug
		DancingBug bug = new DancingBug(turn);
		//将该DanceBug加到world里面
		world.add(new Location(x, y),bug);
		//显示物体
		world.show();
	}

}
