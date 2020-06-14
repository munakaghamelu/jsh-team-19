package uk.ac.ucl.jsh;

import java.io.InputStream;
import java.io.OutputStream;

class Sequence implements Command {

    private Command left;
    private Command right;

    public Sequence(Command left, Command right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void evaluate(InputStream input, OutputStream output) throws Exception {

        this.left.evaluate(input, output);

        this.right.evaluate(input, output);     
        //removed try and catch statments here, need to define out own exception, and handle where this is called
    }

}