package uk.ac.ucl.jsh;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import java.util.List;

/**
 * ls
 */
public class Ls implements Application
{
    @Override
    public void execute(List<String> args, InputStream input, OutputStream output) throws Exception {
        String currentDirectory = Jsh.getCurrentDirectory();
        OutputStreamWriter writer = new OutputStreamWriter(output);
        File currDir; 

        if (args.isEmpty()) {
            currDir = new File(currentDirectory);
        } else if (args.size() == 1) {  
            currDir = new File(args.get(0));
        } else {
            throw new RuntimeException("ls: too many arguments");
        }
        try {
            File[] listOfFiles = currDir.listFiles();
            Arrays.sort(listOfFiles);
            boolean atLeastOnePrinted = false;
            for (File file : listOfFiles) {
                if (!file.getName().startsWith(".")) {
                    writer.write(file.getName());
                    writer.write("\t");
                    writer.flush();
                    atLeastOnePrinted = true;
                }
            }
            if (atLeastOnePrinted) {
                writer.write(System.getProperty("line.separator"));
                System.out.println("hi");
                writer.flush();
            }
        } catch (NullPointerException e) {
            throw new RuntimeException("ls: no such directory");
        }
    }
}