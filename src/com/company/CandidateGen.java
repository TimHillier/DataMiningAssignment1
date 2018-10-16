package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CandidateGen {
    static int MIN = -1;
    static String[][] IN;
    static ArrayList<String> InputSet = new ArrayList<>();
    static ArrayList<ArrayList<String>> pairs = new ArrayList<>();
    static ArrayList<String> Removed = new ArrayList();

    /**
     * Used for setting the min Thresh
     * @param _min The minimum Threashhold in percent.
     */
    public static void CandidateGen(int _min) {
        MIN = _min;
        System.out.println("With Uniqueness" );
    }

    /**
     * Generates a set from the input
     * @param InputArray The Transactions.
     */
    private static void GenerateSet(String[][] InputArray)
    {
        System.out.println("Generating Set");

        for(int i = 0; i < InputArray.length;i++)
        {
            for(int j = 0; j < InputArray[i].length;j++)
            {
                if(!(InputSet.contains(InputArray[i][j])))
                {
                    InputSet.add(InputArray[i][j]);
                }
            }
        }

    }

    private static void GeneratePairs()
    {
        System.out.println("Generating Pairs");

        if(pairs.size() < 1)
        {
            for(String p: InputSet)
            {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(p);
                pairs.add(temp);
            }
        }
        else
        {
            int pairsize = pairs.size();
            for(int i = 0; i<pairsize-1;i++)
            {
                for(int j = i+1; j<pairsize;j++)
                {

                    List<String> newList = Stream.of(pairs.get(i),pairs.get(j)).flatMap(List::stream).collect(Collectors.toList());
                    ArrayList<String> t = new ArrayList<>();
                    t.addAll(newList);
                    Set<String> s = new LinkedHashSet<>(t);
                    t = new ArrayList<>(s);
                    Collections.sort(t);
                    //I think generating thepairs is fast.
                    //It might still be to many paris?
                    //But it seems like Checking Uniqueness slows it down the most.
//                    System.out.println("Size of check: " + 2 * pairs.get(0).size());
                    if(t.size() == pairs.get(0).size()+1)
                    {
                        pairs.add(t);
                    }

                }

            }
            //Time this
            //With uniqueness is slower than without.
            long startTime = System.currentTimeMillis();
            uniqueness();
            long endTime = System.currentTimeMillis();
            System.out.println("Duration of Uniqueness: " + (endTime - startTime) + " Miliseconds");
            for(int i = 0; i<pairsize;i++)
            {
                if(pairs.size() > 0)
                {
                    pairs.remove(0);
                }
            }
            System.out.println("Pairs Size: " + pairs.size());





        }


    }

    private static void Trim(String[][] InputArray)
    {
        System.out.println("Trimming: " + pairs.size());

        ArrayList<Integer> count = new ArrayList<>(Collections.nCopies(pairs.size(), 0)) ;
        System.out.println("Inputset Size: " + InputSet.size());

//        int[] count = new int[InputSet.size()];
        //i*j*k
        for(int i = 0; i < InputArray.length; i++)
        {
            for(int j = 0; j < pairs.size(); j++)
            {
                for(int k =0; k<pairs.get(j).size();k++)
                {

//                    if(Arrays.stream(InputArray[i]).forEach(pairs.get(j).get(k)::contains))
                    if(Arrays.asList(InputArray[i]).containsAll(pairs.get(j)))
                    {
                        count.set(j,count.get(j) + 1);
                        break;
                    }
                }
            }


        }
        System.out.println("Count: " + Arrays.toString(count.toArray()));
        for(int i = count.size()-1; i >= 0; i-- )
        {
            if(count.get(i) < MIN)
            {
                count.remove(i);
//                InputSet.remove(InputSet.indexOf(pairs.get(i).get(0)));
                pairs.remove(i);


//                System.out.println("Size: " + pairs.size());
            }
        }
         System.out.println("After Removed: ");
         System.out.println("Pairs: " + Arrays.toString(pairs.toArray()));
         System.out.println("Count: " + Arrays.toString(count.toArray()));
    }

    public static void Generate(String[][] InputArray)
    {
        long ST = System.currentTimeMillis();

        System.out.println("Generating");

        GenerateSet(InputArray);
        System.out.println("Set: " + Arrays.deepToString(InputSet.toArray()));
        System.out.println("Set Size: " + InputSet.size());

//        MIN = InputSet.size() / (100/MIN);
        MIN = (int)Math.floor(InputArray.length * ((double)MIN/100));

        System.out.println("Min: " + MIN + "\nInput Size: " + InputSet.size());


        int i = 0;
        do {
            long startTime = System.currentTimeMillis();
            GeneratePairs();
            long endTime = System.currentTimeMillis();
            System.out.println("Duration of GeneratePairs: " + (endTime - startTime)+ " Miliseconds");
            System.out.println("Pairs: " + Arrays.deepToString(pairs.toArray()) );
            System.out.println("Trim Number " + i);
            Trim(InputArray);
            i++;
        }
        while(!(pairs.isEmpty()));
        long ET = System.currentTimeMillis();
        System.out.println("Duration of Program: " + (ET - ST)+ " Miliseconds");

//        System.out.println("")
        System.out.println("END");



    }
    //this is bad fix this.
    //This makes it all slow af.
    private static void uniqueness()
    {
        System.out.println("Uniqueness: " + pairs.size());
            int removed = 0;

        for(int i = 0; i < pairs.size()-1;i++)
        {

            for(int j =i+1; j< pairs.size();j++)
            {

                if(listsareEquivelent(pairs.get(i),pairs.get(j)))
                {
                    pairs.remove(pairs.get(j));
                    removed++;

                }
            }
        }
        System.out.println("Uniqueness: Done. Removed: " + removed);
    }

    /**
     * returns true if two lists are the same.
     * @param a List to compare
     * @param b List to Compare
     * @return True or False depending on weather theyre equal.
     */
    private static boolean listsareEquivelent(List<? extends Object>a, List<? extends Object>b)
    {
        if(a==null)
        {
            if(b==null)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        if(b==null)
        {
            return false;
        }
        Map<Object, Integer> tempMap = new HashMap<>();
        for(Object element : a) {
            Integer currentCount = tempMap.get(element);
            if(currentCount == null) {
                tempMap.put(element, 1);
            } else {
                tempMap.put(element, currentCount+1);
            }
        }
        for(Object element : b) {
            Integer currentCount = tempMap.get(element);
            if(currentCount == null) {
                return false;
            } else {
                tempMap.put(element, currentCount-1);
            }
        }
        for(Integer count : tempMap.values()) {
            if(count != 0) {
                return false;
            }
        }
        return true;
    }

}


