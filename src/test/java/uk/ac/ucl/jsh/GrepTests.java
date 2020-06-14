package uk.ac.ucl.jsh;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class GrepTests {

    public GrepTests() {
    }

    Jsh newShell;
    File testFile1;
    File testFile2;
    File dirFile;
    File nestedFile;
    File nestedFile2; 
    File resultFile;
    File hidden;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void beforeTest() throws Exception {
        newShell = new Jsh();
        Jsh.setCurrentDirectory(tempFolder.getRoot().getPath());
        dirFile = tempFolder.newFolder("folder1");
        testFile1 = tempFolder.newFile("test1.txt");
        testFile1.createNewFile();
        testFile2 = tempFolder.newFile("test2.txt"); 
        testFile2.createNewFile();     
        nestedFile = new File(dirFile, "nested.txt");
        nestedFile.createNewFile();
        nestedFile2 = new File(dirFile, "nested2.txt");
        nestedFile2.createNewFile();
        resultFile = tempFolder.newFile("result.txt");
        resultFile.createNewFile();
    }

    @After
    public void afterTest() throws IOException
    {
        tempFolder.delete();
    }

    @Test
    public void grepSingleWord() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }

        newShell.eval("grep \"grep\" test1.txt", out);
        Scanner scn = new Scanner(in); 
        out.close();

        assertEquals("grep this", scn.nextLine());
        scn.close();  
    }

    @Test
    public void grepRedir() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.flush();
            printOut.println("not on this line");
            printOut.flush();
            printOut.println("found a grep?");
            printOut.close(); 
        }

        newShell.eval("grep \"grep\" <test1.txt", out);
        Scanner scn = new Scanner(in); 
        out.close();

        assertEquals("grep this", scn.nextLine());
        assertEquals("found a grep?", scn.nextLine());
        scn.close();  
    }

    @Test
    public void grepTwoRedir() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.flush();
            printOut.println("not on this line");
            printOut.flush();
            printOut.println("found a grep?");
            printOut.close(); 
        }

        newShell.eval("grep \"grep\" <test1.txt >result.txt", out);
        Path file = Paths.get(Jsh.getCurrentDirectory(), "result.txt");
        Scanner scn = new Scanner(file.toFile()); 
        out.close();

        assertEquals("grep this", scn.nextLine());
        assertEquals("found a grep?", scn.nextLine());
        scn.close();  
    }

    @Test
    public void grepPipe() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.flush();
            printOut.println("not on this line");
            printOut.flush();
            printOut.println("found a grep?");
            printOut.close(); 
        }

        newShell.eval("find -name 'test1.txt' | grep \"grep\"", out);
        Scanner scn = new Scanner(in); 
        out.close();
        assertEquals("grep this", scn.nextLine());
        assertEquals("found a grep?", scn.nextLine());
        scn.close();  
    }

    @Test (expected = Exception.class)
    public void grepFolderException() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.flush();
            printOut.println("found a grep?");
            printOut.close(); 
        }
        newShell.eval("grep \"grep\" folder*", out);
        out.close();
    }

    @Test (expected = Exception.class)
    public void grepHiddenException() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.flush();
            printOut.println("found a grep?");
            printOut.close(); 
        }
        newShell.eval("grep \"grep\" .projec*", out);
        out.close();
    }

    @Test
    public void grepUnMatchhable() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }

        newShell.eval("grep \"unmatchable\" test1.txt", out);
        Scanner scn = new Scanner(in); 
        out.close();

        assertEquals(in.available(), 0);
        scn.close();  
    }

    @Test
    public void grepKeywordNotFoundTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }

        newShell.eval("grep \"muna\" test1.txt", out);
        Scanner scn = new Scanner(in); 
        out.close();
        assertEquals(in.available(), 0);
        scn.close(); 
    }

    @Test (expected = RuntimeException.class)
    public void grepNoFile() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        newShell.eval("grep \"muna\" notAFile.txt", out);
    }

    @Test (expected = RuntimeException.class)
    public void grepFolder() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        newShell.eval("grep \"muna\" folder1", out);
    }

    @Test (expected = RuntimeException.class)
    public void grepNoArguments() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        newShell.eval("grep", out);
    }

    @Test (expected = RuntimeException.class)
    public void grepSingleArgumentNoFileTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        newShell.eval("grep \"muna\"", out);
    }

    @Test
    public void grepWithGlobbing() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }

        try (PrintWriter printOut = new PrintWriter(testFile2)) {
            printOut.println("Hola here too");
            printOut.flush();
            printOut.println("Michael Jackson > Beyonce");
            printOut.close(); 
        }

        newShell.eval("grep \"Hola\" t*.txt", out);
        Scanner scn = new Scanner(in); 
        out.close();
        assertEquals("Hola", scn.nextLine());
        assertEquals("Hola here too", scn.nextLine());
        scn.close(); 
    }

    @Test
    public void grepWithGlobbingHidden() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        hidden = tempFolder.newFile(".hidden.txt");
        hidden.createNewFile(); 
        
        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }

        try (PrintWriter printOut = new PrintWriter(testFile2)) {
            printOut.println("Hola here too");
            printOut.flush();
            printOut.println("Michael Jackson > Beyonce");
            printOut.close(); 
        }

        newShell.eval("grep \"Hola\" t*.txt", out);
        Scanner scn = new Scanner(in); 
        out.close();
        assertEquals("Hola", scn.nextLine());
        assertEquals("Hola here too", scn.nextLine());
        scn.close(); 
    }
}
