//package Part5;
//SparseGridNode类代表一个节点
public class SparseGridNode
{
    //表示该节点存的物体（一般是actor)
    private Object occupant;
    //表示该节点的纵坐标
    private int col;
    //表示该节点指向的下一个节点
    private SparseGridNode next;
    //默认构造器，只有两个参数，因为一般构造的时候next是不知道
    public SparseGridNode(Object o, int col) {
        occupant = o;
        this.col = col;
    }
    //返回节点的纵坐标
    public int getCol() {
        return col;
    }
    //设置节点的纵坐标
    public void setCol(int c) {
        col = c;
    }
    //返回节点上的物体
    public Object getOccupant() {
        return occupant;
    }
    //设置节点上的物体
    public void setOccupant(Object o) {
        occupant = o;
    }
    //返回下一个节点
    public SparseGridNode getNext() {
        return next;
    }
    //设置下一个节点
    public void setNext(SparseGridNode s) {
        next = s;
    }
}