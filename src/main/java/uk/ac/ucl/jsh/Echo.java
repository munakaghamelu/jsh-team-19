package uk.ac.ucl.jsh;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * echo
 */
public class Echo implements Application
{
    @Override
    public void execute(List<String> args, InputStream input, OutputStream output) throws Exception {
        OutputStreamWriter writer = new OutputStreamWriter(output);
        ArrayList<String> files = new ArrayList<String>(); 
        boolean atLeastOnePrinted = false;
        
        for (int i = 0; i < args.size(); i++)
        {
            if(args.get(i).contains("*"))
            {
                files = echoGlob(args.get(i).trim());       
                for(String file : files)
                {
                    writer.write(file.trim());
                    // if(i != args.size()-1)
                    // {
                    //     writer.write(" ");
                    // }
                    writer.flush();
                    // atLeastOnePrinted = true;
                    
                    // if (atLeastOnePrinted) 
                    // {
                        writer.write(System.getProperty("line.separator"));
                        writer.flush();
                    // } 
                }
                continue;     
            }
            else 
            {
                writer.write(args.get(i).trim());
                writer.flush();   
            }
            if(i != args.size()-1)
            {
                writer.write(" ");
            }
            // atLeastOnePrinted = true; 
        }
        // if (atLeastOnePrinted) {
            writer.write(System.getProperty("line.separator"));
            writer.flush();
        // }  
    }

    public ArrayList<String> echoGlob(String arg) throws Exception
    {
        ArrayList<String> tempStorage = new ArrayList<String>(); 
        ArrayList<String> files = new ArrayList<String>();
        Globbing glob = new Globbing(); 
        try {
            tempStorage = glob.glob(arg);
        }
        catch (RuntimeException e) {
            files.add(arg);
        }
        // for(String file: tempStorage)
        for(int i = 0; i < tempStorage.size(); i++)
        {
            File check = new File(tempStorage.get(i)); 
            if(!check.isHidden())
            {
                files.add(tempStorage.get(i));
            }
        }
        return files; 
    }
}