package com.mnishiguchi.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**  This class provides a few static methods to read data from a file  */
public class ReadFile
{
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
            
            // If file doesn't exists, then create it.
            if (!file.exists() ) file.createNewFile();
            // Create a scanner
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
     * Reads data from a file.
     * @param filePath
     * @return  An ArrayList object with each line of the file as an array element.
     */
    public static ArrayList<String> getDataFromFile(String filePath)
    {
        Scanner in = getScanner(filePath);  // Open file
        
        String line = "";  // Temporary storage for each line
        ArrayList<String> data = new ArrayList<String>();
        while (in.hasNextLine() )
        {
            line = in.nextLine();  // Read a line
            // If any, add to temporary storage.
            if (!line.equals("") ) data.add(line); 
            //System.out.println(line);
        }
        in.close();  // Close file
        return data;
    }

}
