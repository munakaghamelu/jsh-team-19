package uk.ac.ucl.jsh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

class Pipe implements Command {

    private Command left;
    private Command right;

    public Pipe(Command left, Command right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void evaluate(InputStream input, OutputStream output) throws Exception{

        try {

            PipedInputStream rightInput = new PipedInputStream(100000); //set buffer size, maybe a problem, may need to change to threads
            PipedOutputStream leftOutput = new PipedOutputStream(rightInput); //pipes stay connected throughout the program
            
            this.left.evaluate(input, leftOutput);
            leftOutput.close(); //need to close the left output stream or program still assumes its waiting for an input, an no print to terminal
            this.right.evaluate(rightInput, output);
            //will print out for us whenever it reaches the end of the pipe

        } catch (IOException e) {
            throw new RuntimeException();
        }
        
    }

}