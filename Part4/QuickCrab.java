package Part4;

import java.util.ArrayList;

import info.gridworld.grid.Location;

public class QuickCrab extends CrabCritter{
    //重写getMoveLocations方法，使得优先选取距离左右距离为两个空格的位置
    @Override
    public ArrayList<Location> getMoveLocations(){
        //分别代表距离为2和距离为1的空白有效位置集
        ArrayList<Location> twoSpaceLocs = new ArrayList<>();
        ArrayList<Location> oneSpaceLocs = new ArrayList<>();
        //判断左右两边
        int[] dirs =
            { Location.LEFT, Location.RIGHT };
        for (int i = 0; i < 2; i++) {
            //先判断临近的位置
            Location neighborLoc = getLocation().getAdjacentLocation(getDirection() + dirs[i]);
            if (getGrid().isValid(neighborLoc) && getGrid().get(neighborLoc) == null) {
                //再判断临近位置旁边相同方向的临近位置
                Location nneighborLoc = neighborLoc.getAdjacentLocation(getDirection() + dirs[i]);
                //代表该方向两个位置都是有效且空的
                if (getGrid().isValid(nneighborLoc) && getGrid().get(nneighborLoc) == null) {
                    twoSpaceLocs.add(nneighborLoc);
                //代表该方向只有第一个位置有效且空
                }else{
                    oneSpaceLocs.add(neighborLoc);
                }
            }
        }
        //如果距离两个单位的有效且空位置集合不为空则优先选取
        if(!twoSpaceLocs.isEmpty()) {
            return twoSpaceLocs;
        }
        //不管oneSpaceLocs是否为空，
        else {
            return oneSpaceLocs;
        }
            
    }
}
