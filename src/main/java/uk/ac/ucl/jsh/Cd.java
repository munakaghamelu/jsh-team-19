package uk.ac.ucl.jsh;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * cd
 */
public class Cd implements Application {
    @Override

    public void execute(List<String> args, InputStream input, OutputStream output) throws Exception 
    {
        String currentDirectory = Jsh.getCurrentDirectory();
        
        if (args.isEmpty()) {
            throw new RuntimeException("cd: missing argument");
        } else if (args.size() > 1) {
            throw new RuntimeException("cd: too many arguments");
        }
        String targetDirectory = args.get(0);
        File dir = new File(currentDirectory, targetDirectory);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new RuntimeException("cd: " + targetDirectory + " is not an existing directory");
        }

        Jsh.setCurrentDirectory(dir.getCanonicalPath());
        // Jsh.currentDirectory = dir.getCanonicalPath();
        // Should return 'input' ? -> must change the global variabale "currentDirectory" in Jsh.java
    }

}