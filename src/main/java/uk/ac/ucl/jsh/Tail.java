package uk.ac.ucl.jsh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

/**
 * tail
 */
public class Tail implements Application
{
    @Override
    public void execute(List<String> args, InputStream input, OutputStream output) throws Exception {
        
        OutputStreamWriter writer = new OutputStreamWriter(output);
        int tailLines = 10;
        String fileArg;
        Globbing glob = new Globbing();
        ArrayList<String> fileNames = new ArrayList<String>();
        ArrayList<String> storage = new ArrayList<>();
        String inputContent;

        if (args.size() == 2 && input != null || args.isEmpty() && input != null)
        {

            if(!args.isEmpty())
            {
                tailLines = Integer.parseInt(args.get(1));
            }
            else tailLines = 10; 

            inputContent = IOUtils.toString(input, Charset.forName(StandardCharsets.UTF_8.name())).trim();
            
            if(inputStreamCheck(inputContent))
            {
                Path path = Paths.get(Jsh.getCurrentDirectory(), inputContent); 
                storage = fileContent(path); 
            }
            else
            {
                String[] temp = inputContent.split("\n"); 
                for(String element : temp)
                {
                    if(inputStreamCheck(element))
                    {
                        throw new RuntimeException("Tail: should not pass more than one file");
                    }
                    storage.add(element); 
                }
            }
            
            int index = 0;
            if (tailLines > storage.size()) {
                index = 0;
            } else {
                index = storage.size() - tailLines;
            }

            for (int j = index; j < storage.size(); j++) 
            {
                writer.write(storage.get(j) + System.getProperty("line.separator"));
                writer.flush();
            }
        }

        
        else {
            if (args.isEmpty()) {
                throw new RuntimeException("tail: missing arguments");
            }
    
            else if (args.size() != 1 && args.size() != 3) {
                throw new RuntimeException("tail: wrong arguments");
            }
    
            else if (args.size() == 3 && !args.get(0).equals("-n")) {
                throw new RuntimeException("tail: wrong argument " + args.get(0));
            }

            else 
            {
                if (args.size() == 3) 
                {
                    tailLines = Integer.parseInt(args.get(1));
                    fileArg = args.get(2);
                } 
                else 
                {
                    fileArg = args.get(0);
                }
                
                if(fileArg.contains("*"))
                {
                    fileNames = glob.glob(fileArg); 
                }
    
                else fileNames.add(fileArg); 
    
                for(int i= 0; i< fileNames.size(); i++)
                {
                    Path filePath = Paths.get(Jsh.getCurrentDirectory(),fileNames.get(i));
                    if (filePath.toFile().exists()) {
                        if(filePath.toFile().isDirectory())
                        {
                            // writer.write("tail: " + filePath.toString() + "is a directory");
                            // writer.flush(); 
                            continue;
                        }
                        storage = fileContent(filePath); 
                    }
                    else {
                        throw new RuntimeException("Tail: file " + filePath.toString() + " doesn't exist");
                    }
    
                    int index = 0;
                    if (tailLines > storage.size()) {
                        index = 0;
                    } else {
                        index = storage.size() - tailLines;
                    }
    
                    for (int j = index; j < storage.size(); j++) {
                        writer.write(storage.get(j) + System.getProperty("line.separator"));
                        writer.flush();
                    }
                    if(i == fileNames.size()-1)
                    {
                        writer.write(System.getProperty("line.separator"));
                        writer.flush();
                    }
                } 
            }
        }
    }

    public boolean inputStreamCheck(String inputContent) {
        Path path = Paths.get(Jsh.getCurrentDirectory(), inputContent);
        return path.toFile().exists();
    }

    public ArrayList<String> fileContent(Path file) throws IOException
    {
        BufferedReader reader = null;
        ArrayList<String> fileContent = new ArrayList<String>(); 
        Charset encoding = StandardCharsets.UTF_8;
        reader = Files.newBufferedReader(file, encoding);
        String line = null;
        while((line = reader.readLine()) != null) {
            fileContent.add(line); 
        }
        reader.close();
        return fileContent; 
    }

}