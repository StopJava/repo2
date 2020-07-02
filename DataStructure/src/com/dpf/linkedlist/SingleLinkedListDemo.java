package com.dpf.linkedlist;

import javax.xml.transform.sax.SAXSource;
import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {

        //进行测试
        //先创建节点
        HeroNode here1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode here2 = new HeroNode(2, "卢俊义", "王麒麟");
        HeroNode here3 = new HeroNode(3, "吴用", "智多星");
        HeroNode here4 = new HeroNode(4, "林冲", "豹子头");
        //创建初始列表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
/*        singleLinkedList.add(here1);
        singleLinkedList.add(here2);
        singleLinkedList.add(here3);
        singleLinkedList.add(here4);*/
        System.out.println("初始链表为:");
        singleLinkedList.addorderby(here1);
        singleLinkedList.addorderby(here4);
        singleLinkedList.addorderby(here3);
        singleLinkedList.addorderby(here2);
        singleLinkedList.list();
       // System.out.println("反转链表为:");
       // reversetList(singleLinkedList.getHead());
       // singleLinkedList.list();
        System.out.println("反向链表为:");
        reversePrint(singleLinkedList.getHead());
        // singleLinkedList.deleteNode(3);
        // HeroNode newheroNode = new HeroNode(2,"武松","武僧");
        // singleLinkedList.update(newheroNode);


        //System.out.println("该链表节点数为"+getLength(singleLinkedList.getHead()));
        //HeroNode Res = getNode(singleLinkedList.getHead(),2);
        //System.out.println("res="+Res);
    }

    //获取到单链表的节点个数（如果是带头节点的链表.需求不统计头结点)
    public static int getLength(HeroNode head) {

        if (head.next == null) {
            return 0;
        }
        HeroNode cur = head.next;
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //获取倒数第index个的节点[新浪面试题]
    public static HeroNode getNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        int size = getLength(head);
        HeroNode cur = head.next;
        if (cur.no < 0 || cur.no > size) {
            return null;
        }
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //将单链表反转[腾讯面试题]
    public static void reversetList(HeroNode head) {
        //如果当前链表为空,或者只有一个节点,无需反转,直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助的指针（变量）,帮助我们遍历原来的链表
        HeroNode temp = head.next;
        HeroNode reverseHead = new HeroNode(0,"","");
        HeroNode next = null;
        while (temp != null) {
            next = temp.next;
            temp.next = reverseHead.next;
            reverseHead.next = temp;
            temp = next;
        }
        head.next = reverseHead.next;
    }

    //将单链表反向打印
    //利用栈的思想,先进后出,后进先出
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        //创建一个栈,将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        //将链表的所有节点压入栈
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        //将栈中的节点打印出来
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}
    class SingleLinkedList {
        public HeroNode getHead() {
            return head;
        }

        //先初始化一个头结点,头结点不要动
        public HeroNode head = new HeroNode(0, "", "");

        public void add(HeroNode herNode) {
            //因为head节点不能动,因此我们需要一个辅助遍历temp
            HeroNode temp = head;
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
            //将最后这个节点的next指向新的节点
            temp.next = herNode;
        }

        //通过排序添加
        public void addorderby(HeroNode heroNode) {
            boolean flag = false;
            HeroNode temp = head;
            while (true) {
                if (temp.next == null) {
                    break;
                }
                if (temp.next.no > heroNode.no) {
                    break;
                } else if (temp.next.no == heroNode.no) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if (flag) {
                System.out.println("已存在" + heroNode + "号这个数据");
            } else {
                heroNode.next = temp.next;
                temp.next = heroNode;
            }
        }

        //修改链表
        public void update(HeroNode heroNode) {
            //如果链表为空
            boolean flag = true;
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            HeroNode temp = head.next;
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

        //删除链表中的节点
        public void deleteNode(int n) {
            boolean flag = false;
            if (head.next == null) {
                System.out.println("该列表为空！");
                return;
            }
            HeroNode temp = head.next;
            while (true) {
                if (temp == null) {
                    break;
                }
                if (temp.next.no == n) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if (flag) {
                temp.next = temp.next.next;
                return;
            } else {
                System.out.println("该节点不存在");
                return;
            }
        }

        //显示链表[遍历]
        public void list() {
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            //因为头结点,不能动,因此我们需要一个辅助变量来遍历
            HeroNode temp = head.next;
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

    }

    class HeroNode {
        public int no;
        public String name;
        public String nickname;
        public HeroNode next;   //指向下一个节点


        public HeroNode(int no, String name, String nickname) {
            this.no = no;
            this.name = name;
            this.nickname = nickname;
        }

        @Override
        public String toString() {
            return this.no + "," + this.name + "," + this.nickname;
        }
    }



