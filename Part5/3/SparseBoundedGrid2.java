//package Part5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

public class SparseBoundedGrid2<E> extends AbstractGrid<E> {
    //利用map存储位置和物体的对应
    private Map<Location, E> occupantMap;
    //因为是有界网格，所以有列数
    private int col;
    //因为是有界网格，所以有行数
    private int row;
    //默认构造器，传入数组的行数和列数
    public SparseBoundedGrid2(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
            
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        //新建哈希map
        occupantMap = new HashMap<>();
        col = cols;
        row = rows;
    }
    //返回数的行数
    public int getNumRows()
    {
        return row;
    }
//返回数组的列数
    public int getNumCols()
    {
        return col;
    }
    //判断给定坐标是否在网格内
    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }
    //实现get方法
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<>();
        //利用hashmap的keySet可以得到所有的键值
        for (Location loc : occupantMap.keySet()) {
            a.add(loc);
        }
        return a;
    }
    //实现了get方法
    public E get(Location loc)
    {
        if (loc == null) {
            throw new NullPointerException("loc == null");
       }else if (!isValid(loc)) {
           throw new IllegalArgumentException("Location " + loc
                   + " is not valid");
       }
        //当位置合理后，直接利用hashmap的get方法
        return occupantMap.get(loc);
    }
    //实现put方法
    public E put(Location loc, E obj)
    {
        if (loc == null) {
             throw new NullPointerException("loc == null");
        }else if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }
      //当位置合理后，直接利用hashmap的put方法
        return occupantMap.put(loc, obj);
    }
//实现remove方法
    public E remove(Location loc)
    {   
       if (loc == null) {
            throw new NullPointerException("loc == null");
       }else if (!isValid(loc)) {
           throw new IllegalArgumentException("Location " + loc
                   + " is not valid");
       }
     //当位置合理后，直接利用hashmap的remove方法
       return occupantMap.remove(loc);
        
    }
}
