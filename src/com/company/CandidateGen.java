package com.company;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

public class CandidateGen {
    static int MIN = -1;
    static String[] found;
    static boolean running = true;

    //used for setting the minthresh.
    public static void CandidateGen(int _min)
    {
        MIN = _min;
    }


    /**
     * This needs to change.
     * @param InputArray The input table with all the transactions and items.
     * @return probably nothign.
     */
    public static Block[] Generate(String[][] InputArray)
    {

        found = GenerateSet(InputArray);

        MIN = found.length / (100/MIN); // this is trash but I cant math rn.


        HashMap candidates = HashMapper(found);
        ElementCounter(InputArray,candidates);
//        printmap(candidates);
//        System.out.println("min " + MIN);
        Trim(candidates,MIN,found);
        System.out.println("found" + Arrays.toString(found));//why?
//        printmap(candidates);

        String[][] t = GenerateNext(found);

        for(int i = 0; i < 3; i++)
        {


        }



        while(running)
        {

        }


        Block[] Out = null;


        return Out;
    }

    /**
     * Counts the Number of Unique elements in the data. Adds the number to the hashmap.
     * @param IN The String of unique Elements found.
     * @param HM The Hashmap for this iteration.
     */
    private static void ElementCounter(String[][] IN, HashMap HM)
    {
        for(int i = 0; i < IN.length; i++)
        {
            for(int j = 0; j<IN[i].length;j++)
            {
                if(HM.containsKey(IN[i][j]))
                {
                    HM.put(IN[i][j],(int)HM.get(IN[i][j])+1);
                }
            }
        }

    }

    /**
     *
     * @param found A list of unique elements from the data.
     * @return Returns a hashmap of the Unique elements.
     */
    private static HashMap HashMapper(String[] found)
    {
        //will a hashmap work for this?
        HashMap<String,Integer> hmap = new HashMap<String,Integer>();
        for(String f: found)
        {
            hmap.put(f,0); //put all the things into the map and set them equal to zero.
        }
//        printmap(hmap);
        return hmap;
    }

    /**
     * remove anything below the threshold.
     * Also Trims the found Array.
     * @param HM The HashMap that holds this iteration
     * @param MIN The minimum threashold to search for.
     * @param FND The found array.
     */
    private static void Trim(HashMap HM, int MIN, String[] FND)
    {
        for(int i = 0; i < MIN;i++)
        {

            HM.values().remove(i); //its slow at higher data thigns
        }
//        System.out.println("Here: " + HM.keySet()); //sends collections take these and make them the new found.
        String[] s = new String[HM.size()];
        Set<String> keys = HM.keySet();
        int i = 0;
        for(String key: keys)
        {
            s[i] = key;
            i++;
        }

        found = s;

    }



    /**
     * Generates ck+1
     */
    private static String[][] GenerateNext(String[] FND)
    {
        //if im given an array
        ArrayList<String[]> Permuations = new ArrayList<>();
        System.out.println("Generate Next From: " + Arrays.toString(FND));//
        for(int i= 0; i <FND.length-1; i++)
        {
            for(int j = i+1; j < FND.length;j++)
            {
                String[] t ={FND[i],FND[j]};
                Permuations.add(t);
            }
        }

        System.out.println("Permutations: " + Arrays.deepToString( Permuations.toArray()));

        return ListToArray(Permuations);

    }

    /**
     *
     * @param t The input array of permutations
     * @return A string array of permutations.
     */
    private static String[][] ListToArray(ArrayList<String[]> t)
    {
        String[][] TempArray = new String[t.size()][];
        for(int i = 0; i < t.size(); i++)
        {
            Object[] Temp = t.get(i);//.toArray();
            String[] a = Arrays.copyOf(Temp, Temp.length, String[].class);
            TempArray[i] = a;
        }
//        System.out.println("Temp Array: " + Arrays.deepToString(TempArray));

        return TempArray;



    }




    /**
     * Generates a set of elements given a list of transactions.
     * @param InputArray The array of all transactions.
     * @return The array that is the set.
     */
    private static String[] GenerateSet(String[][] InputArray)
    {
        ArrayList<String> found = new ArrayList<String>();  //for searching.

        for(int i = 0; i < InputArray.length;i++)
        {
            for(int j = 0; j< InputArray[i].length;j++)
            {
                if(!(found.contains(InputArray[i][j])))
                {
                    found.add(InputArray[i][j]);

                }
            }

        }
         String[] f = found.toArray(new String[found.size()]);
         return f;
    }

//for testing prints the hashmap
private static void printmap(HashMap hm)
{
    System.out.println("Hmap:");
    for(Object objname:hm.keySet()) {
        System.out.print(objname + ":");
        System.out.print(hm.get(objname) + "\n");
    }
}





}

