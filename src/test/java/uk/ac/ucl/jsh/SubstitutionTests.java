package uk.ac.ucl.jsh;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

public class SubstitutionTests {

    public SubstitutionTests() {
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
    public void echoAndSubstitution() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("echo a `echo foo`", out);
        Scanner scn = new Scanner(in); 
        assertEquals("a foo", scn.nextLine());
        scn.close();    
    }

    // @Test
    // public void basicCommandSubstition() throws Exception {
    //     PipedInputStream in = new PipedInputStream();
    //     PipedOutputStream out = new PipedOutputStream(in);
    //     newShell.eval("echo a`echo a`a", out);
    //     Scanner scn = new Scanner(in); 
    //     assertEquals(scn.nextLine(), "aaa");
    //     scn.close();    
    // }

    @Test
    public void checkOrderTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("echo hello `echo world`", out);
        Scanner scn = new Scanner(in); 
        assertEquals("hello world", scn.nextLine());
        scn.close();    
    }

    @Test (expected = Exception.class)
    public void noLeafContentTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("echo hello ` `", out);
    }

    @Test
    public void redirectionInSubstition() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Mary had a little little lamb");
            printOut.flush();
            printOut.println("Little lamb, little lamb");
            printOut.flush();
            printOut.println("Twinkle, twinkle little star");
            printOut.flush();
            printOut.close(); 
        }

        newShell.eval("echo a `grep \"Interesting String\" < test1.txt > test2.txt`", out);
        Scanner scn = new Scanner(in); 
        assertEquals("a ", scn.nextLine());
        scn.close();
    }

    @Test (expected = RuntimeException.class)
    public void notAFileRedirectionTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        newShell.eval("echo a `grep \"Interesting String\" < hello.txt > test2.txt`", out);
    }

    @Test
    public void removeSingleQuotesTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("echo a `echo 'hi'`", out);
        Scanner scn = new Scanner(in); 
        assertEquals("a hi", scn.nextLine());
        scn.close();
    }

    @Test
    public void removeDoubleQuotesTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("echo a `echo \"hi\"`", out);
        Scanner scn = new Scanner(in); 
        assertEquals("a hi", scn.nextLine());
        scn.close();
    }

    @Test
    public void echoCommandSub() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        newShell.eval("echo a `echo b`", out);
        Scanner scn = new Scanner(in); 
        assertEquals("a b", scn.nextLine());
        scn.close();
    }

    @Test
    public void echoCommandSub2() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("echo '`echo a`'", out);
        Scanner scn = new Scanner(in); 
        assertEquals("`echo a`", scn.nextLine());
        scn.close();
    }

    @Test
    public void sequenceSubstitutionTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("echo a `echo muna ; echo world`", out);
        Scanner scn = new Scanner(in); 
        assertEquals("a muna", scn.nextLine());
        assertEquals("world", scn.nextLine());
        scn.close();
    }

    @Test
    public void pipeSubstitutionTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("echo a `cat test1.txt | wc`", out);
        Scanner scn = new Scanner(in); 
        assertEquals("a 0 0 0", scn.nextLine());
        scn.close();
    }

    // @Test
    // public void redirectedSubstitutionTest() throws Exception {
    //     PipedInputStream in = new PipedInputStream();
    //     PipedOutputStream out = new PipedOutputStream(in);

    //     try (PrintWriter printOut = new PrintWriter(testFile1)) {
    //         printOut.println("foo");
    //         printOut.flush();
    //         printOut.close(); 
    //     }

    //     newShell.eval("echo a `cat < test1.txt`", out);
    //     Scanner scn = new Scanner(in); 
    //     assertEquals("a foo", scn.nextLine());
    //     scn.close();
    // }
}