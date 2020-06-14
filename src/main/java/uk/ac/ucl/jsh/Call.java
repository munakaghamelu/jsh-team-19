package uk.ac.ucl.jsh;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Call
 */
public class Call implements Command {

    private Application application;
    private List<String> args;
    private File redirectionIn;
    private File redirectionOut; 
    private List<String> subCommand = new ArrayList<String>();
    //need to have these redirection stuff done with method, shouldn't be global

    Call(Application application, List<String> args, File redirectionIn, File redirectionOut, List<String> subCommand) {
        this.application = application;
        this.args = args;
        this.redirectionIn = redirectionIn;
        this.redirectionOut = redirectionOut;
        this.subCommand = subCommand;
    }

    @Override
    public void evaluate(InputStream input, OutputStream output) throws Exception {
        InputStream inFile = null;
        OutputStream outFile = null;
        if (redirectionIn != null || redirectionOut != null){
            //a redirection will occur
            // if (redirectionIn == null && redirectionOut != null)
            if (redirectionIn == null)
            {
                inFile = input;
                outFile = new FileOutputStream(redirectionOut);
            }
            // else if (redirectionOut == null && redirectionIn != null)
            else if (redirectionOut == null)
            {
                outFile = output;
                inFile = new FileInputStream(redirectionIn);
            }
            else 
            {
                inFile = new FileInputStream(redirectionIn);
                outFile = new FileOutputStream(redirectionOut);
            }
            this.application.execute(args, inFile, outFile);
        }
        else{
            if (!subCommand.isEmpty()){
    
                for (String cmd : subCommand){

                    CharStream parserInput;
                    SubCmdEvalVisitor visitor = new SubCmdEvalVisitor();
                    parserInput = CharStreams.fromString(cmd); //echo food, echo family
                    Jsh2Lexer lexer = new Jsh2Lexer(parserInput);
                    CommonTokenStream tokens = new CommonTokenStream(lexer);
                    Jsh2Parser parser = new Jsh2Parser(tokens);
                    parser.removeErrorListeners();
                    parser.addErrorListener(ErrorListener.INSTANCE);
                    
                    ParseTree tree = parser.r2();
                    Command command = visitor.visit(tree); 
        
                    ByteArrayOutputStream tempOutput = new ByteArrayOutputStream();
                    command.evaluate(null, tempOutput);
                    String stringResult = new String(tempOutput.toByteArray());

                    //Debugging to check the order of the arguments
                    // System.out.println(stringResult);
                    // System.out.println("args is " + args);
                    if (args.size() > 1) args.add(1, stringResult.trim());
                    else args.add(stringResult.trim());
                    //args.add(1, stringResult.trim());
                
                }
    
                this.application.execute(this.args, input, output); 
            }

            else {
                this.application.execute(this.args, input, output);
            }
        }

        //change try and catch, bad practice error hinding
    }

}