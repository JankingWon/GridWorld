//package Part3;
import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Jumper extends Actor{
    //默认构造一个黄色jumper
    public Jumper()
    {
        setColor(Color.YELLOW);
    }
    //接收一个颜色参数构造jumper
    public Jumper(Color newColor) {
        setColor(newColor);
    }

    //判断是否能jump（两格）或者能否move（一格）
    public void act()
    {
        //能移动两格的情况
        if (canJump()) {
            jump();
        }
        //能移动一格的情况
        else if(canMove()){
            move();
            turn();
        }
        //不能动的情况
        else {
            turn();
            turn();
        }
            
    }

    //每次转身向顺时针转45度
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
    
    //控制jumper跳动（前进两格）的函数
    public void jump() {
        //获取当前的jumper所在的网格
        Grid<Actor> gr = getGrid();
        //证明jumper不在网格里，结束jumper的行动
        if (gr == null) {return; }
            
        //获取面对的下一个格子
        Location next = getLocation().getAdjacentLocation(getDirection());
        //获取面对的下下一个格子
        Location nnext = next.getAdjacentLocation(getDirection());
        //如果格子不在网格之外，就移动到此处
        if (gr.isValid(nnext)) {moveTo(nnext);}
            
        //否则将移除该jumper
        else
            removeSelfFromGrid();
    }
    
    //判断jumper能否跳动（前进两格）的函数
    public boolean canJump() {
        //获取当前的jumper所在的网格
        Grid<Actor> gr = getGrid();
        //证明jumper不在网格里，判断jumper不能动
        if (gr == null) {return false; }
            
        //获取面对的下一个格子
        Location next = getLocation().getAdjacentLocation(getDirection());
        //获取面对的下下一个格子
        Location nnext = next.getAdjacentLocation(getDirection());
        //如果格子不在网格之外，判断jumper不能动
        if (!gr.isValid(nnext)) {return false; }
            
        //获取下下格子上的actor
        Actor nneighbor = gr.get(nnext);
        //如果为空或者是花就可以移动
        return (nneighbor == null) || (nneighbor instanceof Flower);
    }
    
    //控制jumper移动（前进一格）的函数
    public void move()
    {
        //获取当前的jumper所在的网格
        Grid<Actor> gr = getGrid();
        //证明jumper不在网格里，结束jumper的行动
        if (gr == null) {return;}
            
        //获取面对的下一个格子
        Location next = getLocation().getAdjacentLocation(getDirection());
        //如果格子不在网格之外，就移动到此处
        if (gr.isValid(next)) {moveTo(next);}
            
        //否则将移除该jumper
        else
            removeSelfFromGrid();
    }

    //判断jumper是否能移动（前进一格）的函数
    public boolean canMove()
    {
        //获取当前的jumper所在的网格
        Grid<Actor> gr = getGrid();
        //证明jumper不在网格里，判断jumper不能动
        if (gr == null) {
            return false;
        } 
        //获取面对的下一个格子
        Location next = getLocation().getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) {
            return false;
        }
            
        //获取下格子上的actor
        Actor neighbor = gr.get(next);
        //如果为空或者是花就可以移动
        return (neighbor == null) || (neighbor instanceof Flower);
    }
}
