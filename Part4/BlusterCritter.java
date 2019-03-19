package Part4;

import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

public class BlusterCritter extends Critter{
    //定义颜色每次变化的RGB值
    private static final double COLOR_FACTOR = 0.05;
    //区分颜色是变亮还是变暗的变量
    private final int courage;
    //构造器传入一个初始化courage的int值
    public BlusterCritter(int c){
        courage = c;
    }
    //重写了Critter的processActors方法，使其不是吃actor，而是变颜色
    @Override
    public void processActors(ArrayList<Actor> actors)
    {
        
        int n = actors.size();
        //颜色的三元素
        int red;
        int green;
        int blue;
        //获取当前的颜色
        Color c = getColor();
        if (n >= courage ) {
            //通过衰减RGB分量上的值让颜色暗淡
            red = (int)(c.getRed() * (1 - COLOR_FACTOR));
            green = (int) (c.getGreen() * (1 - COLOR_FACTOR) );
            blue = (int) (c.getBlue() * (1 - COLOR_FACTOR) );
            //System.err.print(" substract ");
        }else {
            //通过增加RGB分量上的值让颜色明亮
            red = (int)( (c.getRed() * (1 + COLOR_FACTOR)) > 255 ? 255 : (c.getRed() * (1 + COLOR_FACTOR)));
            green = (int)( (c.getGreen() * (1 + COLOR_FACTOR)) > 255 ? 255 : (c.getGreen()* (1 + COLOR_FACTOR)));
            blue = (int)( (c.getBlue() * (1 + COLOR_FACTOR)) > 255 ? 255 : (c.getBlue() * (1 + COLOR_FACTOR)));
            //System.err.print(" add ");
        }
        //设置改变后的颜色
        setColor(new Color(red, green, blue));
    }
    //重写了Critter的getActors方法，使其能返回周围24个格子里Critter的集合
    @Override
    public ArrayList<Actor> getActors()
    {
        Location loc = getLocation();
        ArrayList<Actor> critters = new ArrayList<Actor>();
        //初始扫描方向为North
        int d = Location.NORTH;
        //用此循环能够扫描所有方向
        for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
        {
            //两个location分别代表某个方向距离一格和距离两格的坐标
            Location neighborLocFirst = loc.getAdjacentLocation(d);
            Location neighborLocSecond =neighborLocFirst.getAdjacentLocation(d);
            //获取坐标里距离为一的元素，如果是Critter类型就加入到critters里面
            if(getGrid().isValid(neighborLocFirst) ) {
                Actor ifCritter = getGrid().get(neighborLocFirst);
                if(ifCritter instanceof Critter ) {
                    critters.add(ifCritter);
                }
            }
          //获取坐标里距离为二的元素，如果是Critter类型就加入到critters里面
            if(getGrid().isValid(neighborLocSecond)) {
                Actor ifCritter = getGrid().get(neighborLocSecond);
                if(ifCritter instanceof Critter){
                    critters.add(ifCritter);
                }
            }
            
            //每次循环递增的单位方向
            d = d + Location.HALF_RIGHT;
        }
        return critters;
    }
    
    
}
