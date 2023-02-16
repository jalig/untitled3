import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList arrayList = new MyArrayList(5);
        MyArrayList arrayList2 = new MyArrayList(5);

        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");

        arrayList2.add("1");
        arrayList2.add("2");
        arrayList2.add(null);

        System.out.println(arrayList.equals(arrayList2));

//        System.out.println(arrayList);

//        arrayList.clear();

//        System.out.println(arrayList);
//        System.out.println(Arrays.toString(arrayList.toArray()));
//        arrayList.add(2, "5");
//        arrayList.remove(4);
//        arrayList.set(2,"gg");
//        arrayList.remove("gg");
//        arrayList.add(1, "6");
//        arrayList.add(0, "7");
//        arrayList.remove(2);
//        System.out.println(arrayList.indexOf("29"));
//        System.out.println(arrayList.get(2));
//        System.out.println(arrayList.lastIndexOf("3"));
//        System.out.println(arrayList);
//        System.out.println(arrayList.size());

    }
}