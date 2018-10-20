package Code;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.File;



public class CandidateGen {
    static int MIN = -1;
    static String[][] IN;
    static ArrayList<String> InputSet = new ArrayList<>();
    static ArrayList<ArrayList<String>> pairs = new ArrayList<>();
    static ArrayList<String> OUTPUT = new ArrayList<String>();
    static ArrayList<Integer> count = new ArrayList<Integer>();
    static String FileName = "";

    /**
     * Used for setting the min Thresh
     * @param _min The minimum Threashhold in percent.
     */
    public static void CandidateGen(int _min,String _name) {
        MIN = _min;
        FileName = "OutPut_" + Instant.now();
    }

    /**
     * Generates a set from the input
     * @param InputArray The Transactions.
     */
    private static void GenerateSet(String[][] InputArray)
    {
//        System.out.println("Generating Set");

        for(int i = 0; i < InputArray.length;i++)
        {
            for(int j = 0; j < InputArray[i].length;j++)
            {
                if(!(InputSet.contains(InputArray[i][j])))
                {
                    InputSet.add(InputArray[i][j]);
                }
            }
        }

    }

    /**
     * Generates the next set of pairs. Does the real heavy lifting.
     * Basically generates ck+1
     */
    private static void GeneratePairs()
    {
//        System.out.println("Generating Pairs: Start");
        long startTime = System.currentTimeMillis();

        if(pairs.size() < 1)
        {
            for(String p: InputSet)
            {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(p);
                pairs.add(temp);
            }
        }
        else
        {
            int pairsize = pairs.size();
            for(int i = 0; i<pairsize-1;i++)
            {
                for(int j = i+1; j<pairsize;j++)
                {

                    List<String> newList = Stream.of(pairs.get(i),pairs.get(j)).flatMap(List::stream).collect(Collectors.toList());
                    ArrayList<String> t = new ArrayList<>();
                    t.addAll(newList);
                    Set<String> s = new LinkedHashSet<>(t);
                    t = new ArrayList<>(s);
                    Collections.sort(t);
                    //I think generating thepairs is fast.
                    //It might still be to many paris?
                    //But it seems like Checking Uniqueness slows it down the most.
                    if(t.size() == pairs.get(0).size()+1)
                    {
                        pairs.add(t);
                    }

                }

            }
            //Time this
            //With uniqueness is slower than without.
            long st = System.currentTimeMillis();
            uniqueness(); //uniqueness is in gen pairs, so subtract that time. genpairs - unique
            long et = System.currentTimeMillis();
//            System.out.println("Duration of Uniqueness: " + (et - st) + " Miliseconds");
            for(int i = 0; i<pairsize;i++)
            {
                if(pairs.size() > 0)
                {
                    pairs.remove(0);
                }
            }
//            System.out.println("Generating Pairs: End");



            long endTime = System.currentTimeMillis();
//            System.out.println("Duration of GeneratePairs: " + (endTime - startTime - (et-st))+ " Miliseconds");


        }


    }

    //Slow

    /**
     * Trims the array of pairs so that we only get what we want.
     * @param InputArray The input array of transactions
     */
    private static void Trim(String[][] InputArray)
    {
//        System.out.println("Trim: Start");
//        System.out.println("Trimming: " + pairs.size());
        long st = System.currentTimeMillis();
        Count(InputArray);
        long et = System.currentTimeMillis();
//        System.out.println("Duration of Count: " + (et - st)+ " Miliseconds");


        long startTime = System.currentTimeMillis();

//        System.out.println("Count: " + Arrays.toString(count.toArray()));
        for(int i = count.size()-1; i >= 0; i-- )
        {
            if(count.get(i) < MIN)
            {
                count.remove(i);
                pairs.remove(i);


            }
        }
        //print to file.

//         System.out.println("After Removed: ");
//         System.out.println("Pairs: " + Arrays.toString(pairs.toArray()));
//         System.out.println("Count: " + Arrays.toString(count.toArray()));
//         System.out.println("Trim: End");
        long endTime=System.currentTimeMillis();
//        System.out.println("Duration of Trim: " + (endTime - startTime)+ " Miliseconds");


    }

    //slow

