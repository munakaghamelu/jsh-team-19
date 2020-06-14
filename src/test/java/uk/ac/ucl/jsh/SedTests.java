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

public class SedTests {

    public SedTests() {
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
    public void sedBasicTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            for (int i = 0; i < 5 ; i++){
                printOut.println(i);
                printOut.flush();
            }
            printOut.close(); 
        }
        newShell.eval("sed s/1/one test1.txt", out);
        Scanner scn = new Scanner(in); 
        for (int i = 0; i < 5 ; i++){
            if (i == 1){
                assertEquals(scn.nextLine(), "one");
            }
            else {
                assertEquals(scn.nextLine(), Integer.toString(i));
            }
        }
        scn.close();
    }

    @Test
    public void sedReplaceOne() throws Exception {
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
        newShell.eval("sed s/little/huge test1.txt", out);
        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "Mary had a huge little lamb");
        assertEquals(scn.nextLine(), "Little lamb, huge lamb");
        assertEquals(scn.nextLine(), "Twinkle, twinkle huge star");
        scn.close();
    }

    @Test
    public void sedNewDelim() throws Exception {
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
        newShell.eval("sed s&little&huge test1.txt", out);
        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "Mary had a huge little lamb");
        assertEquals(scn.nextLine(), "Little lamb, huge lamb");
        assertEquals(scn.nextLine(), "Twinkle, twinkle huge star");
        scn.close();
    }

    @Test
    public void sedReplaceAll() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Mary had a little little lamb");
            printOut.flush();
            printOut.println("little lamb, little lamb");
            printOut.flush();
            printOut.println("Twinkle, twinkle little star");
            printOut.flush();
            printOut.close(); 
        }
        newShell.eval("sed s/little/huge/g test1.txt", out);
        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "Mary had a huge huge lamb");
        assertEquals(scn.nextLine(), "huge lamb, huge lamb");
        assertEquals(scn.nextLine(), "Twinkle, twinkle huge star");
        scn.close();
    }

    @Test
    public void sedRedirTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Mary had a little little lamb");
            printOut.flush();
            printOut.println("little lamb, little lamb");
            printOut.flush();
            printOut.println("Twinkle, twinkle little star");
            printOut.flush();
            printOut.close(); 
        }
        newShell.eval("sed s/little/huge/g < test1.txt", out);
        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "Mary had a huge huge lamb");
        assertEquals(scn.nextLine(), "huge lamb, huge lamb");
        assertEquals(scn.nextLine(), "Twinkle, twinkle huge star");
        scn.close();
    }

    @Test
    public void sedRedirTest2() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Mary had a little little lamb");
            printOut.flush();
            printOut.println("little lamb, little lamb");
            printOut.flush();
            printOut.println("Twinkle, twinkle little star");
            printOut.flush();
            printOut.close(); 
        }
        newShell.eval("sed s/little/huge/ < test1.txt", out);
        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "Mary had a huge little lamb");
        assertEquals(scn.nextLine(), "huge lamb, little lamb");
        assertEquals(scn.nextLine(), "Twinkle, twinkle huge star");
        scn.close();
    }

    @Test
    public void sedNoMatch() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Mary had a little little lamb");
            printOut.flush();
            printOut.println("little lamb, little lamb");
            printOut.flush();
            printOut.println("Twinkle, twinkle little star");
            printOut.flush();
            printOut.close(); 
        }
        newShell.eval("sed s/tiny/huge/g test1.txt", out);
        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "Mary had a little little lamb");
        assertEquals(scn.nextLine(), "little lamb, little lamb");
        assertEquals(scn.nextLine(), "Twinkle, twinkle little star");
        scn.close();
    }

    @Test
    public void sedPipeTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Mary had a little little lamb");
            printOut.flush();
            printOut.println("little lamb, little lamb");
            printOut.flush();
            printOut.println("Twinkle, twinkle little star");
            printOut.flush();
            printOut.close(); 
        }
        newShell.eval("find -name test1.txt | sed s/little/huge/g", out);
        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "Mary had a huge huge lamb");
        assertEquals(scn.nextLine(), "huge lamb, huge lamb");
        assertEquals(scn.nextLine(), "Twinkle, twinkle huge star");
        scn.close();
    }

    @Test(expected = RuntimeException.class)
    public void sedWrongReplacementArgs() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        newShell.eval("sed s/little/huge/s", out);
    }

    @Test(expected = RuntimeException.class)
    public void sedWrongReplacementArgs2() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        newShell.eval("sed r/little/huge/g", out);
    }

    @Test(expected = RuntimeException.class)
    public void sedFileDoesntExist() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        newShell.eval("sed s/little/huge/g noFile.txt", out);
    }


    @Test(expected = RuntimeException.class)
    public void sedTooManyArgs() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        newShell.eval("sed s/little/little/huge/g < test1.txt", out);
    }
}