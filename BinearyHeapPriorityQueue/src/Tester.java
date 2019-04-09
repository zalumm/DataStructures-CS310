import data_structures.*;
import java.util.concurrent.TimeUnit;

public class Tester {
  private PriorityQueue<Integer> list;

  public Tester() {
    list = new BinaryHeapPriorityQueue<>(10);
    runTests();
  }

  private void runTests() {
    int ticker = 0;
      list.insert(0);
      list.insert(1);
      list.insert(2);
      list.insert(3);
      list.insert(4);
      list.insert(5);
      list.insert(6);
      list.insert(7);
      list.insert(8);
      list.insert(9);

      for (int i = 0 ; i < 9 ; i++){
         boolean x = list.delete(i);
         if (x != true )
           System.out.println("ERRRRR");
    }
    System.out.println(list.size());
    System.out.println(list.peek());
    list.insert(9);
    for(int x : list){
      System.out.print(x);
   //   list.remove();
    }
    System.out.println(list.delete(9));
    System.out.println(list.delete(9));

    System.out.println(list.size());

//    System.out.print("---------------");
    list.insert(0);
    list.insert(1);
    list.insert(3);
//    list.insert(3);
//    list.insert(4);
//    list.insert(5);
//    System.out.println(list.delete(3));
//    System.out.println(list.delete(3));
  }
    public static void main(String [] args) {
        new Tester();
    }
}
