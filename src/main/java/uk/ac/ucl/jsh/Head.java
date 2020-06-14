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
 * head
 */
public class Head implements Application {
    @Override
    public void execute(List<String> args, InputStream input, OutputStream output) throws Exception 
    {
        int headLines = 10;
        String headFile = null;
        String fileContent = null;
        String inputContent;
        OutputStreamWriter writer = new OutputStreamWriter(output);
        int length;

        Globbing glob = new Globbing();
        ArrayList<String> fileNames = new ArrayList<String>();

        if(input == null)
        {
            if (args.size() == 3) 
            {
                if(!args.get(0).equals("-n"))
                {
                    throw new RuntimeException("head: wrong argument " + args.get(0));   
                }
                headLines = Integer.parseInt(args.get(1));
                headFile = args.get(2);
            }
            else if (args.size() == 1)
            {
                headFile = args.get(0);
            }
            else
            {
                throw new RuntimeException("head: wrong arguments");
            }
        }

        // Input NOT null
        else
        {
                if (args.size() == 2) 
                {
                    headLines = Integer.parseInt(args.get(1));
                    inputContent = IOUtils.toString(input, Charset.forName(StandardCharsets.UTF_8.name())).trim();
                    if (inputStreamCheck(inputContent)) 
                    {
                        headFile = inputContent;
                    } 
                    else fileContent = inputContent;
                } 
                
                else
                {
                    inputContent = IOUtils.toString(input, Charset.forName(StandardCharsets.UTF_8.name())).trim();
                    if (inputStreamCheck(inputContent)) 
                    {
                        headFile = inputContent;
                    } 
                    else fileContent = inputContent;
                }  

        }

        //  At this point, headFile = null / headFile = [fileName]  , fileContent = something / fileContent = null
        if(headFile != null)
        {
            if (headFile.contains("*")) 
            {
                fileNames = glob.glob(headFile);
            } 
            else fileNames.add(headFile);
            length = fileNames.size();
        }

        // Headfile is null therefore we are reading from fileContent
        else length = 1; 


        for (int j = 0; j < length; j++) 
        {
            if(fileContent == null)
            {
                Path headFilePath = Paths.get(Jsh.getCurrentDirectory(), fileNames.get(j));

                if (!headFilePath.toFile().exists()) 
                {
                    throw new RuntimeException("Head: file " + headFilePath.toString() + " doesn't exist");
                }
                else if (headFilePath.toFile().isDirectory()) 
                {
                    continue;
                }
                fileContent = fileContent(headFilePath);
            }

            String[] lines = fileContent.split("\n");
            if(headLines > lines.length) headLines = lines.length;
            for (int i = 0; i < headLines; i++) 
            {
                writer.write(lines[i]);
                writer.write(System.getProperty("line.separator"));
                writer.flush();
            }
            if (j != fileNames.size() - 1) 
            {
                writer.write(System.getProperty("line.separator"));
                writer.flush();
            }
            fileContent = null; 
        }
    }

    public boolean inputStreamCheck(String inputContent) {
        Path path = Paths.get(Jsh.getCurrentDirectory(), inputContent);
        return path.toFile().exists();
    }

    public String fileContent(Path file) throws IOException
    {
        BufferedReader reader = null;
        String fileContent = ""; 
        Charset encoding = StandardCharsets.UTF_8;
        reader = Files.newBufferedReader(file, encoding);
        String line = null;
        while((line = reader.readLine()) != null) {
            fileContent = fileContent + line + "\n"; 
        }
        return fileContent; 
    }
}