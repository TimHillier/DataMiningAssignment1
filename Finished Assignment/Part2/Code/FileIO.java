package com.company;
import java.nio.Buffer;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.nio.file.Path;
import java.nio.file.*;
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
            if(BigBrotherArray.length <100) {
                System.out.println("Start: " + Arrays.deepToString(BigBrotherArray));
            }
            else
            {
                System.out.println("Start: Size To large to Display");
            }

            CandidateGen.Generate(BigBrotherArray);

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


    /**
     * Outputs the data to a file.
     * @param Out String to output to file.
     * @param fileName Name of the file to output to.
     */
    public static void OutputToFile(String fileName, String Out) throws IOException
    {


        Path path = Paths.get(fileName);

        if(!(Files.exists(path)))
        {
            Files.createFile(path);
//            System.out.println("File Created");
        }
        if(Files.exists(path))
        {
//            System.out.println("File already exists");
        }

        byte[] words = Out.getBytes();
        Files.write(path, words,StandardOpenOption.APPEND);



    }

}
