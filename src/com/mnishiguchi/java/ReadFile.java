package com.mnishiguchi.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**  This class provides a few static methods to read data from a file  */
public class ReadFile
{
    
    // Static constants
    public static final String DELIMITER = "\t";
    
    /**
     * Creates a Scanner object to read a specified file.
     * @param filePath - path of the file to be opened
     * @return a Scanner object associated with the specified file
     */
    public static Scanner getScanner(String filePath)
    {
        Scanner in = null;
        try
        {
            File file = new File(filePath);

            if (file.exists() == false)    //if file doesn't exists, then create it
            {
                file.createNewFile();
            }
            in = new Scanner(file);
        }
        catch (IOException e)
        {
            System.out.println("I/O Error in ReadFile.getScanner, when trying to open a file and create a Scanner object.");
            System.exit(0);
        }
        return in;
    }
    
    /**
     * Reads data from a file
     * @param   filePath    the path to the file to be read
     * @return  an ArrayList object with each line of the file as an array element
     */
    public static ArrayList<String> getDataFromFile(String filePath)
    {
        // open file
        Scanner in = getScanner(filePath);
        
        String line = "";  // temporary storage
        ArrayList<String> lines = new ArrayList<String>();  // to store raw data
        
        while (in.hasNextLine() )
        {
            line = in.nextLine();  // read a line
            
            // if a line has data, add to temporary storage
            if ( line.equals("") == false)
            {
                lines.add(line); 
            }
        }
        in.close();  // close file
        return lines ;
    }

}