    /**
     * Counts the number of times a pair appears in the actual transaction table.
     * @param InputArray The transaction Array
     */
    private static void Count(String[][] InputArray)
    {
        ArrayList<Integer> C = new ArrayList<>(Collections.nCopies(pairs.size(), 0)) ;
//        System.out.println("InputArray Size: " + InputArray.length);
        for(int i = 0; i < InputArray.length; i++)
        {
            for(int j = 0; j < pairs.size(); j++)
            {
                if(InputArray[i].length >= pairs.get(j).size() && Arrays.asList(InputArray[i]).containsAll(pairs.get(j)))
                {
                    C.set(j,C.get(j) + 1);
                }
           }
        }
        count = C;
//        return count;
    }


    /**
     * The brain of the operations. calls everything needed to do apriori, and run until its done.
     * @param InputArray The input array of transactions.
     */
    public static void Generate(String[][] InputArray)
    {
        long ST = System.currentTimeMillis();

        System.out.println("START");

        GenerateSet(InputArray);
//        System.out.println("Set: " + Arrays.deepToString(InputSet.toArray()));
//        System.out.println("Set Size: " + InputSet.size());

//        MIN = InputSet.size() / (100/MIN);
        MIN = (int)Math.floor(InputArray.length * ((double)MIN/100));

//        System.out.println("Min: " + MIN + "\nInput Size: " + InputSet.size());


//        int i = 0;
//        do {
////            long startTime = System.currentTimeMillis();
//            GeneratePairs();
////            Trim(InputArray);
//            for(int a = 0; a < count.size(); a++)
//            {
//                OUTPUT.add(pairs.get(a)+":"+count.get(a));
////                writeToFile("TestOutPut",pairs.get(a) + ":" + count.get(a));
//            }
//
//
//
//
//            i++;
//            System.out.println("size: " + pairs.size());
//        }
//        while(!(pairs.get(0).size()<InputArray[0].length));
        GeneratePairs();
        Trim(InputArray);
        for(int i = 0; i < InputArray[0].length;i++)
        {
        GeneratePairs();
       }

        for(int i = 0; i < pairs.size();i++)
        {
            System.out.println(pairs.get(i));
        }
        for(int a = 0; a < pairs.size(); a++)
        {
            OUTPUT.add(pairs.get(a).toString());//+":"+count.get(a));
//                writeToFile("TestOutPut",pairs.get(a) + ":" + count.get(a));
        }
        long ET = System.currentTimeMillis();
        System.out.println("Duration of Program: " + (ET - ST)+ " Miliseconds");

        System.out.println("END! Now Printing To File");
        //check output for dupes.
        for(int a = 0; a < OUTPUT.size()-1;a++)
        {
            for(int b = a+1;b<OUTPUT.size();b++)
            {
                if(OUTPUT.get(a).equals(OUTPUT.get(b)))
                {
                    OUTPUT.remove(b);
                }
            }

        }
        writeToFile(FileName,"CK + 1");
        for(int a = 0;a<OUTPUT.size();a++)
        {
                writeToFile(FileName,OUTPUT.get(a));
        }
        System.out.println("File at: " + Paths.get(FileName));



    }
   //Slow

    /**
     * Checks the pair list for uniqueness.
     * removes situations where [a,b,c] [b,c,a] appear in the list.
      */
    private static void uniqueness()
    {
            int removed = 0;

        for(int i = 0; i < pairs.size()-1;i++)
        {

            for(int j =i+1; j< pairs.size();j++)
            {

                if(listsareEquivelent(pairs.get(i),pairs.get(j)))
                {
                    pairs.remove(pairs.get(j));
                    removed++;

                }
            }
        }

    }

    /**
     * returns true if two lists are the same.
     * @param a List to compare
     * @param b List to Compare
     * @return True or False depending on weather theyre equal.
     */
    private static boolean listsareEquivelent(List<? extends Object>a, List<? extends Object>b)
    {
        if(a==null)
        {
            if(b==null)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        if(b==null)
        {
            return false;
        }
        Map<Object, Integer> tempMap = new HashMap<>();
        for(Object element : a) {
            Integer currentCount = tempMap.get(element);
            if(currentCount == null) {
                tempMap.put(element, 1);
            } else {
                tempMap.put(element, currentCount+1);
            }
        }
        for(Object element : b) {
            Integer currentCount = tempMap.get(element);
            if(currentCount == null) {
                return false;
            } else {
                tempMap.put(element, currentCount-1);
            }
        }
        for(Integer count : tempMap.values()) {
            if(count != 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * Write to file
     * @param FileName Filename of the file to write to.
     * @param words Words to write to file.
     */
    private static void writeToFile(String FileName, String words)
    {
        try {
            FileIO.OutputToFile(FileName, words + "\n");
        }
        catch (Exception e)
        {

        }
    }

}


