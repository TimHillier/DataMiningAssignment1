package com.company;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Deals with the file IO of the program
public class FileIO {
        public static String First;
        static boolean firstTime = true;
        static String[][] BigBrotherArray;



    public static void GetInputFile(String loc) {
        //come back later and try to find a better way to store and get the file.
//        try(Stream<String> st = Files.lines(Paths.get(loc)))
//        {
//            //get the first element which is the number of transactions:
//            First = st.findFirst().get();
//            List<String> Disct;
//
//            System.out.println("There are " + First + " transactions");
//            System.out.println(Disct);
//
//
//        }
//        catch(IOException e)
//        {
//
//        }
        //this is the boring way to do this/
        try (BufferedReader br = new BufferedReader(new FileReader(loc)))
        {
            String line;
            while((line = br.readLine()) != null)
            {
                if(firstTime)
                {
                    First = line;
                    firstTime = false;
                    //Make Array to hold array;
                    //God am I actually making an array of arrays??????
                    BigBrotherArray = new String[Integer.parseInt(First)][];

                }
                else {
                    //Split the string and sort them into their boxes;
                    String[] split = line.split("\\s+");
                    String[] TempArray = new String[Integer.parseInt(split[1])];
                    for (int i = 0; (i < Integer.parseInt(split[1])); i++) {
                        TempArray[i] = split[i + 2];
                    }
                    BigBrotherArray[Integer.parseInt(split[0]) - 1] = TempArray;
                }
                }

           // System.out.println("First: " + First);
            System.out.println("Start: " + Arrays.deepToString(BigBrotherArray));
            Block[] n = CandidateGen.Generate(BigBrotherArray);

//            for(int i = 0; i < n.length; i++)
//            {
//                System.out.println("Element: " + n[i].getElement() + " : " + n[i].getAmount());
//            }


        }
        catch(IOException e)
        {
            System.out.println("No File at location: " + loc);
            System.exit(0);
        }

    }


    public static void parseInput()
    {

    }

}
