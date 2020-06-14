package uk.ac.ucl.jsh;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class CatTests {

    public CatTests() {
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
    public void cat2Files() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }
        try (PrintWriter printOut = new PrintWriter(testFile2)) {
            printOut.println("Hello");
            printOut.flush();
            printOut.println("Bye felicia");
            printOut.close(); 
        }
        newShell.eval("cat *.txt", out);
        Scanner scn = new Scanner(in); 
        out.close();
        assertEquals("Hola", scn.nextLine());        
        assertEquals("grep this", scn.nextLine());
        assertEquals("Hello", scn.nextLine());
        assertEquals("Bye felicia", scn.nextLine());
        scn.close();  
    }

    @Test
    public void BasicCatTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        
        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }
        newShell.eval("cat test1.txt", out);
        Scanner scn = new Scanner(in); 
        out.close();
        assertEquals(scn.nextLine(), "Hola");
        assertEquals(scn.nextLine(), "grep this");
        scn.close();
    }

    @Test
    public void CatPipeTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }
        out = new PipedOutputStream(in);
        newShell.eval("find -name test1.txt | cat", out);
        Scanner scn = new Scanner(in); 
        out.close();
        assertEquals(scn.nextLine(), "Hola");
        assertEquals(scn.nextLine(), "grep this");
        scn.close();
    }

    @Test
    public void CatPipeGrepTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }
        out = new PipedOutputStream(in);
        newShell.eval("grep \"grep\" test1.txt | cat", out);
        Scanner scn = new Scanner(in); 
        out.close();
        assertEquals(scn.nextLine(), "grep this");
        scn.close();
    }

    @Test
    public void CatPipeItselfTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.println("\n");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }
        out = new PipedOutputStream(in);
        newShell.eval("cat test1.txt | cat", out);
        Scanner scn = new Scanner(in); 
        out.close();
        assertEquals(scn.nextLine(), "Hola");
        assertEquals(scn.nextLine(), "");
        assertEquals(scn.nextLine(), "");
        assertEquals(scn.nextLine(), "grep this");
        scn.close();
    }

    @Test
    public void CatPipeDirTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;

        out = new PipedOutputStream(in);
        newShell.eval("find -name folder1 | cat", out);
        out.close();
        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "");
        scn.close();
    }

    @Test
    public void catNestedGlobTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(nestedFile)) {
            printOut.println("Foo");
            printOut.flush();
            printOut.close(); 
        }
        try (PrintWriter printOut = new PrintWriter(nestedFile2)) {
            printOut.println("Boo");
            printOut.flush();
            printOut.close(); 
        }
        newShell.eval("cat folder1/n*.txt", out);
        Scanner scn = new Scanner(in); 
        out.close();
        assertEquals("Foo", scn.nextLine());     
        assertEquals("Boo", scn.nextLine());     
        scn.close();  
    }

    @Test
    public void catNestedTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        try (PrintWriter printOut = new PrintWriter(nestedFile)) {
            printOut.println("Foo");
            printOut.flush();
            printOut.close(); 
        }
        newShell.eval("cat folder1/nested.txt", out);
        Scanner scn = new Scanner(in); 
        out.close();

        assertEquals("Foo", scn.nextLine());        
        scn.close();  
    }

    @Test
    public void catTwoRedirTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        try (PrintWriter printOut = new PrintWriter(nestedFile)) {
            printOut.println("Foo");
            printOut.println("Shortcakes");
            printOut.flush();
            printOut.close(); 
        }
        newShell.eval("cat < folder1/nested.txt >result.txt", out);
        Path path = Paths.get(Jsh.getCurrentDirectory(), "result.txt"); 
        Scanner scn = new Scanner(path.toFile()); 
        out.close();

        assertEquals("Foo", scn.nextLine());  
        assertEquals("Shortcakes", scn.nextLine());             
        scn.close();  
    }

    @Test
    public void catRedirTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        try (PrintWriter printOut = new PrintWriter(nestedFile)) {
            printOut.println("Foo");
            printOut.flush();
            printOut.close(); 
        }
        newShell.eval("> result.txt cat folder1/nested.txt", out);
        Scanner scn = new Scanner(resultFile); 
        out.close();
        out.close();
        assertEquals("Foo", (String) scn.nextLine());     
        scn.close();  
    }

    @Test(expected = RuntimeException.class)
    public void catNoArgumentsNoInput() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("cat", out);
    }

    @Test(expected = RuntimeException.class)
    public void catInvalidFile() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("cat muna", out);
    }

    @Test(expected = RuntimeException.class)
    public void catInvalidFileRedir() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("cat < muna.txt", out);
    }

    @Test
    public void catFolderIO() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("echo folder1 | cat", out);
        assertEquals(in.available(), 0);
    }

}