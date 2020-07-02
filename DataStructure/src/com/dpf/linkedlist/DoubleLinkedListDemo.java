package com.dpf.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        HeroNode2 here1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 here2 = new HeroNode2(2, "卢俊义", "王麒麟");
        HeroNode2 here3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 here4 = new HeroNode2(4, "林冲", "豹子头");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(here1);
        doubleLinkedList.add(here2);
        doubleLinkedList.add(here3);
        doubleLinkedList.add(here4);
        doubleLinkedList.deleteNode(1);
        doubleLinkedList.list();
    }
}
//创建一个双链表
class DoubleLinkedList{
    //先初始化一个头结点,头结点不要动
    public HeroNode2 head = new HeroNode2(0, "", "");
    //返回头结点
    public HeroNode2 getHead(){
        return head;
    }
    //遍历双向链表的方法
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头结点,不能动,因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //对temp后移,一定小心
            temp = temp.next;
        }
    }
    //添加一个节点到最后
    public void add(HeroNode2 herNode) {
        //因为head节点不能动,因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;
        //遍历链表
        while (true) {
            //找到链表尾部
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后,将temp后移
            temp = temp.next;
        }
        //当退出while循环时,temp就指向了链表的最后
        temp.next = herNode;
        herNode.pre = temp;
    }
    //修改一个节点的内容
    public void update(HeroNode2 heroNode) {
        //如果链表为空
        boolean flag = true;
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            //到了链表尾部
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                flag = false;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("无法查询" + heroNode + "数据");
            return;
        } else {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
            System.out.println(temp);
        }
    }
    //删除双链表中的节点
    public void deleteNode(int n) {
        boolean flag = false;
        if (head.next == null) {
            System.out.println("该列表为空！");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == n) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            //如果是最后一个节点,就不需要执行下面这句话
            //temp.next=null null.pre=null
            if(temp.next!=null) {
                temp.next.pre = temp.pre;
                return;
            }
        } else {
            System.out.println("该节点不存在");
            return;
        }
    }

}
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;   //指向下一个节点
    public HeroNode2 pre;    //指向前一个节点

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return this.no + "," + this.name + "," + this.nickname;
    }
}