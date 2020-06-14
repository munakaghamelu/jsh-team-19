package uk.ac.ucl.jsh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Wc implements Application {

    @Override
    public void execute(List<String> args, InputStream input, OutputStream output) throws Exception {
        
        OutputStreamWriter writer = new OutputStreamWriter(output);        
        File file;

        int numOfBytes = 0;
        int numOfWords = 0;
        int numOfNewLines = 0;

        if (input != null){
            //convert the input stream into a string so we can use the string multiple times without the reader getting rid of the result

            InputStreamReader isReader = new InputStreamReader(input, Charset.forName(StandardCharsets.UTF_8.name()));
            BufferedReader reader = new BufferedReader(isReader);

            String word = "";
            ArrayList<String> wordsInFile = new ArrayList<>();

            numOfBytes = input.available();

            int c = 0;
            while ((c = reader.read()) != -1){   
                char currentChar = (char) c;
                if ( currentChar != ' ' && currentChar != '\n') {
                    word += currentChar;
                }
                else if (currentChar == ' '){
                    if (!word.equals("")) wordsInFile.add(word);
                    word = "";
                }
                else if (currentChar == '\n'){
                    if (!word.equals("")) wordsInFile.add(word);
                    word = "";
                    numOfNewLines++;
                }
            }
            if (!word.isEmpty()) wordsInFile.add(word);

            //correction for new line as the last line is considered to have a new line
            numOfWords = wordsInFile.size();

            if (args.size() == 1){
                if (args.get(0).equals("-m")) {
                    writer.write(numOfBytes + "\n");
                    writer.flush();
                }
                else if (args.get(0).equals("-w")) {
                    writer.write(numOfWords + "\n");
                    writer.flush();
                }
                else if (args.get(0).equals("-l")) {
                    writer.write(numOfNewLines + "\n");
                    writer.flush();
                }
            }
            else if (args.size() == 0){
                writer.write(numOfNewLines + " " + numOfWords + " " + numOfBytes + "\n");
                writer.flush();
            }
            else {
                throw new RuntimeException("wc: no such file or directory");
            }
        }
        
        else {
            if (args.isEmpty()){
                throw new RuntimeException("wc error: no arguments given");
            }

            else if (args.size() == 2){
                
                file = new File(Jsh.getCurrentDirectory(), args.get(1));
                
                if (!file.exists()) {
                    throw new RuntimeException("wc: " + args.get(1) + "no such file or directory");
                }
                
                else {
                    //convert file to input stream and then to a string buffer so it can be read from multiple times
                    FileInputStream fStream = new FileInputStream(file);
                    InputStreamReader isReader = new InputStreamReader(fStream);
                    BufferedReader reader = new BufferedReader(isReader);

                    String word = "";
                    ArrayList<String> wordsInFile = new ArrayList<>();

                    numOfBytes = (int) file.length();

                    int c = 0;
                    while ((c = reader.read()) != -1){   
                        char currentChar = (char) c;
                        if ( currentChar != ' ' && currentChar != '\n') {
                            word += currentChar;
                        }
                        else if (currentChar == ' '){
                            if (!word.equals("")) wordsInFile.add(word);
                            word = "";
                        }
                        else if (currentChar == '\n'){
                            if (!word.equals("")) wordsInFile.add(word);
                            word = "";
                            numOfNewLines++;
                        }
                    }

                    if (!word.isEmpty()) wordsInFile.add(word);

                    //correction for new line as the last line is considered to have a new line
                    numOfWords = wordsInFile.size();

                    if (args.get(0).equals("-m")) {
                        writer.write(numOfBytes + "\n");
                        writer.flush();
                    }
                    else if (args.get(0).equals("-w")) {
                        writer.write(numOfWords + "\n");
                        writer.flush();
                    }
                    else if (args.get(0).equals("-l")) {
                        writer.write(numOfNewLines + "\n");
                        writer.flush();
                    }
                    else {
                        throw new RuntimeException("wc: invalid argument");
                    }
                }
            }
            else if (args.size() == 1){
                file = new File(Jsh.getCurrentDirectory(), args.get(0));
                if (!file.exists()) {
                    throw new RuntimeException("wc: " + args.get(0) + "no such file or directory");
                }
                else {
                    FileInputStream fStream = new FileInputStream(file);
                    InputStreamReader isReader = new InputStreamReader(fStream);
                    BufferedReader reader = new BufferedReader(isReader);

                    String word = "";
                    ArrayList<String> wordsInFile = new ArrayList<>();

                    numOfBytes = (int) file.length();

                    int c = 0;
                    while ((c = reader.read()) != -1){   
                        char currentChar = (char) c;
                        if ( currentChar != ' ' && currentChar != '\n') {
                            word += currentChar;
                        }
                        else if (currentChar == ' '){
                            if (!word.equals("")) wordsInFile.add(word);
                            word = "";
                        }
                        else if (currentChar == '\n'){
                            if (!word.equals("")) wordsInFile.add(word);
                            word = "";
                            numOfNewLines++;
                        }
                    }

                    if (!word.isEmpty()) wordsInFile.add(word);

                    //correction for new line as the last line is considered to have a new line
                    numOfWords = wordsInFile.size();

                    writer.write(numOfNewLines + " " + numOfWords + " " + numOfBytes + "\n");
                    writer.flush();
                }
            }
            else {
                throw new RuntimeException("wc: no such file or directory");
            }
        }
    }
}