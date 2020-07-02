package com.dpf.queue;

import java.util.Scanner;

//数组模拟环形队列
public class CircleArrayQueue {
    public static void main(String[] args) {
        CircleArray circlearry = new CircleArray(4);
        char key=' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean flag=true;
        while(flag){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出队列");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):获取队列数据");
            System.out.println("h(head):查看队列头的数据");
            key=scanner.next().charAt(0);
            switch (key) {
                case 's':
                    circlearry.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    circlearry.addQueue(value);
                    break;
                case 'g':
                    try {
                        int cs = circlearry.Queue();
                        System.out.println("取出来的数据为" + cs);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = circlearry.headQueue();
                        System.out.println("队列头的数据为:" + head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    flag = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出！");
    }
}
class CircleArray{
    private int maxsize;//表示数组的最大容量
    private int front=0;  //队列头;指向队列头部,分析出front是指向队列头的第一个位置
    private int rear=0;   //队列尾;指向队列尾部,指向队列尾的数量的后一个位置（即就是队列最后一个数据的后一个位置）
    private int[] arr; //该数据用于存放数据,模拟队列a

    public CircleArray(int arrmaxsize){
        maxsize = arrmaxsize;
        arr = new int[maxsize];
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1)% maxsize == front;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否为满
        if(isFull()){
            System.out.println("队列满,不能加入数据");
            return;
        }
        arr[rear]=n;
        rear=(rear + 1)% maxsize;
    }
    //获取队列的数据
    public int Queue(){
        if(isEmpty()){
            throw new RuntimeException("队列空,不能取数据");
        }
       int value=arr[front];
        front=(front+1) % maxsize;
        return value;
    }
    //显示队列的所有数据
    public  void showQueue(){
        if(isEmpty()){
            System.out.println("队列空的,没有数据");
            return;
        }
        for (int i=front;i<front+size();i++){
            System.out.println("arr["+(i%maxsize)+"]="+arr[i%maxsize]);
        }
    }
    //求出当前队列的有效数据个数
    public int size(){
        return(rear + maxsize - front)%maxsize;
    }
    //显示队列的头数据,注意不是取出数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空的,没有数据");
        }
        return arr[front];
    }

}