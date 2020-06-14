package uk.ac.ucl.jsh;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
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

public class GlobbingTests {

    public GlobbingTests() {
    }

    Jsh newShell;
    File testFile1;
    File testFile2;
    File dirFile;
    File nestedFile;
    File nestedFile2; 

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
    }

    @After
    public void afterTest() throws IOException
    {
        tempFolder.delete();
    }

    @Test
    public void echoGlobTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("echo *.txt", out);
        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "test1.txt");
        assertEquals(scn.nextLine(), "test2.txt");
        scn.close();
    }

    @Test
    public void echoNestedGlobTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("echo folder1/n*.txt", out);
        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "folder1/nested.txt");
        assertEquals(scn.nextLine(), "folder1/nested2.txt");
        scn.close();
    }

    @Test
    public void echoHiddenTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("echo folder1/*.txt", out);
        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "folder1/nested.txt");
        assertEquals(scn.nextLine(), "folder1/nested2.txt");
        scn.close();
    }

    @Test
    public void backSlashFile() throws Exception {
        //need to implement this after checking with Nadhirah
    }

    // @Test
    // public void argumentButNotPathTest() throws Exception {
    //     PipedInputStream in = new PipedInputStream();
    //     PipedOutputStream out = new PipedOutputStream(in);
    //     newShell.eval("echo folder1/*hola", out);
    //     Scanner scn = new Scanner(in); 
    //     assertEquals("folder1/*hola", scn.nextLine());
    //     scn.close();
    // }

    @Test(expected = FileNotFoundException.class)
    public void noFilesForGlobbing() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("cat *", out);
    }

    @Test(expected = RuntimeException.class)
    public void ApplicationFactoryTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("null test", out);
    }

}