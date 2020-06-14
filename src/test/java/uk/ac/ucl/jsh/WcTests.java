package uk.ac.ucl.jsh;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class WcTests {

    public WcTests() {
    }

    Jsh newShell;
    File testFile1;
    File testFile2;
    File dirFile;
    File nestedFile;
    File nestedFile2; 
    File resultFile;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void beforeTest() throws Exception {
        newShell = new Jsh();
        Jsh.setCurrentDirectory(tempFolder.getRoot().getPath());
        testFile1 = tempFolder.newFile("test1.txt");
        testFile2 = tempFolder.newFile("test2.txt"); 
        dirFile = tempFolder.newFolder("folder1");
        nestedFile = new File(dirFile, "nested.txt");
        nestedFile.createNewFile();
        nestedFile2 = new File(dirFile, "nested2.txt");
        nestedFile2.createNewFile();
        resultFile = tempFolder.newFile("result.txt");
    }

    @After
    public void afterTest() throws IOException
    {
        tempFolder.delete();
    }

    @Test
    public void normalWcTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }

        newShell.eval("wc test1.txt", out);
        Scanner scn = new Scanner(in); 
        out.close();

        assertEquals("2 3 15", scn.nextLine());
        scn.close();  
    }

    @Test
    public void WcRedirection() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }

        newShell.eval("wc < test1.txt", out);
        Scanner scn = new Scanner(in); 
        out.close();

        assertEquals("2 3 15", scn.nextLine());
        scn.close();  
    }

    @Test
    public void wcCatPipe() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }

        newShell.eval("cat test1.txt | wc", out);
        Scanner scn = new Scanner(in); 
        out.close();

        assertEquals("2 3 15", scn.nextLine());
        scn.close();  
    }

    @Test
    public void wcLTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }

        newShell.eval("wc -l test1.txt", out);
        Scanner scn = new Scanner(in); 
        out.close();

        assertEquals("2", scn.nextLine());
        scn.close();  
    }

    @Test
    public void wcWTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }

        newShell.eval("wc -w test1.txt", out);
        Scanner scn = new Scanner(in); 
        out.close();

        assertEquals("3", scn.nextLine());
        scn.close();  
    }

    @Test
    public void wcMTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }

        newShell.eval("wc -m test1.txt", out);
        Scanner scn = new Scanner(in); 
        out.close();

        assertEquals("15", scn.nextLine());
        scn.close();  
    }

    @Test
    public void wcLPipeTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }

        newShell.eval("cat test1.txt | wc -l", out);
        Scanner scn = new Scanner(in); 
        out.close();

        assertEquals("2", scn.nextLine());
        scn.close();  
    }

    @Test
    public void wcWPipeTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }

        newShell.eval("cat test1.txt | wc -w", out);
        Scanner scn = new Scanner(in); 
        out.close();

        assertEquals("3", scn.nextLine());
        scn.close();  
    }

    @Test
    public void wcMPipeTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }

        newShell.eval("cat test1.txt | wc -m", out);
        Scanner scn = new Scanner(in); 
        out.close();

        assertEquals("15", scn.nextLine());
        scn.close();  
    }


    public void wcCommandSubstitution() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }

        newShell.eval("wc `find -name test1.txt`", out);
        Scanner scn = new Scanner(in); 
        out.close();

        assertEquals("2 3 15", scn.nextLine());
        scn.close();  
    }

    @Test (expected = RuntimeException.class)
    public void noArgumentWc() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("wc", out);
    }

    @Test (expected = RuntimeException.class)
    public void tooManyArguments() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("wc arg1 arg2 arg3 arg4", out);
    }

    @Test (expected = RuntimeException.class)
    public void invalidArgumentWctest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("wc -k test1.txt", out);
    }

    @Test (expected = RuntimeException.class)
    public void notAFile() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("wc hey", out);
    }

    @Test (expected = RuntimeException.class)
    public void argumentButNotAFileWc() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("wc -l hey", out);
    }

    @Test (expected = RuntimeException.class)
    public void invalidPipedFile() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("cat foo | wc", out);
    }

    @Test
    public void multipleFilesWcCheck() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Winter");
            printOut.flush();
            printOut.println("Summer and Spring");
            printOut.flush();
            printOut.println("Winter Wonderland is a scam");
            printOut.close(); 
        }
        try (PrintWriter printOut = new PrintWriter(testFile2)) {
            printOut.println("Spaghetti");
            printOut.flush();
            printOut.println("Lasagna and Bao");
            printOut.flush();
            printOut.println("Custard Buns are amazing");
            printOut.close(); 
        }

        newShell.eval("cat test*.txt | wc", out);
        Scanner scn = new Scanner(in);
        out.close(); 
        assertEquals("6 17 104", scn.nextLine());
        scn.close();
    }

    @Test
    public void additionalSpacesTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Winter");
            printOut.flush();
            printOut.println("Summer         and Spring");
            printOut.flush();
            printOut.println("Winter Wonderland is a scam     ");
            printOut.close(); 
        }
        try (PrintWriter printOut = new PrintWriter(testFile2)) {
            printOut.println("Spaghetti");
            printOut.flush();
            printOut.println("Lasagna and Bao");
            printOut.flush();
            printOut.println("Custard Buns are amazing");
            printOut.close(); 
        }

        newShell.eval("cat test*.txt | wc", out);
        Scanner scn = new Scanner(in);
        out.close(); 
        assertEquals("6 17 117", scn.nextLine());
        scn.close();
    }

    @Test (expected = RuntimeException.class)
    public void tooManyArgumentsPipe() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("cat test*.txt | wc muna muna", out);
    }

    
}