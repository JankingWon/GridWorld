//package Part5;

import java.util.ArrayList;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

public class UnboundedGrid2<E> extends AbstractGrid<E>{
    //用二维数组存储元素
    private Object[][] occupantArray;
    //代表数组的宽度
    private int bounds;
    //默认构造器，从16的宽度开始
    public UnboundedGrid2()
    {
        bounds = 16;
        occupantArray = new Object[bounds][bounds];
    }
    //返回-1表示是一个无界网格
    public int getNumRows()
    {
        return -1;
    }
    //返回-1表示是一个无界网格
    public int getNumCols()
    {
        return -1;
    }
    //坐标的行数和列数都要大于0
    public boolean isValid(Location loc)
    {
        return (0 <= loc.getRow()&& 0 <= loc.getCol());
    }
    //实现getOccupiedLocations方法
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        //遍历该二维数组
        for (int r = 0; r < bounds; r++)
        {
            for (int c = 0; c < bounds; c++)
            {
                //如果有元素在该位置上
                Location loc = new Location(r, c);
                if (get(loc) != null) {
                    theLocations.add(loc);
                }
                    
            }
        }

        return theLocations;
    }
//实现get方法
    @SuppressWarnings("unchecked")
    public E get(Location loc)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if(loc.getRow() >= bounds || loc.getCol() >= bounds) {
            return null;
        }
        //直接通过数组下标得到
        return (E)occupantArray[loc.getRow()][loc.getCol()];
    }
//实现put方法
    public E put(Location loc, E obj)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }
        if(loc.getRow() >= bounds || loc.getCol() >= bounds) {
            expand(loc);
        }
        //跟原来的有界网格差不多
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }
//实现remove方法
    public E remove(Location loc)
    {
        if(!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if(loc.getRow() >= bounds || loc.getCol() >= bounds) {
            return null;
        }
        //跟原来的有界网格差不多
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
//新建一个数组扩张的方法
    public void expand(Location loc) {
        int newBounds = bounds;
        //数组宽度不停乘以2，直至能够容纳下loc坐标
        while(loc.getCol() >= newBounds || loc.getRow() >= newBounds) {
            newBounds *= 2;
        }
        //把原来的数据复制到临时的数组上
        Object[][] newArray = new Object[newBounds][newBounds];
        for(int i = 0; i < bounds; i++) {
            for(int j = 0; j < bounds; j++) {
                newArray[i][j] = occupantArray[i][j]; 
            }
        }
        //更改bounds以及存储数据的数组
        bounds = newBounds;
        occupantArray = newArray;
    }
    
}
