package com.company;
import com.sun.tools.internal.ws.wsdl.document.Input;
import com.sun.tools.internal.ws.wsdl.document.Output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class CandidateGen {

    public static Block[] Generate(String[][] InputArray)
    {
        ArrayList<String> found = new ArrayList<String>();  //for searching.

        for(int i = 0; i < InputArray.length;i++)
        {
            for(int j = 0; j< InputArray[i].length;j++)
            {
                if(found.contains(InputArray[i][j]))
                {
//                    System.out.println("Found " + InputArray[i][j]);

                }
                else
                {
                    found.add(InputArray[i][j]);
                }

            }

        }
        Block[] Out = ElementCounter(found,InputArray);
//        Block[] Out = ElementCounter(found,new ArrayList<String>(Arrays.asList(InputArray)));

        System.out.println("Array: " + Arrays.toString(found.toArray()));




        return Out;
    }

    private static Block[] ElementCounter(ArrayList<String> found, String[][] Input)
    {
//        ArrayList<Block> OutputArray = new ArrayList<Block>(); //for the output
        Block[] OutputArray = new Block[found.size()];

        for(int i = 0; i < found.size();i++)
        {
            int amount = 0;
            for(int j = 0; j<Input.length;j++)
            {

                    amount += Collections.frequency(Arrays.asList(Input[j]),found.get(i));


            }
            Block temp = new Block(found.get(i),amount);
            OutputArray[i] = temp;

        }




        return OutputArray;
    }

    private static boolean compare(String a, String b)
    {
        return a.equals(b);
    }




}
