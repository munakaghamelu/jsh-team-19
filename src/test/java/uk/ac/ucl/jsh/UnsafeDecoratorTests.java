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

public class UnsafeDecoratorTests {

    public UnsafeDecoratorTests() {
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

    //cat, cd, echo, find, grep, head, ls, pwd, sed, tail, wc
    @Test
    public void unsafeCat() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("_cat lala ; echo muna", out);
        Scanner scn = new Scanner(in);
        assertEquals("muna", scn.nextLine());
        scn.close();
    }

        //cat, cd, echo, find, grep, head, ls, pwd, sed, tail, wc
        @Test
        public void unsafeMkDir() throws Exception {
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);
            newShell.eval("_mkdir lala lala ; echo muna", out);
            Scanner scn = new Scanner(in);
            assertEquals("muna", scn.nextLine());
            scn.close();
        }

    @Test
    public void unsafeEcho() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("_echo lala ; echo muna", out);
        Scanner scn = new Scanner(in);
        assertEquals("lala", scn.nextLine());
        assertEquals("muna", scn.nextLine());
        scn.close();
    }

    @Test
    public void unsafePwd() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("_pwd ; echo muna", out);
        Scanner scn = new Scanner(in);
        assertEquals(Jsh.getCurrentDirectory(), scn.nextLine());
        assertEquals("muna", scn.nextLine());
        scn.close();
    }

    @Test
    public void unsafeLs() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("_ls jjj ; echo muna", out);
        Scanner scn = new Scanner(in);
        assertEquals("muna", scn.nextLine());
        scn.close();
    }

    @Test
    public void unsafeCd() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("_cd jjj ; echo muna", out);
        Scanner scn = new Scanner(in);
        assertEquals("muna", scn.nextLine());
        scn.close();
    }

    @Test
    public void unsafeFind() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("_find -name jjj ; echo muna", out);
        Scanner scn = new Scanner(in);
        assertEquals("muna", scn.nextLine());
        scn.close();
    }

    @Test
    public void unsafeGrep() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("_grep hiya ; echo muna", out);
        Scanner scn = new Scanner(in);
        assertEquals("muna", scn.nextLine());
        scn.close();
    }

    @Test
    public void unsafeHead() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("_head -n 5 jjj ; echo muna", out);
        Scanner scn = new Scanner(in);
        assertEquals("muna", scn.nextLine());
        scn.close();
    }

    @Test
    public void unsafeSed() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("_sed jjj ; echo muna", out);
        Scanner scn = new Scanner(in);
        assertEquals("muna", scn.nextLine());
        scn.close();
    }

    @Test
    public void unsafeTail() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("_tail jjj ; echo muna", out);
        Scanner scn = new Scanner(in);
        assertEquals("muna", scn.nextLine());
        scn.close();
    }

    @Test
    public void unsafeWc() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        newShell.eval("_wc jjj ; echo muna", out);
        Scanner scn = new Scanner(in);
        assertEquals("muna", scn.nextLine());
        scn.close();
    }
}