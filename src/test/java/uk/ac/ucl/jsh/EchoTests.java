package uk.ac.ucl.jsh;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class EchoTests {

    public EchoTests() {
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
        testFile1 = tempFolder.newFile("test1.txt");
        testFile2 = tempFolder.newFile("test2.txt"); 
        dirFile = tempFolder.newFolder("folder1");
        nestedFile = new File(dirFile, "nested.txt");
        nestedFile.createNewFile();
        nestedFile2 = new File(dirFile, "nested2.txt");
        nestedFile2.createNewFile();
        resultFile = tempFolder.newFile("result.txt");
        resultFile.createNewFile();
        hidden = tempFolder.newFile(".hidden.txt"); 
        hidden.createNewFile();
    }

    @After
    public void afterTest() throws IOException
    {
        tempFolder.delete();
    }

    @Test
    public void echoWhitespaceTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        newShell.eval("echo t   t", out);
        Scanner scn = new Scanner(in); 
        assertEquals("t t", scn.nextLine());
        scn.close();
    }

    @Test
    public void echoJustCommandSub() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        newShell.eval("echo `echo hi`", out);
        Scanner scn = new Scanner(in); 
        assertEquals("hi", scn.nextLine());
        scn.close();
    }

    // @Test
    // public void echoAFewCommandSub() throws Exception {
    //     PipedInputStream in = new PipedInputStream();
    //     PipedOutputStream out;
    //     out = new PipedOutputStream(in);
    //     newShell.eval("echo `echo hi` ho haha", out);
    //     Scanner scn = new Scanner(in); 
    //     assertEquals("ho hi", scn.nextLine());
    //     scn.close();
    // }

    @Test
    public void echoHidden() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        newShell.eval("echo *", out);
        Scanner scn = new Scanner(in); 
        assertEquals("folder1", scn.nextLine());
        assertEquals("result.txt", scn.nextLine());
        assertEquals("test1.txt", scn.nextLine());
        assertEquals("test2.txt", scn.nextLine());
        scn.close();
    }

    @Test
    public void echoSplittingTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        // testing echo and globbing
        newShell.eval("echo a\"b\"c", out);
      
        Scanner scn = new Scanner(in); 
        assertEquals("abc", scn.nextLine());
        scn.close();
    }

    @Test
    public void echoWithCommand() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        // testing echo and globbing
        newShell.eval("echo cat test.1.txt", out);
      
        Scanner scn = new Scanner(in); 
        assertEquals("cat test.1.txt", scn.nextLine());
        scn.close();
    }

    @Test
    public void echoEcho() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        newShell.eval("echo echo", out);
        Scanner scn = new Scanner(in); 
        assertEquals("echo", scn.nextLine());
        scn.close();
    }

    @Test 
    public void globButNotFileTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        newShell.eval("echo *holaAmigo", out);
        Scanner scn = new Scanner(in); 
        assertEquals("*holaAmigo", scn.nextLine());
        scn.close();
    }

    @Test
    public void echoGlobSpaceCheckTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        newShell.eval("echo t*.txt", out);
        Scanner scn = new Scanner(in); 
        assertEquals("test1.txt", scn.nextLine());
        assertEquals("test2.txt", scn.nextLine());
        scn.close();
    }

    @Test
    public void echoGlobTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        newShell.eval("echo " + Jsh.getCurrentDirectory() + "/*.txt", out);
        Scanner scn = new Scanner(in); 
        assertEquals("result.txt", scn.nextLine());
        assertEquals("test1.txt", scn.nextLine());
        assertEquals("test2.txt", scn.nextLine());
        scn.close();
    }
}