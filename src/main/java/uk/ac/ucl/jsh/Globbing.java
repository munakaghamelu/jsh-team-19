package uk.ac.ucl.jsh;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;


public class Globbing 
{
    public ArrayList<String> glob(String path) throws Exception
    {
        // DEBUGGING STATEMENT
        // System.out.println("Globbing: Param path is " + path); 

        ArrayList<String> files = new ArrayList<String>();
        Path targetDirectory;
        String regex = "";  

        // Extracting the parameter (path) without the regex (substring containing *)
        String[] pathComponents = path.split(System.getProperty("file.separator"));
        
        // Paths.get function creates proper paths eg. Paths.get("dir1","dir2 ") => dir1/dir2
        // Right now I'm creating an empty path
        if(path.startsWith("/"))
        {
            targetDirectory = Paths.get("/"); 
        }
        else targetDirectory = Paths.get("");   

        // Checking each path component for the regex symbol *
        for(String comp : pathComponents)
        {
            if(comp.contains("*"))
            {
                // If the first component (comp) is the regex alrd && targetDirectory is empty string, that means the directory to search is current directory
                // Eg. Parameter argument, "path" will look like this: tes*.txt
                // if(targetDirectory.toString().equals("")) targetDirectory = Paths.get(Jsh.getCurrentDirectory()); 
                regex = comp;
                break; 
            }
            // Else, add the component to the target directory
            // Goal is to replicate the parameter argument, "path" without the regex component within it
            targetDirectory = Paths.get(targetDirectory.toString(), comp);
        }
        // DEBUGGING STATEMENTS
        // System.out.println("Globbing: regex is: " + regex); 
        // System.out.println("Globbing: target dir: " + targetDirectory.toString()); 

        // Create a new file/directory using the target directory and check if it exists
        Path pathTest; 
        if(targetDirectory.toString().equalsIgnoreCase(Jsh.getCurrentDirectory()))
        {
            pathTest = targetDirectory; 
        }
        else 
        {
            pathTest = Paths.get(Jsh.getCurrentDirectory(),targetDirectory.toString());  
        }
        // System.out.println("Globbing: pathTest is " + pathTest);
        // if(!pathTest.toFile().exists())
        // {
        //     // System.out.println("Globbing: File " + checkFile.getPath() + " doesnt exists");  
        //     // If directory/file does not exist, then the argument was not a globbing path, could be an argument simply meant to be printed out
        //     files.add(path);
        //     Collections.sort(files);
        //     return files; 
        // }
        // Directory stream filters all files & directories in the targetDirectory for files & folders fitting the regex pattern
        // Returns an arrayList<Path>

        // DirectoryStream<Path> stream = Files.newDirectoryStream(targetDirectory, regex);
        DirectoryStream<Path> stream = Files.newDirectoryStream(pathTest, regex);

        // Add the string version of the path to ArrayList<String> files to be returned
        for(Path entry: stream)
        {
            // Getting relative path of file
            String relPath = entry.toString().replaceFirst(Jsh.getCurrentDirectory()+"/", "");
            files.add(relPath); 

            // DEBUGGING STATEMENT
            // System.out.println("Globbing: rel entry is " + relPath); 
        }
        stream.close();
        // If Directory exists, but no matching files/directories then throw an exception
        if(files.isEmpty())
        {
            throw new RuntimeException("Globbing: no files or directories found for " + path); 
        }
        Collections.sort(files);
        return files; 
    }
}