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

public class SequenceTests {

    public SequenceTests() {
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
    public void echoTwiceSequence() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        newShell.eval("echo hola ; echo muna", out);
        Scanner scn = new Scanner(in);
        assertEquals(scn.nextLine(), "hola");
        assertEquals(scn.nextLine(), "muna");
        scn.close();
    }

    @Test (expected = RuntimeException.class)
    public void wrongRedirection() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        newShell.eval("< fakeFile.txt > test1.txt", out);
    }

    @Test
    public void multipleSequence() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        newShell.eval("echo hola ; echo muna ; echo hehe", out);
        Scanner scn = new Scanner(in);
        assertEquals(scn.nextLine(), "hola");
        assertEquals(scn.nextLine(), "muna");
        assertEquals(scn.nextLine(), "hehe");
        scn.close();
    }

    @Test
    public void multipleSequenceWithTabs() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        newShell.eval("echo     hola", out);
        Scanner scn = new Scanner(in);
        assertEquals(scn.nextLine(), "hola");
        scn.close();
    }

}