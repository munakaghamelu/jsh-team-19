package uk.ac.ucl.jsh;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.http.HttpResponse.ResponseInfo;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class JshTests {

    public JshTests() {
    }

    private PrintStream sysOut;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    Jsh newShell;
    // @Rule
    // public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void beforeTests() throws Exception {
        // Jsh.setCurrentDirectory(tempFolder.getRoot().getPath());
        newShell = new Jsh();
        sysOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void revertStreams() {
        System.setOut(sysOut);
    }

    @Test(expected = Exception.class)
    public void setDirectoryTest() throws Exception {
        Jsh.setCurrentDirectory("hola chica");
    }

    @Test
    public void wrongLengthOfArguments() throws Exception {
        Jsh.main(new String[] {"hello"});
        String result = outContent.toString();
        assertTrue(result.contains("wrong number"));
    }

    @Test
    public void containsCCommandTest() throws Exception {
        Jsh.main(new String[] {"-c", "echo foo"});
        String result = outContent.toString();
        assertEquals("Welcome to JSH!\nfoo\n", result);
    }

    @Test
    public void wrongArgumentJsh() throws Exception {
        Jsh.main(new String[] {"hello", "echo foo"});
        String result = outContent.toString();
        assertTrue(result.contains("jsh:"));
    }

}