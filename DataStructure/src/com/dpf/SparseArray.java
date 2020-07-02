package com.dpf;


import java.io.*;


//稀疏数组
public class SparseArray {
    public static void main(String[] args) throws IOException {
        //创建二维数组
        //1代表黑棋
        //2代表白棋
        int [][] arr = new int [11][11];
        arr[1][2]=1;
        arr[2][3]=2;
        for(int [] i:arr){
            for(int Data:i){
                System.out.print(Data+"\t");
            }
            System.out.println();
        }
        int sum=0;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++){
                if(arr[i][j]!=0){
                    sum++;
                }
            }
        }
        System.out.println("sum="+sum);
        //将二维数组转化为稀疏数组
        int [][] SparseArray =new int [sum+1][3];
        int count=0;
          SparseArray[0][0] =11;
          SparseArray[0][1] =11;
          SparseArray[0][2] =sum;

         for(int i=0;i<arr.length;i++){
             for(int j=0;j<arr.length;j++){
                 if(arr[i][j]!=0){
                     count++;
                     SparseArray[count][0]=i;
                     SparseArray[count][1]=j;
                     SparseArray[count][2]=arr[i][j];
                 }
             }
         }
        System.out.println();
        System.out.println("转化为稀疏数组~");
         for(int i=0;i<SparseArray.length;i++){
             for(int j=0;j<SparseArray.length;j++){
                 System.out.print(SparseArray[i][j]+"\t");
             }
             System.out.println();
         }
        //将稀疏数组写入磁盘data.txt
        File file = new File("data.txt");
        BufferedWriter bw=new BufferedWriter(new FileWriter(file));
        for(int i=0;i<SparseArray.length;i++){
            for(int j=0;j<SparseArray.length;j++){
                bw.write(SparseArray[i][j]+"\t");
                bw.flush();
            }
            bw.write("\n");
        }
        bw.close();
        System.out.println("---------");
        //将稀疏数组从磁盘中读出
        BufferedReader br = new BufferedReader(new FileReader(file));
        int len=0;
        char [] ch=new char[1024];
        while((len=br.read(ch))!=-1){
            System.out.print(new String(ch,0,len));
        }
        System.out.println();
        br.close();
        //将稀疏数组还原成二维数组
        int [][] arr2=new int[SparseArray[0][0]][SparseArray[0][1]];
        for(int i=1;i<SparseArray.length;i++){
                arr2[SparseArray[i][0]][SparseArray[i][1]]=SparseArray[i][2];
        }
        //遍历二维数组
        for(int i=0;i<arr2.length;i++){
            for(int j=0;j<arr2.length;j++){
                System.out.print(arr2[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
