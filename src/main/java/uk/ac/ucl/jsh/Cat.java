package uk.ac.ucl.jsh;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

/**
 * cat
 */
public class Cat implements Application
{
    @Override
    public void execute(List<String> args, InputStream input, OutputStream output) throws Exception 
    {
        Globbing glob = new Globbing();
        ArrayList<String> files = new ArrayList<String>();
        OutputStreamWriter writer = new OutputStreamWriter(output);

        for (String arg: args)
        {
            if(arg.contains("*"))
            {
                files.addAll(glob.glob(arg)); 
            }
            else files.add(arg); 
        }

        if (!args.isEmpty()) 
        {
            int i; 
            for (i = 0; i < files.size(); i++)
            {           
                Path buildPath = Paths.get(Jsh.getCurrentDirectory(), files.get(i)); 
                // File argFile = new File(Jsh.getCurrentDirectory() + File.separator + files.get(i));
                File argFile = buildPath.toFile();
                if (argFile.exists()) {
                    writer.flush();
                    FileInputStream targetFile = new FileInputStream(argFile);
                    targetFile.transferTo(output);
                    targetFile.close();
                    writer.flush();
                } 
                else{
                    throw new RuntimeException("cat: file does not exist");
                }
            }
        } 
        
        else 
        {
            if (input == null){
                throw new RuntimeException("cat: No file or stdin provided");
            }
            else
            {
                String inputContent = IOUtils.toString(input,Charset.forName(StandardCharsets.UTF_8.name())).trim();           
                
                // Split each file into separate element
                String[] pathList = inputContent.split("\n");

                // Why do we need to split by spaces? ALL FILE LISTS MUST BE NEWLINE LISTS 
                // Do not uncomment line below this, it will break programme
                // if(pathList.length == 1) pathList = inputContent.split(" ");

                // System.out.println("inputContent: " + inputContent);
                // for (String elem : pathList)
                // {
                //     System.out.println("element " + elem);
                // }
                
                for(int i = 0; i < pathList.length; i++)
                {
                    Path filePath = Paths.get(Jsh.getCurrentDirectory(),pathList[i]).toAbsolutePath().normalize();
                    
                    if(!filePath.toFile().exists() || pathList[i].equals(""))
                    {
                        writer.write(pathList[i]);
                        writer.flush();
                        writer.write(System.getProperty("line.separator"));
                        writer.flush();
                        // writer.close();
                        continue;
                    }
                    else
                    {       
                        File inputFile = filePath.toFile();
                        if (!inputFile.isDirectory()) {
                            writer.flush();
                            FileInputStream targetFile = new FileInputStream(filePath.toFile());
                            targetFile.transferTo(output);
                            targetFile.close();
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        } 

                        // I dont think we need this since we only enter this block of code if we are reading
                        // from stdIn. ____ | cat -> if the file did not exist, an exception would be thrown before being
                        // piped to cat
                        // else
                        // {
                        //     throw new RuntimeException("cat: file " + inputFile.toString() + " does not exist");
                        // }
                    }
                }
            }
        }
    }
}