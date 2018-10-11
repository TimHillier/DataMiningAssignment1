package com.company;

import java.util.*;
import java.util.stream.*;
public class CandidateGen {
    static int MIN = -1;
    static String[][] found;
    static boolean running = false;

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
        Trim(candidates,MIN,found);
        System.out.println("found" + Arrays.deepToString(found));//why?

        String[][] t = GenerateNext(found);

        candidates = HashMapper(t);
        ElementCounter(InputArray,candidates);
        System.out.println("found: " + Arrays.deepToString(found));
        Trim(candidates,MIN,found);
        Block[] Out = null;


        return Out;
    }

    /**
     * Counts the Number of Unique elements in the data. Adds the number to the hashmap.
     * @param IN The String of unique Elements found.
     * @param HM The Hashmap for this iteration.
     */
//    private static void ElementCounter(String[][] IN, HashMap HM)
//    {
//        for(int i = 0; i < IN.length; i++)
//        {
//            for(int j = 0; j<IN[i].length;j++)
//            {
//                if(HM.containsKey(IN[i][j]))
//                {
//                    HM.put(IN[i][j],(int)HM.get(IN[i][j])+1);
//                }
//            }
//        }
//
//    }

    private static void ElementCounter(String[][] IN,HashMap HM)
    {
        Object[] objarry = HM.keySet().toArray();
        String[][] temp = Arrays.stream(objarry).toArray(String[][]::new);
        for(int i = 0; i < IN.length;i++)
        {
            for(int j = 0; j < objarry.length;j++)
            {
               if(IsSubSet(IN[i], temp[j]))
                {
                    HM.put(objarry[j],(int)HM.get(objarry[j])+1);
                }
            }
        }
    }


    /**
     * Tests to see if the Generated Candidates are in the. This is dumb
     * @param IN The array that you want to look for the substring in
     * @param Test The sub string youre lookcing for.
     * @return true or false depending on if the substring exists.
     */
    private static boolean IsSubSet(String[] IN, String[] Test)
    {
        int a = 0;
        for(int i = 0; i < IN.length; i++)
        {
            for(int j = 0; j < Test.length; j++)
            {
                if(IN[i].equals(Test[j]))
                {
                    a++;
                }
            }
        }
        return a == Test.length;
    }
//    /**
//     *
//     * @param found A list of unique elements from the data.
//     * @return Returns a hashmap of the Unique elements.
//     */
//    private static HashMap HashMapper(String[] found)
//    {
//        //will a hashmap work for this?
//        HashMap<String,Integer> hmap = new HashMap<>();
//        for(String f: found)
//        {
//            hmap.put(f,0); //put all the things into the map and set them equal to zero.
//        }
////        printmap(hmap);
//        return hmap;
//    }

    /**
     * For later hashmaps.
     * @param found A list of unique lists of elements from the data.
     * @return Returns a hashmap of unique elements.
     */
    private static HashMap HashMapper(String[][] found)
    {
        HashMap<String[],Integer> hmap = new HashMap<>();
        for(String[] f: found)
        {
            hmap.put(f,0);
        }
        printmap(hmap);
        return hmap;

    }

    /**
     * remove anything below the threshold.
     * Also Trims the found Array.
     * @param HM The HashMap that holds this iteration
     * @param MIN The minimum threashold to search for.
     * @param FND The found array.
     */
    private static void Trim(HashMap HM, int MIN, String[][] FND)
    {
        for(int i = 0; i < MIN;i++)
        {

            HM.values().remove(i); //its slow at higher data thigns
        }
//        System.out.println("Here: " + HM.keySet()); //sends collections take these and make them the new found.
        String[][] s = new String[HM.size()][1];
        Set<String> keys = HM.keySet();
        String[][] arry = keys.toArray(new String[keys.size()][]);
        int i = 0;
        for(String[] key: arry)
        {
//            System.out.println("wtf: " + Arrays.toString(key));
            s[i][0] = Arrays.toString(key);
//            System.out.println(s[i][0]);
            i++;
        }
        System.out.print(Arrays.deepToString(s));
        found = s;

    }



    /**
     * Generates ck+1
     */
    private static String[][] GenerateNext(String[][] FND)
    {
        //if im given an array
        ArrayList<String[]> Permuations = new ArrayList<>();
        System.out.println("Generate Next From: " + Arrays.deepToString(FND));//
        for(int i= 0; i <FND.length-1; i++)
        {
            for(int j = i+1; j < FND.length;j++)
            {
                String[] t ={FND[i][0],FND[j][0]};
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
    private static String[][] GenerateSet(String[][] InputArray)
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
        String[][] f = new String[found.size()][];
        for(int i = 0; i < found.size();i++)
        {
            String[] temp = {found.get(i)};
            f[i] = temp;
        }
         return f;
    }

//for testing prints the hashmap
private static void printmap(HashMap hm)
{
    System.out.print("Hmap:");
    System.out.println(Arrays.deepToString(hm.keySet().toArray()));
//    for(Object objname:hm.keySet()) {
//        System.out.print( objname.toString()+ ":");
//        System.out.print(hm.get(objname) + "\n");
//    }
}





}

