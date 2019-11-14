import java.util.Iterator;

import data_structures.*;

public class MyDriver {

    public static void main(String args[])
    {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>();
        Hashtable<Integer, String> ht = new Hashtable<Integer, String>(50);
        BalancedTree<Integer, String> bt = new BalancedTree<Integer, String>();

        int[] array = new int[50];

        for(int i = 0; i < 50; i++)
        {
            array[i] = (int) (Math.random() * 6000000 + 1);

        }


        System.out.println("--First testing Binary Search Tree--");
        System.out.println("Testing add and delete");


        for(int i = 0; i < 50; i++)
        {
            if((!bst.add(array[i], Integer.toString(array[i]))))
            {
                System.out.println("Something wrong with adding in BST");
            }
        }

        if(bst.add(1000, "shouldntadd"))
        {
            System.out.println("BST Added when it shouldn't");
        }

        if(bst.add(array[0], "Doesnt matter"))
        {
            System.out.println("BST added a duplicate key");

        }

        for(int i = 0; i <= 49; i++)
        {
            bst.delete(array[49-i]);

            if(bst.contains(array[49-i]))
            {
                System.out.println("Either delete or contains is wrong for BST");
            }

        }


        System.out.println("Done");

        System.out.println("Now testing getValue and getKey in BST");

        for(int i = 0; i < 50; i++)
        {
            if((!bst.add(array[i], Integer.toString(array[i]))))
            {
                System.out.println("Something wrong with adding in BST");
            }
        }

        for(int i = 0; i < 50; i++)
        {
            if(bst.getKey(Integer.toString(array[i])) != array[i])
            {
                System.out.println("Something wrong with getKey in BST");

            }

            if(!(bst.getValue(array[i]).equals(Integer.toString(array[i]))) )
            {
                System.out.println("Something wrong with getValue in BST");

            }


        }

        System.out.println("Testing clear");

        bst.clear();

        for(int i = 0; i < 50; i++)
        {
            if((!bst.add(array[i], Integer.toString(array[i]))))
            {

                System.out.println("Something wrong with adding in BST");
            }
        }

        bst.clear();

        for(int i = 0; i < 50; i++)
        {
            if(bst.contains(array[i]))
            {
                System.out.println("Something wrong with clear in BST");
            }

        }

        System.out.println("Testing key and value iterators");


        for(int i = 0; i < 50; i++)
        {
            if((!bst.add(array[i], Integer.toString(array[i]))))
            {
                System.out.println("Something wrong with adding in BST");
            }
        }

        Iterator it = bst.keys();

        int[] sortArr = new int[50];

        for(int i = 0; i < 50; i++)
        {
            sortArr[i] = array[i];


        }

        bubbleSort(sortArr);

        for(int i = 0; i < 50; i++)
        {
            int a = (int) it.next();
            if(a != sortArr[i])
            {
                System.out.println("Something wrong with key iterator");
            }
        }

        it = bst.values();

        for(int i = 0; i < 50; i++)
        {
            String a = (String) it.next();
            if(a.compareTo(Integer.toString(sortArr[i])) != 0)
            {
                System.out.println("Something wrong with value iterator");
            }
        }

        System.out.println("Now going to try and cause Exception in both iterators to see if catches");

        try
        {
            Iterator newIt = bst.keys();

            bst.delete(array[3]);

            int a = (int)newIt.next();

            System.out.println("Something wrong with key iterator");

        }

        catch(Exception e)
        {
            System.out.println("Caught exception good job");
        }

        try
        {
            Iterator newIt = bst.values();

            bst.delete(array[7]);

            String a = (String)newIt.next();

            System.out.println("Something wrong with value iterator");

        }

        catch(Exception e)
        {
            System.out.println("Caught exception good job");
        }



        System.out.println("Finished with BST");


        System.out.println("Now doing hashtable");
        //---------------------------------------------------------------------------------------------


        System.out.println("Testing add and delete");


        for(int i = 0; i < 50; i++)
        {
            if((!ht.add(array[i], Integer.toString(array[i]))))
            {
                System.out.println("Something wrong with adding in ht");
            }
        }

        if(ht.add(1000, "shouldntadd"))
        {
            System.out.println("ht Added when it shouldn't");
        }

        if(ht.add(array[0], "Doesnt matter"))
        {
            System.out.println("ht added a duplicate key");

        }

        for(int i = 0; i <= 49; i++)
        {
            ht.delete(array[49-i]);

            if(ht.contains(array[49-i]))
            {
                System.out.println("Either delete or contains is wrong for ht");
            }

        }


        System.out.println("Done");

        System.out.println("Now testing getValue and getKey in ht");

        for(int i = 0; i < 50; i++)
        {
            if((!ht.add(array[i], Integer.toString(array[i]))))
            {
                System.out.println("Something wrong with adding in ht");
            }
        }

        for(int i = 0; i < 50; i++)
        {
            if(ht.getKey(Integer.toString(array[i])) != array[i])
            {
                System.out.println("Something wrong with getKey in ht");

            }

            if(!(ht.getValue(array[i]).equals(Integer.toString(array[i]))) )
            {
                System.out.println("Something wrong with getValue in ht");

            }


        }

        System.out.println("Testing clear");

        ht.clear();

        for(int i = 0; i < 50; i++)
        {
            if((!ht.add(array[i], Integer.toString(array[i]))))
            {

                System.out.println("Something wrong with adding in ht");
            }
        }

        ht.clear();

        for(int i = 0; i < 50; i++)
        {
            if(ht.contains(array[i]))
            {
                System.out.println("Something wrong with clear in ht");
            }

        }

        System.out.println("Testing key and value iterators");


        for(int i = 0; i < 50; i++)
        {
            if((!ht.add(array[i], Integer.toString(array[i]))))
            {
                System.out.println("Something wrong with adding in ht");
            }
        }

        Iterator ti = ht.keys();

        int[] srt = new int[50];

        for(int i = 0; i < 50; i++)
        {
            srt[i] = array[i];


        }

        bubbleSort(srt);

        for(int i = 0; i < 50; i++)
        {
            int a = (int) ti.next();
            if(a != srt[i])
            {
                System.out.println("Something wrong with key iterator");
            }
        }

        ti = ht.values();

        for(int i = 0; i < 50; i++)
        {
            String a = (String) ti.next();
            if(a.compareTo(Integer.toString(srt[i])) != 0)
            {
                System.out.println("Something wrong with value iterator");
            }
        }

        System.out.println("Now going to try and cause Exception in both iterators to see if catches");

        try
        {
            Iterator newIt = ht.keys();

            ht.delete(array[3]);

            int a = (int)newIt.next();

            System.out.println("Something wrong with key iterator");

        }

        catch(Exception e)
        {
            System.out.println("Caught exception good job");
        }

        try
        {
            Iterator newIt = ht.values();

            ht.delete(array[7]);

            String a = (String)newIt.next();

            System.out.println("Something wrong with value iterator");

        }

        catch(Exception e)
        {
            System.out.println("Caught exception good job");
        }



        System.out.println("Finished with ht");

        //----------------------------------------------------------------------------------
        System.out.println("Starting Balanced Tree");


        System.out.println("Testing add and delete");


        for(int i = 0; i < 50; i++)
        {
            if((!bt.add(array[i], Integer.toString(array[i]))))
            {
                System.out.println("Something wrong with adding in bt");
            }
        }

        if(bt.add(1000, "shouldntadd"))
        {
            System.out.println("bt Added when it shouldn't");
        }

        if(bt.add(array[0], "Doesnt matter"))
        {
            System.out.println("bt added a duplicate key");

        }

        for(int i = 0; i <= 49; i++)
        {
            bt.delete(array[49-i]);

            if(bt.contains(array[49-i]))
            {
                System.out.println("Either delete or contains is wrong for bt");
            }

        }


        System.out.println("Done");

        System.out.println("Now testing getValue and getKey in bt");

        for(int i = 0; i < 50; i++)
        {
            if((!bt.add(array[i], Integer.toString(array[i]))))
            {
                System.out.println("Something wrong with adding in bt");
            }
        }

        for(int i = 0; i < 50; i++)
        {
            if(bt.getKey(Integer.toString(array[i])) != array[i])
            {
                System.out.println("Something wrong with getKey in bt");

            }

            if(!(bt.getValue(array[i]).equals(Integer.toString(array[i]))) )
            {
                System.out.println("Something wrong with getValue in bt");

            }


        }

        System.out.println("Testing clear");

        bt.clear();

        for(int i = 0; i < 50; i++)
        {
            if((!bt.add(array[i], Integer.toString(array[i]))))
            {

                System.out.println("Something wrong with adding in bt");
            }
        }

        bt.clear();

        for(int i = 0; i < 50; i++)
        {
            if(bt.contains(array[i]))
            {
                System.out.println("Something wrong with clear in bt");
            }

        }

        System.out.println("Testing key and value iterators");


        for(int i = 0; i < 50; i++)
        {
            if((!bt.add(array[i], Integer.toString(array[i]))))
            {
                System.out.println("Something wrong with adding in bt");
            }
        }

        Iterator tip = bt.keys();

        int[] trs = new int[50];

        for(int i = 0; i < 50; i++)
        {
            trs[i] = array[i];


        }

        bubbleSort(trs);

        for(int i = 0; i < 50; i++)
        {
            int a = (int) tip.next();
            if(a != trs[i])
            {
                System.out.println("Something wrong with key iterator");
            }
        }

        tip = bt.values();

        for(int i = 0; i < 50; i++)
        {
            String a = (String) tip.next();
            if(a.compareTo(Integer.toString(trs[i])) != 0)
            {
                System.out.println("Something wrong with value iterator");
            }
        }

        System.out.println("Now going to try and cause Exception in both iterators to see if catches");

        try
        {
            Iterator newIt = bt.keys();

            bt.delete(array[3]);

            int a = (int)newIt.next();

            System.out.println("Something wrong with key iterator");

        }

        catch(Exception e)
        {
            System.out.println("Caught exception good job");
        }

        try
        {
            Iterator newIt = bt.values();

            bt.delete(array[7]);

            String a = (String)newIt.next();

            System.out.println("Something wrong with value iterator");

        }

        catch(Exception e)
        {
            System.out.println("Caugbt exception good job");
        }



        System.out.println("Finished with bt");




    }


    private static void bubbleSort(int[] arr)
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    // swap arr[j+1] and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }


}
