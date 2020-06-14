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

public class HeadTailTests {

    public HeadTailTests() {
    }

    Jsh newShell;
    File testFile1;
    File testFile2;
    File dirFile;
    File nestedFile;
    File nestedFile2; 
    File resultFile;
    File testFile; 

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void beforeTest() throws Exception {
        newShell = new Jsh();
        Jsh.setCurrentDirectory(tempFolder.getRoot().getPath());
        testFile1 = tempFolder.newFile("test1.txt");
        testFile2 = tempFolder.newFile("test2.txt"); 
        dirFile = tempFolder.newFolder("folder1");
        testFile = tempFolder.newFolder("testFolder");
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
    public void headNoBoundTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            for (int i = 0; i < 20 ; i++){
                printOut.println(i);
                printOut.flush();
            }
            printOut.close(); 
        }

        newShell.eval("head test1.txt", out);
        Scanner scn = new Scanner(in); 
        for (int i = 0; i < 10 ; i++){
            assertEquals(scn.nextLine(), Integer.toString(i));
        }
        scn.close();
    }

    @Test
    public void tailNoBoundTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            for (int i = 0; i < 20 ; i++){
                printOut.println(i);
                printOut.flush();
            }
            printOut.close(); 
        }
        newShell.eval("tail test1.txt", out);
        Scanner scn = new Scanner(in); 
        for (int i = 10; i < 20 ; i++){
            assertEquals(scn.nextLine(), Integer.toString(i));
        }
        scn.close();
    }

    @Test
    public void headWithBoundTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            for (int i = 0; i < 20 ; i++){
                printOut.println(i);
                printOut.flush();
            }
            printOut.close(); 
        }
        newShell.eval("head -n 2 test1.txt", out);
        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "0");
        assertEquals(scn.nextLine(), "1");
        scn.close();
    }

    @Test
    public void headRedirTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            for (int i = 0; i < 20 ; i++){
                printOut.println(i);
                printOut.flush();
            }
            printOut.close(); 
        }
        newShell.eval("head -n 3 < test1.txt", out);

        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "0");
        assertEquals(scn.nextLine(), "1");
        assertEquals(scn.nextLine(), "2");
        scn.close();
    }

    @Test
    public void headRedirNoBoundTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            for (int i = 0; i < 20 ; i++){
                printOut.println(i);
                printOut.flush();
            }
            printOut.close(); 
        }
        newShell.eval("head < test1.txt", out);
        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "0");
        assertEquals(scn.nextLine(), "1");
        assertEquals(scn.nextLine(), "2");
        assertEquals(scn.nextLine(), "3");
        assertEquals(scn.nextLine(), "4");
        assertEquals(scn.nextLine(), "5");
        assertEquals(scn.nextLine(), "6");
        assertEquals(scn.nextLine(), "7");
        assertEquals(scn.nextLine(), "8");
        assertEquals(scn.nextLine(), "9");
        scn.close();
    }

    @Test
    public void tailWithBound() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            for (int i = 0; i < 20 ; i++){
                printOut.println(i);
                printOut.flush();
            }
            printOut.close(); 
        }
        newShell.eval("tail -n 2 test1.txt", out);
        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "18");
        assertEquals(scn.nextLine(), "19");
        scn.close();
    }

    @Test
    public void tailWithStreamNotEmpty() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            for (int i = 0; i < 20 ; i++){
                printOut.println(i);
                printOut.flush();
            }
            printOut.close(); 
        }
        newShell.eval("cat test1.txt | tail -n 2", out);
        Scanner scn = new Scanner(in);
        assertEquals("18", scn.nextLine());
        assertEquals("19", scn.nextLine());
        scn.close();
    }

    @Test
    public void tailGlobNoInputTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            for (int i = 0; i < 20 ; i++){
                printOut.println(i);
                printOut.flush();
            }
            printOut.close(); 
        }
        try (PrintWriter printOut = new PrintWriter(testFile2)) {
            for (int i = 0; i < 20 ; i++){
                printOut.println(i);
                printOut.flush();
            }
            printOut.close(); 
        }
        newShell.eval("tail -n 2 *.txt", out);
        Scanner scn = new Scanner(in);
        assertEquals("18", scn.nextLine());
        assertEquals("19", scn.nextLine());
        scn.close();
    }

    @Test
    public void tailGlobbingNotEmptyTest() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            for (int i = 0; i < 20 ; i++){
                printOut.println(i);
                printOut.flush();
            }
            printOut.close(); 
        }
        try (PrintWriter printOut = new PrintWriter(testFile2)) {
            for (int i = 0; i < 20 ; i++){
                printOut.println(i);
                printOut.flush();
            }
            printOut.close(); 
        }
        newShell.eval("cat *.txt | tail -n 2", out);
        Scanner scn = new Scanner(in);
        assertEquals("18", scn.nextLine());
        assertEquals("19", scn.nextLine());
        scn.close();
    }

    @Test(expected = RuntimeException.class)
    public void invalidArgumentTail() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("tail -n kk arg1", out);
    }

    @Test(expected = RuntimeException.class)
    public void invalidFileHead() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("head -n 2 nullFile.txt", out);
    }

    @Test(expected = RuntimeException.class)
    public void invalidFileTail() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("tail -n 2 nullFile.txt", out);
    }

    @Test(expected = RuntimeException.class)
    public void headOfDirectory() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("head -n 2 src", out);
    }

    @Test(expected = RuntimeException.class)
    public void noArgumentsHead() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("head", out);
    }

    @Test(expected = RuntimeException.class)
    public void noArgumentsTail() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("tail", out);
    }

    @Test(expected = RuntimeException.class)
    public void headWrongNumberOfArguments() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("head arg1 arg2", out);
    }

    @Test(expected = RuntimeException.class)
    public void tailWrongNumberOfArguments() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("tail arg1 arg2", out);
    }


    @Test(expected = RuntimeException.class)
    public void noArgumentNoInputTail() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("tail", out);
    }

    @Test(expected = RuntimeException.class)
    public void noArgumentNoInputHead() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("head", out);
    }

    @Test(expected = RuntimeException.class)
    public void tooManyArgumentsNoInput() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("tail arg1 arg2 arg3 arg4 arg5", out);
    }

    @Test(expected = RuntimeException.class)
    public void tooLittleArgumentsNoInput() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("head arg1", out);
    }

    @Test(expected = RuntimeException.class)
    public void wrongArgHead() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("head -r 8 arg3", out);
    }

    @Test(expected = RuntimeException.class)
    public void tooManyArgumentsNoInputHead() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("head arg1 arg2 arg3 arg4 arg5", out);
    }

    @Test(expected = RuntimeException.class)
    public void threeArgumentsNoNTail() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("tail -arg1 arg2 arg3", out);
    }

    @Test(expected = RuntimeException.class)
    public void wrongArgumentTailTest() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("tail arg1 arg2 arg3 arg4", out);
    }

    @Test(expected = RuntimeException.class)
    public void tailWithStreamEmpty() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("cat test1.txt | tail -n 2", out);
    }

    @Test(expected = RuntimeException.class)
    public void tailWithStreamGlobbingTestEmpty() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("cat *.txt | tail -n 2", out);
    }
    
    @Test(expected = RuntimeException.class)
    public void threeArgumentsNoHead() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("head arg1 arg2 arg3", out);
    }
    
    @Test
    public void inputFileNamePipe() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("find -name test.txt | head", out);
        assertEquals(in.available(), 0);
    }

    @Test(expected = RuntimeException.class)
    public void inputFileNamePipeTail() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("find -name test.txt | tail", out);
    }

    @Test
    public void inputFileNamePipeTailNotEmpty() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hey");
            printOut.flush();
            printOut.close(); 
        }
        newShell.eval("find -name test1.txt | tail", out);
        Scanner scn = new Scanner(in);
        assertEquals("Hey", scn.nextLine());
        scn.close();
    }

    @Test
    public void headPipeTest2() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hey");
            printOut.flush();
            printOut.println("How are you doin");
            printOut.flush();
            printOut.println("Watermelon Sugar");
            printOut.close(); 
        }
        newShell.eval("find -name test1.txt | head -n 1", out);
        Scanner scn = new Scanner(in);
        assertEquals("Hey", scn.nextLine());
        scn.close();
    }

    @Test
    public void headBasicTest3() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hey");
            printOut.flush();
            printOut.println("Watermelon Sugar");
            printOut.close(); 
        }
        newShell.eval("find -name test1.txt | head", out);
        Scanner scn = new Scanner(in);
        assertEquals("Hey", scn.nextLine());
        assertEquals("Watermelon Sugar", scn.nextLine());
        scn.close();
    }

    @Test
    public void headGlobbingTest() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hey");
            printOut.flush();
            printOut.println("Watermelon Sugar");
            printOut.flush();
            printOut.println("Rainbow Cronut");
            printOut.close(); 
        }
        try (PrintWriter printOut = new PrintWriter(testFile2)) {
            printOut.println("Summer Sky");
            printOut.flush();
            printOut.println("Brown skin and lemons");
            printOut.flush();
            printOut.println("Jar Jar Binks");
            printOut.close(); 
        }
        newShell.eval("head -n 1 t*.txt", out);
        Scanner scn = new Scanner(in);
        assertEquals("Hey", scn.nextLine());
        assertEquals("", scn.nextLine());
        assertEquals("Summer Sky", scn.nextLine());
        scn.close();
    }

    @Test
    public void headGlobbingFolder() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hey");
            printOut.flush();
            printOut.println("Watermelon Sugar");
            printOut.flush();
            printOut.println("Rainbow Cronut");
            printOut.close(); 
        }
        try (PrintWriter printOut = new PrintWriter(testFile2)) {
            printOut.println("Summer Sky");
            printOut.flush();
            printOut.println("Brown skin and lemons");
            printOut.flush();
            printOut.println("Jar Jar Binks");
            printOut.close(); 
        }
        newShell.eval("head -n 1 t*", out);
        Scanner scn = new Scanner(in);
        assertEquals("Hey", scn.nextLine());
        assertEquals("", scn.nextLine());
        assertEquals("Summer Sky", scn.nextLine());
        scn.close();
    }



    @Test(expected = RuntimeException.class)
    public void tailignoreDirectoriesIfPassedTest() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("echo * | tail", out);
    }

    @Test(expected = RuntimeException.class)
    public void headWrongArgQty() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("head -n 5 file.txt file2.txt", out);
    }

    @Test(expected = RuntimeException.class)
    public void headWrongArg() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("head -r 5 file.txt", out);
    }

    @Test
    public void globFileNameCheck() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hey");
            printOut.flush();
            printOut.close(); 
        }

        try (PrintWriter printOut = new PrintWriter(testFile2)) {
            printOut.println("Hola");
            printOut.flush(); 
            printOut.close(); 
        }
        newShell.eval("cat *.txt | tail", out);
        Scanner scn = new Scanner(in);
        assertEquals("Hey", scn.nextLine());
        assertEquals("Hola", scn.nextLine());
        scn.close();
    }

    @Test
    public void globFileNameCheckHead() throws Exception {
        newShell = new Jsh();
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hey");
            printOut.flush();
            printOut.close(); 
        }

        try (PrintWriter printOut = new PrintWriter(testFile2)) {
            printOut.println("Hola");
            printOut.flush(); 
            printOut.close(); 
        }
        newShell.eval("cat *.txt | head", out);
        Scanner scn = new Scanner(in);
        assertEquals("Hey", scn.nextLine());
        assertEquals("Hola", scn.nextLine());
        scn.close();
    }
}
