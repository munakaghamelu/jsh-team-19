package uk.ac.ucl.jsh;

import java.io.InputStream;
import java.io.OutputStream;

interface Command {

    void evaluate(InputStream input, OutputStream output) throws Exception;
    //need to change this exception to be a command exception
}