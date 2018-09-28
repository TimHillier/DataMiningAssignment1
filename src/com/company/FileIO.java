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



    public static void GetInputFile(String loc)
    {
        try(Stream<String> st = Files.lines(Paths.get(loc)))
        {
            //get the first element which is the number of transactions:
            First = st.findFirst().get();
            List<String> Disct;

            System.out.println("There are " + First + " transactions");
            System.out.println(Disct);

        }
        catch(IOException e)
        {

        }

    }


    public static void parseInput()
    {

    }

}
