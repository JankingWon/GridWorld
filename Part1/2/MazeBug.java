package info.gridworld.maze;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
    //记录下一步要走的位置
	public Location next;
	//记录上一步的位置(一般就是当前的位置,因为移动到next的位置之后,当前位置就成了上一个位置)
	public Location last;
	//判断是否走到了终点
	public boolean isEnd = false;
	//记录树的节点的栈,用Arraylist来存储是可以把顶上的元素周围可以移动的位置也放进栈中
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	//记录移动的步数
	public Integer stepCount = 0;
	//判断提示信息是否已经输出(失败或者成功)
	boolean hasShown = false;
	//记录已经访问的位置
	public ArrayList<Location> visited = new ArrayList<>();
	//记录四个方向的可能性(方向分别是 上,右,下,左;用int存储的是该方向选择成功的次数,次数越高,可能性越大(可能性为相应的整数除以数组元素的总和))
	public int probability[] = new int[4];
	//MazeBug的构造器
	public MazeBug() {
		setColor(Color.GREEN);
		//设置起始点
		last = new Location(0, 0);
		//将当前位置压入栈中
		ArrayList<Location> temp = new ArrayList<>();
		temp.add(last);
		crossLocation.push(temp);
		//将当前位置标记为已访问
		visited.add(last);
		//将四个方向的可能性设置为相同
		for(int i = 0; i < 4; i++) {
		    probability[i] = 1;
		}
	}
	
	//判断loc位置是否已经访问过
	boolean ifVisited(Location loc) {
	    for(Location l : visited) {
	        if(l.equals(loc)) {
	            return true;
	        }
	            
	    }
	    return false;
	}
	
	//每次MazeBug的行为
	@Override
	public void act() {
	    //避免程序过对运行
	    if(hasShown) {
	        return;
	    }
	        
	    //首先将栈顶的Arraylist元素处理好
	    getValid(getLocation());
	    
		boolean willMove = canMove();
		if (isEnd) {
		//到达终点的提示信息		
			String msg = stepCount.toString() + " steps";
			JOptionPane.showMessageDialog(null, msg);
			hasShown = true;
		} else if (willMove) {
		    //willsteps记录了当前的位置和能够移动的未访问的周边位置
		    ArrayList<Location> willsteps = crossLocation.peek();
		    //获取随机数
		    double r = Math.random();
		    int sum = 0;
		    //概率数组 如果probability里面为 1 2 3 2
		    //         则p为               0 1 3 6
		    //表示在[0,8(sum))区间里,随机数落在[0,1)的概率就是选择第一个元素的概率,落在[1,3)的概率就是选择第二个元素的概率
		    ArrayList<Integer> p = new ArrayList<>();
		    //将willsteps中能移动的下一个位置的坐标转化为到下一个位置的方向
		    ArrayList<Integer> dir  = new ArrayList<>();
		    for(int i = 1; i < willsteps.size(); i ++) {
		        int tempDir = getLocation().getDirectionToward(willsteps.get(i));
		        dir.add(tempDir);
		        p.add(sum);
		        sum += probability[tempDir / 90];
		    }
		    //通过扩大随机数的范围(* sum)得到选择的方向
		    for(int i = 0; i < p.size(); i++) {
		        if(r * sum >= p.get(i)) {
		            next = willsteps.get(1 + i);
		            setDirection(dir.get(i));
		        }
		            
		    }
		    //保存当前位置
		    last = getLocation();
		    //Direction是0, 90, 180, 270分别映射到数组下标为0,1,2,3的元素中
		    probability[getDirection() / 90] += 1;
		    //将即将要移动至的位置设为已访问
		    visited.add(next);
		    //将下一个位置压入栈中
		    ArrayList<Location> temp = new ArrayList<>();
		    temp.add(next);
		    crossLocation.push(temp);
		    //移动
			move();
			//移动步数加一
			stepCount++;
		} else {
		    //将栈顶元素推出,栈顶元素就是记录当前位置以及能移动的位置
		    crossLocation.pop();
		    //如果不能移动且栈中只剩自己了表明无法到达终点
		    if(crossLocation.empty()) {
		        String msg = " Falied!";
	            JOptionPane.showMessageDialog(null, msg);
	            hasShown = true;
	            return;
		    }
		    //记录前一个位置的链表
		    ArrayList<Location> temp = crossLocation.pop();
		    //因为前一个位置放进去的时候还记录了当时可以移动的位置,现在需要移去它们只留下单独的上一次走的位置
		    ArrayList<Location> newPeek = new ArrayList<>();
		    newPeek.add(temp.get(0));
		    crossLocation.push(newPeek);
		    next = temp.get(0);
		    last = getLocation();
		    //因为这是在做倒退移动,所以从之前位置移动到当前位置的方向可能性要降低
		    if (probability[next.getDirectionToward(last) / 90] > 1) {
		        probability[next.getDirectionToward(last) / 90] -=1;
            }
		    //设置方向并移动
		    setDirection(getLocation().getDirectionToward(next));
		    move();
		    //也要增加移动步数
		    stepCount++;
        }
	}

	//主要是处理栈顶元素,将栈顶的数组扩充为包括首个位置周围可以移动且没有访问过的位置
	public ArrayList<Location> getValid(Location location) {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
		    return (ArrayList<Location>) Collections.<Location>emptyList();
		}
		//先把栈顶元素推出来进行操作
		ArrayList<Location> valid = crossLocation.pop();
		//只能向这四个方向移动
		int[] dirs = 
            { Location.NORTH , Location.EAST , Location.SOUTH, Location.WEST};
        for (int d : dirs)
        {
            Location neighborLoc = location.getAdjacentLocation(d);
            //可以移动
            if (gr.isValid(neighborLoc) && (gr.get(neighborLoc) == null || gr.get(neighborLoc) instanceof Flower) && !ifVisited(neighborLoc) ) {
                valid.add(neighborLoc);
            }
            //到达终点
            if (gr.isValid(neighborLoc) && gr.get(neighborLoc) instanceof Rock && gr.get(neighborLoc).getColor().equals(Color.RED)) {
                isEnd = true;
            }
                
        }
        crossLocation.push(valid);
		return valid;
	}

	//判断是否能够移动到新的位置
	@Override
	public boolean canMove() {
	    return crossLocation.peek().size() > 1;
	    
	}
	//移动操作,其实主要的判断都在act方法里面了,这里是直接向面对方向移动一格,因为方向也已经调整好了
	@Override
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
		    return;
		}
		//再次确认位置前方是否能移动
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else
			removeSelfFromGrid();
		//移动完之后在原来位置留下花朵
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, last);
	}
}
