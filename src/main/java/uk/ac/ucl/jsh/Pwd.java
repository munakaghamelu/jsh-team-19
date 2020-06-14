package uk.ac.ucl.jsh;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * pwd
 */
public class Pwd implements Application {

    @Override
    
    public void execute(List<String> args, InputStream input, OutputStream output) throws Exception {
        String currentDirectory = Jsh.getCurrentDirectory();
        OutputStreamWriter writer = new OutputStreamWriter(output);
        writer.write(currentDirectory);
        writer.write(System.getProperty("line.separator"));
        writer.flush(); 
    }
    
}
