import data_structures.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Driver {

  private LinearListADT<Integer> list;
  private Stack<Integer> stack;

  public Driver() {

    list = new LinearList<>();
    stack = new Stack<>();

    runTests();
  }

  private void runTests() {
	  System.out.println( stack.isEmpty());
	  stack.push(1);
	  System.out.println(stack.remove(1));
	System.out.println( stack.peek());
    for (int i = 1; i <= 10; i++) {
      list.addLast(i);

    }
   

    
            System.out.println("Should print 1 .. 10");
    
            for(int x : list)
    
                System.out.print(x + " ");
    
            System.out.println("\n");
    
    
    
            System.out.println("Now removing them all");
    
            for(int i=1; i <= 10; i++)
    
                list.remove(new Integer(i));
            //TODO test remove in middle and end . THis is onyl front
    
    
    
            System.out.println("Current size of list: (should be zero) "
    
                    + list.size());
    
    
    
            for(int i=1; i <= 10; i++)
    
                list.addFirst(new Integer(i));
    
            System.out.println("Current size of list: (should be 10)"
    
                    + list.size());
    
    
    
            System.out.println("Now using the iterator, should print "
    
                    + "10 .. 1");
    
            for(int x : list)
    
                System.out.print(x + " ");
    
            System.out.println();
    
    
    
            for(int i=1; i <= 10; i++)
    
                list.removeFirst();
    
            System.out.println("List should be empty, nothing should " +
    
                    "print below\n\n");
    
            for(int x : list)
    
                System.out.print(x + " ");
    
    
    
            list.addFirst(new Integer(25));
    
            list.addLast(new Integer(50));
    
            //list.insert(new Integer(75), 2);
    
            System.out.println("Should print 25 50");
    
            for(int x : list)
    
                System.out.print(x + " ");
    
            System.out.println();
            System.out.println(list.find(25));
    
            if(!list.contains(25))
    
                System.out.println("ERROR, element in list not found!");
    
            if(!list.contains(50))
    
                System.out.println("ERROR, element in list not found!");
//    
            list.remove(new Integer(50));
    
            list.remove(new Integer(25));
    
            if(list.remove(new Integer(100)) != null)
    
                System.out.println("ERROR, found element not in list!");
    
            System.out.println("List should be empty, nothing should print below\n\n");
    
            for(int x : list)
    
                System.out.print(x + " ");
    
    
    
            list.clear();
    
            System.out.println("Now inserting 100k elements in position #1\n" +
    
                    "Testing the resize and shift operations\n" +
    
                    "Times will vary, about 30 seconds on rohan, " +
    
                    "3 secs on my office machine.");
    
            long start = System.currentTimeMillis();
    
            for(int i=1; i <= 100000; i++)
    
                list.addFirst(new Integer(i));
    
            long stop = System.currentTimeMillis();
    
            System.out.println("Operation took " + ((stop-start)/1000) + " seconds");
    
            System.out.println("Now removing them all.");
    
            int counter = 100000;
    
            while(!list.isEmpty()) {
    
                list.removeLast();
    
                counter++;
    
                //System.out.println("This is the output of the couter: " + counter);
    
            }
    
            System.out.println("Done.");
    
  }

  public static void main(String[] args) {

    try {

      new Driver();

    } catch (Exception e) {

      System.out.println("ERROR " + e);

      e.printStackTrace();
    }
  }
}
