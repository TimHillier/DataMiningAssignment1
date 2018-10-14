package com.company;

import com.sun.deploy.util.ArrayUtil;

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
    }

    /**
     * Generates a set from the input
     * @param InputArray The Transactions.
     */
    private static void GenerateSet(String[][] InputArray)
    {

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
//            for(int i = 0; i < pairs.size(); i++)
//            {
//                for(int j = i+1; j < InputSet.size(); j++)
//                {
//                    if(!(pairs.get(i).contains(InputSet.get(j))))
//                    {
//                        pairs.get(i).add(InputSet.get(j));
//                        break;
//                    }
//                }
//            }
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
                    pairs.add(t);
                    System.out.println(Arrays.toString(pairs.toArray()));
                }
            }
            for(int i = 0; i<pairsize;i++)
            {
                pairs.remove(0);
            }

        }


    }

    private static void Trim(String[][] InputArray)
    {
        ArrayList<Integer> count = new ArrayList<>(Collections.nCopies(pairs.size(), 0)) ;
        System.out.println("Inputset Size: " + InputSet.size());

//        int[] count = new int[InputSet.size()];
        for(int i = 0; i < InputArray.length; i++)
        {
            for(int j = 0; j < pairs.size(); j++)
            {
                for(int k =0; k<pairs.get(j).size();k++)
                {

//                    if(Arrays.stream(InputArray[i]).forEach(pairs.get(j).get(k)::contains))
                    if(Arrays.asList(InputArray[i]).containsAll(pairs.get(j)))
                    {
//                        System.out.println("Match: " + Arrays.toString(InputArray[i]) +","+ pairs.get(j).get(k));
    //                    int n = count.get(j) + 1;
                        count.set(j,count.get(j) + 1);
                        break;
                    }
                }
            }


        }
        System.out.println("Count: " + Arrays.toString(count.toArray()));

        for(int i = count.size()-1; i > 0; i-- )
        {
            if(count.get(i) < MIN)
            {
                count.remove(i);
//                System.out.println("InputSet: " + Arrays.deepToString(InputSet.toArray()));
//                InputSet.remove(InputSet.indexOf(pairs.get(i).get(0)));
                pairs.remove(i);


                System.out.println("Size: " + pairs.size());
            }
        }
        System.out.println("After Removed: ");
        System.out.println("Pairs: " + Arrays.toString(pairs.toArray()));
        System.out.println("Count: " + Arrays.toString(count.toArray()));
    }

    public static void Generate(String[][] InputArray)
    {
        GenerateSet(InputArray);
        System.out.println("Set: " + Arrays.deepToString(InputSet.toArray()));
        MIN = InputSet.size() / (100/MIN);
        System.out.println("Min: " + MIN);
        GeneratePairs();
        System.out.println("Pairs: " + Arrays.deepToString(pairs.toArray()) + "\nFirst Trim");
        Trim(InputArray);
        GeneratePairs();
        System.out.println("Pairs: " + Arrays.deepToString(pairs.toArray()) + "\nSecond Trim");
        Trim(InputArray);
        GeneratePairs();
        System.out.println("Pairs: " + Arrays.deepToString(pairs.toArray()) + "\nThird Trim");
        Trim(InputArray);
        GeneratePairs();

        System.out.println("END");



    }
    {

    }
}


