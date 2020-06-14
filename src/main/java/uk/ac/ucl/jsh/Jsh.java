package uk.ac.ucl.jsh;

import java.io.File;
import java.io.OutputStream;
import java.util.Scanner;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.*;

public class Jsh {

    private static String currentDirectory = System.getProperty("user.dir");

    public static String getCurrentDirectory() {
        return currentDirectory;
    }

    public static void setCurrentDirectory(String newDirectory) throws Exception {
        currentDirectory = newDirectory;
        File checkDir = new File(currentDirectory);
        if(!checkDir.exists())
        {
            throw new Exception("Jsh: new cwd does not exist"); 
        }
    }

    public void eval(String input, OutputStream out) throws Exception {
        
        CharStream parserInput;
        EvalVisitor visitor = new EvalVisitor();

        parserInput = CharStreams.fromString(input); 
        
        JshLexer lexer = new JshLexer(parserInput);        
        CommonTokenStream tokens = new CommonTokenStream(lexer);        
        JshParser parser = new JshParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(ErrorListener.INSTANCE);

        ParseTree tree = parser.r(); 
        Command command = visitor.visit(tree);
        command.evaluate(null, out); //initially pass null
    }

    static void checkArguments(String[] args) {
        if (args.length != 2) {
            System.out.println("jsh: wrong number of arguments");
            return;
        }
        if (!args[0].equals("-c")) {
            System.out.println("jsh: " + args[0] + ": unexpected argument");
        }
    }

    private static void prompt(Jsh shell, Scanner input) {
        while (true) {
            String terminalOut = Jsh.getCurrentDirectory() + "> ";
            System.out.print(terminalOut);
            try {
                String cmdline = input.nextLine();
                shell.eval(cmdline, System.out);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to JSH!");
        OutputStream out = System.out; 
        Jsh shell = new Jsh();
        if (args.length > 0) {
            checkArguments(args);
            try {
                shell.eval(args[1], out);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } 
        } else {
                try (Scanner input = new Scanner(System.in)) {
                    prompt(shell, input);
                }
            }
        }
}
