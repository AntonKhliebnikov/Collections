package collection.my_list;

public class ListTest {
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
//        System.out.println("list.isEmpty() = " + list.isEmpty());
//        System.out.println("list.contains(5) = " + list.contains(5));
//        System.out.println("list.contains(9) = " + list.contains(9));


//        System.out.println("list.size() = " + list.size());
//        System.out.println(list);

//        System.out.println("list.remove(22) = " + list.remove(22));
//        list.clear();
//        System.out.println("list.isEmpty() = " + list.isEmpty());
//        System.out.println("list.get(0) = " + list.get(0));


        System.out.println("list.size() = " + list.size());
        System.out.println(list);
//        System.out.println("list.indexOf(22) = " + list.indexOf(22));
//        System.out.println("list.indexOf(6) = " + list.indexOf(6));
//        System.out.println("list.indexOf(15) = " + list.indexOf(15));

        System.out.println("list.lastIndexOf(6) = " + list.lastIndexOf(6));
        System.out.println("list.lastIndexOf(23) = " + list.lastIndexOf(23));
        System.out.println("list.lastIndexOf(2) = " + list.lastIndexOf(2));
    }
}
