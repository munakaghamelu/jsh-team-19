package uk.ac.ucl.jsh;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;

/**
 * grep
 */
public class Grep implements Application {
    @Override
    public void execute(List<String> args, InputStream input, OutputStream output) throws Exception {

        OutputStreamWriter writer = new OutputStreamWriter(output);
        String pattern = "";
        

        pattern = args.get(0);
        Pattern grepPattern = Pattern.compile(pattern);

        // Non Pipe version
        if (args.size() != 1) 
        {
            int fileQty = args.size() - 1;      
            ArrayList<Path> filePathArray = new ArrayList<Path>(); 
            filePathArray = prepareFiles(args, fileQty);
            // System.out.println("filePathArray: " + filePathArray);

            for (int j = 0; j < filePathArray.size(); j++) {
                Charset encoding = StandardCharsets.UTF_8;

                // If an element in filePathArray contains a "*", skip bc the file has been globbed 
                // and globbed files are alrd in the filePathArray 
                if(filePathArray.get(j).toString().contains("*")) continue; 

                // MUST BE FULL PATH BY THIS POINT
                Path filePath = Paths.get(filePathArray.get(j).toString());
                // System.out.println("filepath " + filePath);
                BufferedReader reader = Files.newBufferedReader(filePath, encoding);
                
                String line = null;

                while ((line = reader.readLine()) != null) 
                {
                    Matcher matcher = grepPattern.matcher(line);
                    if (matcher.find()) {
                        writer.write(line);
                        writer.write(System.getProperty("line.separator"));
                        writer.flush();
                    }
                }
            }
        }
        // pipe version
        else {
            if (input == null) {
                throw new RuntimeException("no file or stdin provided");
            }
            String inputContent = IOUtils.toString(input,Charset.forName(StandardCharsets.UTF_8.name())).trim();
            String[] inputItems = inputContent.split("\n"); 
            Charset encoding = StandardCharsets.UTF_8;
            for(int i = 0; i < inputItems.length; i++)
            {
                Path path = Paths.get(Jsh.getCurrentDirectory(), inputItems[i]); 
                if(path.toFile().exists())
                {
                    BufferedReader reader = Files.newBufferedReader(path, encoding);
                    String line = null;
                    while ((line = reader.readLine()) != null) 
                    {
                        Matcher matcher = grepPattern.matcher(line);
                        if (matcher.find()) 
                        {
                            writer.write(line);
                            writer.write(System.getProperty("line.separator"));
                            writer.flush();
                        }
                    }
                }
                else
                {
                    Matcher matcher = grepPattern.matcher(inputItems[i]);
                    if (matcher.find()) 
                    {
                        writer.write(inputItems[i]);
                        writer.write(System.getProperty("line.separator"));
                        writer.flush();
                    }              
                }
            }
        }
    }

    public ArrayList<Path> prepareFiles(List<String> args, int fileQty) throws Exception
    {
        Path filePath;
        Path currentDirPath = Paths.get(Jsh.getCurrentDirectory());
        ArrayList<Path> globbingFiles = new ArrayList<Path>();
        ArrayList<Path> filePathArray = new ArrayList<Path>();

        for (int i = 0; i < fileQty; i++) 
        {
            // FULL PATH FOR NON-GLOBBING ONES
            filePath = currentDirPath.resolve(args.get(i + 1));
            // System.out.println("filePath prepareFiles: " + filePath);
            // If there is a file that has to be globbed, add only its name to filePathArray
            
            if (filePath.getFileName().toString().contains("*")) 
            {
                filePathArray.add(filePath.getFileName());
                continue;
            } 
            else if (Files.isDirectory(filePath) || !Files.exists(filePath)) 
            {
                throw new RuntimeException("grep: wrong file argument");
            }
            filePathArray.add(filePath); 
        }

        for (Path path : filePathArray) 
        {
            if (path.toString().contains("*")) {
                // will contain globbing files OR original argument [no match to a file -> ERROR]
                globbingFiles = globbingGrep(path.toString(), globbingFiles);
            }
        }
        // if there are files that have been globbed, add them to filePathArray
        if(!globbingFiles.isEmpty())
        {
            for(Path preparePath: globbingFiles)
            {
                preparePath = Paths.get(Jsh.getCurrentDirectory(), preparePath.toString()); 
                filePathArray.add(preparePath); 
            }
        }
        return filePathArray; 
    }

    public ArrayList<Path> globbingGrep(String arg, ArrayList<Path> globbingFiles) throws Exception
    {
        ArrayList<String> tempStorage = new ArrayList<String>(); 
        Globbing glob = new Globbing(); 
        tempStorage = glob.glob(arg);
        for(String file: tempStorage)
        {
            File relativePath = new File(file);
            // Do not include hidden files or directories 
            if(relativePath.isHidden() || relativePath.isDirectory()) continue; 
            // If the file is not hidden / is not a directory, add it to param arg, ArrayList<String> files (which may alrd contain other files from previous arg globbing)
            globbingFiles.add(relativePath.toPath()); 
        }
        // System.out.println("globbingGrep: the globbingFiles are " + globbingFiles);
        return globbingFiles; 
    }
}
