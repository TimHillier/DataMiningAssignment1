package Code;
import java.nio.Buffer;
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
            int i = 0;
            BigBrotherArray = new String[CountLines(loc)][];
            while((line = br.readLine()) != null)
            {
                    //Split the string and sort them into their boxes;
                    String[] split = line.split("\\s+");
//                    System.out.println("Split: " + Arrays.deepToString(split));
//                    String[] TempArray = new String[Integer.parseInt(split[1])];
//                    for (int i = 0; (i < Integer.parseInt(split[1])); i++) {
//                        TempArray[i] = split[i + 2];
//                    }
//                    BigBrotherArray[Integer.parseInt(split[0]) - 1] = TempArray;
                BigBrotherArray[i] = split;
                i++;
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
    //count the number of lines....
    private static int CountLines(String Location)
    {
       try{
           File file = new File(Location);
           if(file.exists())
           {
               FileReader fr = new FileReader(file);
               LineNumberReader lnr = new LineNumberReader(fr);

               int linenumber = 0;
               while(lnr.readLine()!=null)
               {
                   linenumber++;
               }
               lnr.close();
               System.out.println("Lines: " + linenumber);
               return linenumber;
           }
           else
           {
               System.out.println("File no exists");
               return -1;
           }
       }
       catch(IOException e) {
           System.out.println("Error with file");
       }
       System.out.println("whoops");
       return -1;
    }

}
