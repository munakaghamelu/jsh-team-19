package uk.ac.ucl.jsh;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.FileVisitResult;

public class Find implements Application {

    @Override
    public void execute(List<String> args, InputStream input, OutputStream output) throws Exception {
        //this will need to be implemented, it should have a recieving end to take the strings from the left of a pipe
        File dir; 
        Pattern pattern;
        OutputStreamWriter writer = new OutputStreamWriter(output);
        ArrayList<String> matchedFiles = new ArrayList<String>(); 

        // CALL PATTERN: find [PATH] -name PATTERN

        if (args.isEmpty()) throw new RuntimeException("find : missing argument");
        String dirString = args.get(0);

        
        // Explanation: if 1st argument is not [PATH] -> directory not specified therefore use current directory
        if(dirString.equalsIgnoreCase("-name")) 
        {
            dir = new File(Jsh.getCurrentDirectory());
            if(args.get(1).contains("*")) args.add(1, fixPattern(args.get(1)));
            pattern = Pattern.compile(args.get(1));
            Collections.sort(args);
        }

        // Explanation: if 1st argument is a [PATH]
        else
        {
            dir = new File(Jsh.getCurrentDirectory() + File.separator + dirString);
            if (!dir.exists() || !dir.isDirectory()) {
                throw new RuntimeException("find: " + dirString + " is not an existing directory");
            }

            if(args.get(2).contains("*")) args.add(2, fixPattern(args.get(2)));
            pattern = Pattern.compile(args.get(2));
            Collections.sort(args);
        }

        matchedFiles = search(pattern, dir.getPath());
        // if(matchedFiles.isEmpty()) System.out.println("find: no matches");
        Collections.sort(matchedFiles);
        for(String file : matchedFiles)
        {
            writer.write(file.trim());
            writer.write(System.getProperty("line.separator")); 
            writer.flush();
        } 
    }

    public String fixPattern(String pattern)
    {
        String target = "*"; 
        String replacement = ".*"; 
        return pattern.replace(target, replacement);
    }

    public ArrayList<String> search(Pattern pattern, String dir) throws IOException
    {
        ArrayList<String> matchedFiles = new ArrayList<String>(); 
        Files.walkFileTree(Paths.get(dir), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException {
                // System.out.println("Inside: " + dir.getParent());
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                String fileName = file.getFileName().toString();
                if(pattern.matcher(fileName).matches())
                { 
                    String absoluteFileName = file.toAbsolutePath().toString(); 
                    String currentDir = Jsh.getCurrentDirectory();
                    String replacedPath = currentDir.substring(0, currentDir.length());
                    String modifiedFileName = absoluteFileName.replace(replacedPath, ".");               
                    matchedFiles.add(modifiedFileName); 
                }
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc)
                    throws IOException {
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                    throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
        Collections.sort(matchedFiles);
        return matchedFiles; 
    }

}