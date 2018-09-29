package com.company;
import com.sun.tools.internal.ws.wsdl.document.Input;
import com.sun.tools.internal.ws.wsdl.document.Output;

import java.util.ArrayList;
import java.util.Arrays;


public class CandidateGen {

    public static ArrayList Generate(String[][] InputArray)
    {
        ArrayList<Block> OutputArray = new ArrayList<Block>(); //for the output
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
        System.out.println("Array: " + Arrays.toString(found.toArray()));










        return OutputArray;
    }

    private static boolean compare(String a, String b)
    {
        return a.equals(b);
    }




}
