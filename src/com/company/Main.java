package com.company;
import java.io.*;

public class Main {
    static int MinThresh = -1;
    static String location;

    public static void main(String[] args) {


        //get input from args
        GetArguments(args);
        //get File
        FileIO.GetInputFile(location);


        System.out.println(location + "\n" + MinThresh);
    }

    //gets and checks arguments from CLI
    private static void GetArguments(String[] args)
    {
        //lets check to see if the file exists.
        File f = new File(args[0]);
        if(f.exists() && !f.isDirectory())
        {
            location = args[0];
        }
        else
        {
            System.out.println("File at \"" + args[0] +"\" Does not exist");
            System.exit(0);
        }
        try
        {
            MinThresh = Integer.parseInt(args[1]); //the min threshold of the apiori program.
        }
        catch (NumberFormatException e)
        {
            System.out.println("Min Threshold should be a number");
            System.exit(0);
        }
        //need to check to make sure String location exists and that MinThresh is a number.

    }

}
