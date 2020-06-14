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

public class MkDir implements Application {

    @Override
    public void execute(List<String> args, InputStream input, OutputStream output) throws Exception {

        if (args.isEmpty()) throw new RuntimeException("mkdir: no name specified");
        if (args.size() == 1) {
            File f = new File(Jsh.getCurrentDirectory() + "/" + args.get(0));
            if (!f.mkdir()) { throw new RuntimeException("mkdir: unable to create new Directory");}
        }
        else throw new RuntimeException("mkdir: unable to create new Directory");
    }
}