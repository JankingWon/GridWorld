import info.gridworld.actor.Bug;

public class CircleBug extends Bug{
	//代表已走的步数
	private int steps;
	//代表边长
    private int sideLength;

    /**
     * Constructs a circle bug that traces a square of a given side length
     * @param length the side length
     */
    public CircleBug(int length)
    {
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
    	//判断是否能移动
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
        	//如果不能移动就转一次而不是像BoxBug转两次
            turn();
            steps = 0;
        }
    }
}
