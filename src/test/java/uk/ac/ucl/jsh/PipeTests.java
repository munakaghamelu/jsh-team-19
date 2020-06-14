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

public class PipeTests {

    public PipeTests() {
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
    public void wcCatPipeTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this");
            printOut.close(); 
        }

        newShell.eval("cat test1.txt | wc", out);

        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "2 3 15");
        scn.close();
    }

    @Test
    public void catGrepPipeTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Hola");
            printOut.flush();
            printOut.println("grep this cat");
            printOut.close(); 
        }
        newShell.eval("cat test1.txt | grep \"cat\"", out);

        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "grep this cat");
        scn.close();
    }

    @Test
    public void findCatPipeTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Winter");
            printOut.flush();
            printOut.println("Summer and Spring");
            printOut.close(); 
        }
        try (PrintWriter printOut = new PrintWriter(testFile2)) {
            printOut.println("Orange");
            printOut.flush();
            printOut.println("Apple and Banana");
            printOut.close(); 
        }
        try (PrintWriter printOut = new PrintWriter(nestedFile)) {
            printOut.println("Pineapple");
            printOut.flush();
            printOut.close(); 
        }
        try (PrintWriter printOut = new PrintWriter(nestedFile2)) {
            printOut.println("Red Velvet");
            printOut.flush();
            printOut.println("Irene and Joy");
            printOut.close(); 
        }
        newShell.eval("find -name *.txt | cat", out);

        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "Pineapple");
        assertEquals(scn.nextLine(), "");
        assertEquals(scn.nextLine(), "Red Velvet");
        assertEquals(scn.nextLine(), "Irene and Joy");
        assertEquals(scn.nextLine(), "");
        assertEquals(scn.nextLine(), "Winter");
        assertEquals(scn.nextLine(), "Summer and Spring");
        assertEquals(scn.nextLine(), "");
        assertEquals(scn.nextLine(), "Orange");
        assertEquals(scn.nextLine(), "Apple and Banana");
        scn.close();
    }

    @Test
    public void echoCatPipeTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Winter");
            printOut.flush();
            printOut.println("Summer and Spring");
            printOut.close(); 
        }
        try (PrintWriter printOut = new PrintWriter(testFile2)) {
            printOut.println("Orange");
            printOut.flush();
            printOut.println("Apple and Banana");
            printOut.close(); 
        }

        newShell.eval("echo t*.txt | cat", out);
        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "Winter");
        assertEquals(scn.nextLine(), "Summer and Spring");
        assertEquals(scn.nextLine(), "");
        assertEquals(scn.nextLine(), "Orange");
        assertEquals(scn.nextLine(), "Apple and Banana");
        scn.close();
    }

    @Test
    public void headGrepPipeTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Winter");
            printOut.flush();
            printOut.println("Summer and Spring");
            printOut.flush();
            printOut.println("Winter Wonderland is a scam");
            printOut.close(); 
        }

        newShell.eval("head -n 2 test1.txt | grep 'Winter'", out);
        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "Winter");
        scn.close();
    }

    @Test
    public void headRedirGrepPipeTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Winter");
            printOut.flush();
            printOut.println("Summer and Spring");
            printOut.flush();
            printOut.println("Winter Wonderland is a scam");
            printOut.close(); 
        }

        newShell.eval("head -n 3 < test1.txt | grep 'Winter'", out);
        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "Winter");
        assertEquals(scn.nextLine(), "Winter Wonderland is a scam");
        scn.close();
    }

    @Test
    public void catHeadTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Winter");
            printOut.flush();
            printOut.println("Summer and Spring");
            printOut.flush();
            printOut.println("Winter Wonderland is a scam");
            printOut.close(); 
        }
        try (PrintWriter printOut = new PrintWriter(testFile2)) {
            printOut.println("Spaghetti");
            printOut.flush();
            printOut.println("Lasagna and Bao");
            printOut.flush();
            printOut.println("Custard Buns are amazing");
            printOut.close(); 
        }

        newShell.eval("cat tes*.txt | head -n 4", out);
        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "Winter");
        assertEquals(scn.nextLine(), "Summer and Spring");
        assertEquals(scn.nextLine(), "Winter Wonderland is a scam");
        assertEquals(scn.nextLine(), "Spaghetti");
        scn.close();
    }

    @Test
    public void findCatTest() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        try (PrintWriter printOut = new PrintWriter(testFile1)) {
            printOut.println("Winter");
            printOut.flush();
            printOut.println("Summer and Spring");
            printOut.flush();
            printOut.println("Winter Wonderland is a scam");
            printOut.close(); 
        }
        try (PrintWriter printOut = new PrintWriter(testFile2)) {
            printOut.println("Spaghetti");
            printOut.flush();
            printOut.println("Lasagna and Bao");
            printOut.flush();
            printOut.println("Custard Buns are amazing");
            printOut.close(); 
        }

        newShell.eval("find -name tes*.txt | cat", out);
        Scanner scn = new Scanner(in); 
        assertEquals(scn.nextLine(), "Winter");
        assertEquals(scn.nextLine(), "Summer and Spring");
        assertEquals(scn.nextLine(), "Winter Wonderland is a scam");
        assertEquals(scn.nextLine(), "");
        assertEquals(scn.nextLine(), "Spaghetti");
        assertEquals(scn.nextLine(), "Lasagna and Bao");
        assertEquals(scn.nextLine(), "Custard Buns are amazing");
        scn.close();
    }

    // @Test
    //     public void headCatTest() throws Exception {
    //     PipedInputStream in = new PipedInputStream();
    //     PipedOutputStream out = new PipedOutputStream(in);

    //     try (PrintWriter printOut = new PrintWriter(testFile1)) {
    //         printOut.println("Winter");
    //         printOut.flush();
    //         printOut.println("Summer and Spring");
    //         printOut.flush();
    //         printOut.println("Winter Wonderland is a scam");
    //         printOut.close(); 
    //     }
    //     try (PrintWriter printOut = new PrintWriter(testFile2)) {
    //         printOut.println("Spaghetti");
    //         printOut.flush();
    //         printOut.println("Lasagna and Bao");
    //         printOut.flush();
    //         printOut.println("Custard Buns are amazing");
    //         printOut.close(); 
    //     }

    //     newShell.eval("head -n 5 test*.txt | cat", out);
    //     Scanner scn = new Scanner(in); 
    //     assertEquals(scn.nextLine(), "Winter");
    //     assertEquals(scn.nextLine(), "Summer and Spring");
    //     assertEquals(scn.nextLine(), "Winter Wonderland is a scam");
    //     assertEquals(scn.nextLine(), "");
    //     assertEquals(scn.nextLine(), "Spaghetti");
    //     scn.close();
    // }

    // @Test
    // public void multiplePipeToWcTest() throws Exception {
    //     PipedInputStream in = new PipedInputStream();
    //     PipedOutputStream out = new PipedOutputStream(in);

    //     try (PrintWriter printOut = new PrintWriter(testFile1)) {
    //         printOut.println("Winter");
    //         printOut.flush();
    //         printOut.println("Summer and Spring");
    //         printOut.flush();
    //         printOut.println("Winter Wonderland is a scam");
    //         printOut.close(); 
    //     }
    //     try (PrintWriter printOut = new PrintWriter(testFile2)) {
    //         printOut.println("Spaghetti");
    //         printOut.flush();
    //         printOut.println("Lasagna and Bao");
    //         printOut.flush();
    //         printOut.println("Custard Buns are amazing");
    //         printOut.close(); 
    //     }

    //     newShell.eval("head -n 5 test*.txt | cat | wc", out);
    //     Scanner scn = new Scanner(in);
    //     out.close(); 
    //     assertEquals("7 23 139", scn.nextLine());
    //     scn.close();
    // }

}
