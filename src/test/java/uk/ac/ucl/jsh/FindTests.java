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

public class FindTests {

    public FindTests() {
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
    public void findTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        System.out.println(Jsh.getCurrentDirectory());

        newShell.eval("find -name *1.txt", out);

        Scanner scn = new Scanner(in); 
        assertEquals("./test1.txt", scn.nextLine());
        scn.close();
    }

    @Test(expected = RuntimeException.class)
    public void findGlobbingTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        System.out.println(Jsh.getCurrentDirectory());

        newShell.eval("find folder* -name nested.txt", out);
    }

    @Test(expected = RuntimeException.class)
    public void findGlobbingTest2() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        System.out.println(Jsh.getCurrentDirectory());

        newShell.eval("find test1.txt -name nested.txt", out);
    }

    @Test
    public void findMultiple() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        // System.out.println(Jsh.getCurrentDirectory());

        newShell.eval("find -name *.txt", out);

        Scanner scn = new Scanner(in); 
        assertEquals("./folder1/nested.txt", scn.nextLine());
        assertEquals("./folder1/nested2.txt", scn.nextLine());
        assertEquals("./result.txt", scn.nextLine());
        assertEquals("./test1.txt", scn.nextLine());
        assertEquals("./test2.txt", scn.nextLine());
        scn.close();
    }    
    
    @Test
    public void findNestedMultiple() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("find folder1 -name *.txt", out);
        Scanner scn = new Scanner(in); 
        assertEquals("./folder1/nested.txt", scn.nextLine());
        assertEquals("./folder1/nested2.txt", scn.nextLine());
        scn.close();
    }

    @Test
    public void findBasic2() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("find folder1 -name nested2.txt", out);
        Scanner scn = new Scanner(in); 
        assertEquals("./folder1/nested2.txt", scn.nextLine());
        scn.close();
    }

    @Test(expected = RuntimeException.class)
    public void noArguments() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("find", out);
    }

    @Test(expected = RuntimeException.class)
    public void findDirectoryIssue() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        System.out.println(Jsh.getCurrentDirectory());

        newShell.eval("find folder3 -name *1.txt", out);
    }

    // @Test(expected = RuntimeException.class)
    // public void findInvalidFile() throws Exception {
    //     PipedInputStream in = new PipedInputStream();
    //     PipedOutputStream out = new PipedOutputStream(in);
    //     newShell.eval("find -name hehe", out);
    // }

    // @Test(expected = RuntimeException.class)
    // public void invalidGlobFind() throws Exception {
    //     PipedInputStream in = new PipedInputStream();
    //     PipedOutputStream out = new PipedOutputStream(in);
    //     newShell.eval("find -name *hehe", out);
    // }

}