package com.xj.sparsearray;
/*
* 稀疏数组
* */
public class SparseArray {
    public static void main(String[] args) {
        int chessArr1[][] = new int[11][11];
        chessArr1[1][1] = 1;
        chessArr1[2][3] = 2;
        //计算有效值个数
        int sum = 0;
        for (int i=0;i<chessArr1.length;i++){
            for (int j=0;j<chessArr1[0].length;j++){
                //输出二维数组
                System.out.printf("%d\t",chessArr1[i][j]);
                if(chessArr1[i][j] != 0){
                    sum++;
                }
            }
            System.out.println();
        }
        //创建稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;
        //用于计算稀疏数组的行
        int count = 0;
        for (int i=0;i<chessArr1.length;i++){
            for (int j=0;j<chessArr1[0].length;j++){
                if(chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println("稀疏数组：");
        for (int i=0;i<sparseArr.length;i++){
            for (int j=0;j<sparseArr[0].length;j++){
                System.out.printf("%d\t",sparseArr[i][j]);
            }
            System.out.println();
        }
        //将稀疏数组转化为二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //给二维数组赋值
        for(int i=1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        for (int i=0;i<chessArr2.length;i++){
            for (int j=0;j<chessArr2[0].length;j++){
                //输出二维数组
                System.out.printf("%d\t",chessArr1[i][j]);
                if(chessArr2[i][j] != 0){
                    sum++;
                }
            }
            System.out.println();
        }
    }
}
