package uk.ac.ucl.jsh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class Sed implements Application {

    @Override
    public void execute(List<String> args, InputStream input, OutputStream output) throws Exception {
        String oldtext = "";
        OutputStreamWriter writer = new OutputStreamWriter(output);
        BufferedReader reader = null;
        String[] replacementSplit = null;
        String currentDirectory = Jsh.getCurrentDirectory();

        if (args.isEmpty()) throw new RuntimeException("sed: missing argument");
        String replacement = args.get(0); 
        if(replacement.charAt(0) != 's') throw new RuntimeException("sed: faulty argument");

        // Find the delimiter of the REPLACEMENT -> ISSUE if delim is '|' or ';' -> antlr think its pipe/seq
        char delim = replacement.charAt(1);
        replacementSplit = replacement.split("" + delim);

        int replacementSplitLength = replacementSplit.length; 

        // Checks that the REPLACEMENT length is either 3 or 4
        if(replacementSplitLength != 3 && replacementSplitLength != 4) throw new RuntimeException("sed: faulty " + replacement + " argument. Not enough components");
        if(replacementSplitLength == 4 && !replacementSplit[3].equalsIgnoreCase("g")) throw new RuntimeException("sed: faulty " + replacement + " argument");
        
        String regex = replacementSplit[1];
        String substitute = replacementSplit[2];     
        
        // NO STDIN
        if (args.size() == 2){
            File file = new File(currentDirectory, args.get(1)); 
            if (!file.exists() && !file.isDirectory()) 
            {
                throw new RuntimeException("sed: " + file.getName() + " is not an existing file");
            }
            reader = new BufferedReader(new FileReader(file));
            oldtext = sedFile(replacementSplitLength, reader, regex, substitute); 
        }

        // USES STDIN
        else
        {
            // if (input == null)
            // {
            //     throw new RuntimeException("sed: No file or stdin provided");
            // }
            // else
            // {
                String inputContent = IOUtils.toString(input,Charset.forName(StandardCharsets.UTF_8.name())).trim();
                String[] inputList = inputContent.split("\n");

                for(int i = 0; i < inputList.length; i++)
                {
                    // Assumption: inputList only holds relative paths
                    Path paths = Paths.get(Jsh.getCurrentDirectory(), inputList[i]);
                    if(paths.toFile().exists())
                    {
                        reader = new BufferedReader(new FileReader(paths.toFile()));
                        oldtext = sedFile(replacementSplitLength, reader, regex, substitute); 
                    } 
                    // It holds file contents
                    else
                    {
                        oldtext = sedString(replacementSplitLength, inputList, regex, substitute);
                        break;
                    }
                }
            // }
        }
        //write to stdout not FILE!!
        writer.write(oldtext);
        writer.flush();
    }

    public String sedString(int replacementLength, String[] inputList, String regex, String substitute)
            throws Exception
    {
        String oldtext = ""; 
        if(replacementLength == 3)
        {
            for(int j = 0; j < inputList.length; j++)
            {
                oldtext += inputList[j].replaceFirst(regex, substitute); 
                oldtext += System.getProperty("line.separator"); 
            }
            return oldtext;
        }
        // Replace all substrings
        else
        {
            for(int j = 0; j < inputList.length; j++)
            {
                oldtext += inputList[j].replaceAll(regex, substitute); 
                oldtext += System.getProperty("line.separator"); 
            }
            return oldtext;
        }
    }

    public String sedFile(int replacementLength, BufferedReader reader, String regex, String substitute) throws Exception
    {
        String line, oldtext = ""; 
        if(replacementLength == 3)
        {
            while((line = reader.readLine()) != null)
            {
                line = line.replaceFirst(regex, substitute); 
                oldtext += line + "\r\n";
            }
            reader.close();
            return oldtext; 
        }

        else
        {
            while((line = reader.readLine()) != null)
            {
                line = line.replaceAll(regex, substitute); 
                oldtext += line + "\r\n";
            }
            reader.close();
            return oldtext; 
        }
        
    }
}
