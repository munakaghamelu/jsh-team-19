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

public class JshTest {
    public JshTest() {
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

//     @Test
//     public void lsTest5() throws Exception {
//         PipedInputStream in = new PipedInputStream();
//         PipedOutputStream out;
//         out = new PipedOutputStream(in);
//         newShell.eval("ls dir3", out);
//         Scanner scn = new Scanner(in);
//         assertEquals(scn.next(),"ls: no such directory");
//         scn.close();
//     }

//     @Test
//     public void lsTest6() throws Exception {
//         PipedInputStream in = new PipedInputStream();
//         PipedOutputStream out;
//         out = new PipedOutputStream(in);
//         newShell.eval("ls emptydir", out);
//         Scanner scn = new Scanner(in);
//         assertEquals(scn.next(),"\n");
//         scn.close();
//     }
    

}
