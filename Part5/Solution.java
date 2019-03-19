package solution;

import java.util.ArrayList;
import jigsaw.Jigsaw;
import jigsaw.JigsawNode;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {

    /**
     * 拼图构造函数
     */
    public Solution() {
    }

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
        //将参数传递给父类的相应变量
        beginJNode = bNode;
        endJNode = eNode;
        //表示下一步要访问的节点
        ArrayList<JigsawNode> open = new ArrayList<>();
        //表示已访问的节点
        ArrayList<JigsawNode> close = new ArrayList<>();
        //先把开始节点添加进去
        open.add(bNode);
        //直到待访问列表为空
        while(!open.isEmpty() ){
            //记录原始的大小,因为如果插入新的待访问节点,就会导致Arraylist大小变化
            int openSize = open.size();
            //查找待访问的节点
            for(int i = 0; i < openSize;i++) {
                currentJNode = open.get(0);
                //当前节点跟结束节点相等,则查找成功,结束
                if(currentJNode.equals(eNode)) {
                    return true;
                }
                
                for(int j = 0; j < 4; j++) {
                    if(currentJNode.canMove()[j] == 1) {
                        //得到当前节点的一个副本
                        JigsawNode temp = new JigsawNode(currentJNode);
                        temp.move(j);
                        //确保移动后的节点没有被访问过,就加入到待访问列表中
                        if(!close.contains(temp)) {
                            open.add(temp);
                        }
                    }
                }
                //将当前节点标记为已访问
                close.add(currentJNode);
                //并且从待访问列表中删除该节点
                open.remove(0);
            }
        }
        return false;
    }
    
    
    
    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
     // 后续节点不正确的数码个数
        int s1 = 0; 
        //得到拼图的维度
        int dimension = JigsawNode.getDimension();
        for (int i = 1; i < dimension * dimension; i++) {
            //如果后续节点不是当前节点的数字加一
            if (jNode.getNodesState()[i] + 1 != jNode.getNodesState()[i + 1]) {
                s1++;
            }
        }
      //放错位的数码的个数
        int s2 = 0;
        int currentNodeState[] = jNode.getNodesState();
        int endNodeState[] = endJNode.getNodesState();
        for (int i = 1; i <= dimension * dimension; i++){
            if (currentNodeState[i] != endNodeState[i]) {
                s2++;
            }
        }
        
      //所有 放错位的数码与其正确位置的距离 之和
        int s3 = 0;
        int row1 = 0, col1 = 0, row2 = 0, col2 = 0;
        for (int i = 1; i <= dimension * dimension; i++){
             if (currentNodeState[i] != 0 && currentNodeState[i] != endNodeState[i]){
                 //row1和col1表示放错位的数码坐标
                 //这里是从一维数组的下标转换为行和列的二维坐标
                   row1 = (int) (i - 1) / dimension;
                   col1 = (int) (i + dimension - 1) % dimension;
                   for (int j = 1; j <= dimension * dimension; j++){
                        if (currentNodeState[i] == endNodeState[j]){
                            //row2和col2表示正确数码的位置坐标
                             row2 = (int) (j - 1) / dimension;
                             col2 = (int) (j + dimension - 1) % dimension;
                             //这里用的是曼哈顿距离
                             s3 += (Math.abs(row2 - row1) + Math.abs(col2 - col1));
                             break;
                        }
                   }
             }
        }
        //默认权值是2,0,3
        jNode.setEstimatedValue(2 * s1 + 0 * s2 + 3 *s3);
    }
}
  