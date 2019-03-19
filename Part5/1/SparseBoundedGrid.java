//package Part5;
import java.util.ArrayList;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

public class SparseBoundedGrid<E> extends AbstractGrid<E> {
    //用数组来存储SparseGridNode类
    private SparseGridNode[] sparseArray;
    //表示数组的一维长度，即网格的行数
    private int col;
    //默认构造器
    public SparseBoundedGrid(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        sparseArray = new SparseGridNode[rows];
        col = cols;
    }
    //返回网格的行数
    public int getNumRows()
    {
        return sparseArray.length;
    }
//    返回网格的列数
    public int getNumCols()
    {
        return col;
    }
    //判断给定的坐标是否在网格规定的范围内
    public boolean isValid(Location loc)
    {
        return (0 <= loc.getRow() && loc.getRow() < getNumRows()&& 
                0 <= loc.getCol() && loc.getCol() < getNumCols() );
    }
//实现了getOccupiedLocations方法    
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<>();
        // 遍历数组
        for (int r = 0; r < getNumRows(); r++)
        {
            //遍历SparseGridNode 的链表
            SparseGridNode node = sparseArray[r];
            while(node != null) {
                theLocations.add(new Location(r, node.getCol()));
                node = node.getNext();
            }
        }
        return theLocations;
    }
    //实现了get方法
    @SuppressWarnings("unchecked")
    public E get(Location loc)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
      //遍历SparseGridNode 的链表
        SparseGridNode node = sparseArray[loc.getRow()];
        while(node != null) {
            if(node.getCol() == loc.getCol()) {
                return (E) node.getOccupant();
            }
            node = node.getNext();
        }
        return null;
    }
//实现了put方法
    public E put(Location loc, E obj)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc+ " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }
        //删掉原网格位置的元素之后，把新的元素加到该行链表的头部
        E oldOccupant = remove(loc);
        SparseGridNode newNode = new SparseGridNode(obj, loc.getCol());
        newNode.setNext(sparseArray[loc.getRow()]);
        sparseArray[loc.getRow()] = newNode;
        
        return oldOccupant;
    }
//实现了remove方法
    public E remove(Location loc)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        //先判断该行的链表是够为空
        SparseGridNode node = sparseArray[loc.getRow()];
        if(node == null) {
            return null;
        }
        //这是该行存储元素的链表的头部就是要找的元素的情况
        if(node.getCol() == loc.getCol()) {
            @SuppressWarnings("unchecked")
            E oldOccupant = (E) node.getOccupant();
            sparseArray[loc.getRow()] = node.getNext();
            return oldOccupant;
        }
        //nodeNext和node一起寻找需要找的坐标，node是为了找了之后能得到该元素在链表之前一个位置的元素
        SparseGridNode nodeNext = node.getNext();
        while(nodeNext != null) {
            if(nodeNext.getCol() == loc.getCol()) {
                @SuppressWarnings("unchecked")
                //通过链表之间的关系间接地达到删除的目的
                E oldOccupant = (E) nodeNext.getOccupant();
                node.setNext(nodeNext.getNext());
                return oldOccupant;
            }
            //继续遍历
            node = node.getNext();
            nodeNext = nodeNext.getNext();
        }
        return null;
        
    }
}
