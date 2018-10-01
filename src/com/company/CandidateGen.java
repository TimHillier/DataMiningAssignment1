package com.company;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class CandidateGen {
    static int MIN = -1;


    public static void CandidateGen(int _min)
    {
        MIN = _min;
    }



    public static Block[] Generate(String[][] InputArray)
    {
        ArrayList<String> found = new ArrayList<String>();  //for searching.

        for(int i = 0; i < InputArray.length;i++)
        {
            for(int j = 0; j< InputArray[i].length;j++)
            {
                if(found.contains(InputArray[i][j]))
                {

                }
                else
                {
                    found.add(InputArray[i][j]);
                }

            }

        }
        MIN = found.size() / (100/MIN); // this is trash but I cant math rn. 


        HashMap candidates = HashMapTest(found);
        ElementCounter(InputArray,candidates);
        printmap(candidates);
        System.out.println("min " + MIN);
        Trim(candidates,MIN);
        printmap(candidates);


        Block[] Out = null;


        return Out;
    }

    //count the number of elements in the input and update the hash map.
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


    //Generate a hashmap for the current values.
    private static HashMap HashMapTest(ArrayList<String> found)
    {
        //will a hashmap work for this?
        HashMap<String,Integer> hmap = new HashMap<String,Integer>();
        for(String f: found)
        {
            hmap.put(f,0); //put all the things into the map and set them equal to zero.
        }
        printmap(hmap);
        return hmap;
    }

    //remove anything below the threshold.
    private static void Trim(HashMap HM, int MIN)
    {
        for(int i = 0; i < MIN;i++)
        {
            HM.values().remove(i);
        }


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

