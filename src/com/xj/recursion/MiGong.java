package com.xj.recursion;

public class MiGong {
    public static void main(String[] args) {
        //创建地图,0表示没走过可以走，1表示墙，2表示走过，3表示
        int[][] map = new int[8][7];
        for (int i=0;i<7;i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i=0;i<8;i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }
        for (int i=1;i<3;i++){
            map[3][i] = 1;
        }
        for (int i=0;i<8;i++){
            for (int j=0;j<7;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        setWay(map,1,1);
        System.out.println("走过之后的地图");
        for (int i=0;i<8;i++){
            for (int j=0;j<7;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     *如果小球能到（6,5）的位置说明通路找到，
     * 当map[i][j]为0，说明该点没有走过，1表示墙，2表示通路可以走，3表示该点已走过但是走不通
     * 上->右->下->左
     * @param map 地图
     * @param i 开始的坐标
     * @param j 开始的坐标
     * @return
     */
    public static boolean setWay(int[][] map,int i,int j){
        if(map[6][5] == 2){
            return true;
        }else {
            if (map[i][j] == 0){
                map[i][j] = 2;
                if (setWay(map,i-1,j)){
                    return true;
                }else if (setWay(map,i,j+1)){
                    return true;
                }else if (setWay(map,i+1,j)){
                    return true;
                }else if (setWay(map,i,j-1)){
                    return true;
                }else {
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }
}
