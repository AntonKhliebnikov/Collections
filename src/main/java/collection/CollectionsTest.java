package collection;

import collection.my_list.MyArrayList;
import collection.my_list.MyList;

public class CollectionsTest {
    public static void main(String[] args) {
        MyList list = new MyArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(6);
        list.add(6);
        list.add(9);
        list.add(10);
        list.add(11);
        list.add(4, 22);

        System.out.println("list.size() = " + list.size());
        System.out.println(list);

        System.out.println("list.remove(11) = " + list.remove(11));
        System.out.println("list.size() = " + list.size());
        System.out.println(list);



//        MyLinkedList linkedList = new MyLinkedList();
//        linkedList.add(1);
//        linkedList.add(2);
//        linkedList.add(4);
//        linkedList.add(4);
//        linkedList.add(4);
//        linkedList.add(6);
//
//        System.out.println(linkedList);
//        System.out.println("linkedList.size() = " + linkedList.size());
//
//        System.out.println("linkedList.isEmpty() = " + linkedList.isEmpty());
//        System.out.println("linkedList.get(4) = " + linkedList.get(4));
//        System.out.println("linkedList.contains(7) = " + linkedList.contains(7));
//        System.out.println("linkedList.indexOf(4) = " + linkedList.indexOf(4));
//        System.out.println("linkedList.lastIndexOf(4) = " + linkedList.lastIndexOf(4));
//
//        linkedList.clear();
//       System.out.println(linkedList);
//        System.out.println("linkedList.size() = " + linkedList.size());
//
//        linkedList.add(0, 22);
//        System.out.println(linkedList);
//        System.out.println("linkedList.size() = " + linkedList.size());
//
//        linkedList.add(55,33);
//        System.out.println(linkedList);
//        System.out.println("linkedList.size() = " + linkedList.size());
//
//       System.out.println("linkedList.remove(4) = " + linkedList.remove(4));
//       System.out.println(linkedList);
//        System.out.println("linkedList.size() = " + linkedList.size());
//
//        System.out.println("linkedList.peek() = " + linkedList.peek());
//        System.out.println(linkedList);
//        System.out.println("linkedList.size() = " + linkedList.size());
    }
}
