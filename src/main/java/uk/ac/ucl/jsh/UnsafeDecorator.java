package uk.ac.ucl.jsh;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class UnsafeDecorator implements Application {

    protected Application decoratorApplication;
    
    public UnsafeDecorator(Application decoratorApplication) {
        this.decoratorApplication = decoratorApplication;
    }

    public void execute(List<String> args, InputStream input, OutputStream output) throws Exception {
        try {
            decoratorApplication.execute(args, input, output);
        }
        catch (Exception e) {
            System.out.println("Unsafe Application error: " + e);
        }
    }
}