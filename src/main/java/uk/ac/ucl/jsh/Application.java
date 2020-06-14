package uk.ac.ucl.jsh;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface Application
{
    void execute(List<String> args, InputStream input, OutputStream output) throws Exception ; 
    //need to define our own Application exception
}
