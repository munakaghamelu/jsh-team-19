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
public class LsPwdTests {

    public LsPwdTests() {
    }

    Jsh newShell;
    File testFile1;
    File testFile2;
    File testFile3;
    File dirFile;
    File nestedFile;
    File nestedFile2; 
    File emptyFile; 

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void beforeTest() throws Exception {
        newShell = new Jsh();
        Jsh.setCurrentDirectory(tempFolder.getRoot().getPath());
        testFile1 = tempFolder.newFile("test1.txt");
        testFile1.createNewFile();
        testFile2 = tempFolder.newFile("test2.txt"); 
        testFile2.createNewFile();
        testFile3 = tempFolder.newFile(".test3.txt");
        testFile3.createNewFile(); 
        dirFile = tempFolder.newFolder("folder1");
        emptyFile = tempFolder.newFolder("empty");
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
    public void lsCurrentDirectory() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        newShell.eval("ls", out);
        Scanner scn = new Scanner(in);
        assertEquals("empty" + "\t" + "folder1" + "\t" + "test1.txt" + "\t" + "test2.txt" + "\t", scn.nextLine());
        scn.close();
    }

    @Test
    public void lsEmptyDir() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Jsh.setCurrentDirectory(Jsh.getCurrentDirectory()+"/empty");
        newShell.eval("ls", out);
        assertEquals(in.available(), 0);
    }

    @Test
    public void pwdTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        newShell.eval("pwd", out);
        Scanner scn = new Scanner(in);
        assertEquals(tempFolder.getRoot().getPath(), scn.nextLine());
        scn.close();
    }

    @Test(expected = RuntimeException.class)
    public void cdFile() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("ls folder1 folder2 folder3", out);
    }
}