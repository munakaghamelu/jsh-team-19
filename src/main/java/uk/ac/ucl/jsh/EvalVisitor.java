package uk.ac.ucl.jsh;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

// ADDED A NEW DEPENDANCY
import org.apache.commons.lang3.*;
import uk.ac.ucl.jsh.JshParser.CallContext;
import uk.ac.ucl.jsh.JshParser.PipeContext;
import uk.ac.ucl.jsh.JshParser.SemiCommandContext;

public class EvalVisitor extends JshBaseVisitor<Command> 
{

    @Override
    public Command visitSemiCommand(SemiCommandContext ctx){
        int numberLeaves = ctx.getChildCount();
        boolean leftBool = false;
        Command left = null;
        Command right = null;
        for(int i = 0; i < numberLeaves; i++)
        {
            if(!ctx.children.get(i).getText().equals(" ") && !ctx.children.get(i).getText().equals(";"))
            {
                if(!leftBool)
                {
                    left = ctx.children.get(i).accept(this);
                    leftBool = true; 
                }
                else
                {
                    right = ctx.children.get(i).accept(this);
                    break; 
                }
            }
        }
        return new Sequence(left, right);
    }

    @Override
    public Command visitPipe(PipeContext ctx){
        int numberOfLeaves = ctx.getChildCount();
        boolean leftBool = false;
        Command left = null;
        Command right = null;

        for(int i = 0; i < numberOfLeaves; i++)
        {
            if(!ctx.children.get(i).getText().equals(" ") && !ctx.children.get(i).getText().equals("|"))
            {
                if(!leftBool)
                {
                    left = ctx.children.get(i).accept(this);
                    leftBool = true; 
                }
                else
                {
                    right = ctx.children.get(i).accept(this);
                    break; 
                }
            }
        }

        return new Pipe(left, right);
    }

    @Override
    public Command visitCall(CallContext ctx) {
        int numOfChildren = ctx.getChildCount();
        int count = 0;
        String fileName = "";
        List<String> arguments = new ArrayList<String>();
        String leafsContent = new String();
        File inRedirectFile = null;
        File outRedirectFile = null;
        Application application = null;
        List<String> subCommand = new ArrayList<String>();

        boolean commandAsArg = false; 
        String appName = ctx.unquoted().getText();
        // System.out.println("appName is " + appName);
        application = ApplicationFactory.make(appName); 

        // DEBUGGING
        // System.out.println("NumofChildren is " + numOfChildren); 

        while (count < numOfChildren){
            // System.out.println("count is " + count);
            leafsContent = ctx.getChild(count).getText();
            // System.out.println("leaf content is " + leafsContent);
        
            while (!leafsContent.equalsIgnoreCase(" ") && !leafsContent.equalsIgnoreCase("\t")){

                if (leafsContent.equalsIgnoreCase(appName) && commandAsArg == false){
                    // System.out.println(appName + " detected as leafContent");
                    commandAsArg = true;
                    break;
                }

                                //Command Substitution
                // It is not command substitution when within single quotes
                if (leafsContent.contains("`")){

                    if(leafsContent.contains("\'"))
                    {
                        int firstOccurence = leafsContent.indexOf("\'");
                        // System.out.println("firstOcc is " + firstOccurence); 
                        int secondOccurence = leafsContent.indexOf("\'", firstOccurence + 1); 
                        // System.out.println("secondOcc is " + secondOccurence); 
                        int firstBack = leafsContent.indexOf("`"); 
                        // System.out.println("firstBack is " + firstBack); 
                        int lastBack = leafsContent.indexOf("`", firstBack + 1); 
                        // System.out.println("lastBack is " + lastBack); 
                        if(firstOccurence < firstBack && secondOccurence > lastBack)
                        {
                            // System.out.println("removing and adding to args");
                            arguments.add(removeQuotes(leafsContent)); 
                            break;
                        }
                        else {
                            String subCmd = StringUtils.substringBetween(leafsContent, "`", "`"); 
                            subCommand.add(subCmd);                            
                            break;
                        }
                    }
                    else {
                        if (!leafsContent.startsWith("`")) arguments.add(leafsContent.substring(0, leafsContent.indexOf("`")));
                        //treat as a command substitution, needs to be evaluated
                        String subCmd = StringUtils.substringBetween(leafsContent, "`", "`"); 
                        subCommand.add(subCmd);
                        break;
                    }
                }

                else if (leafsContent.contains("<")){ //redirect in

                    // trim extracted fileName in case it is  <[ input.txt]
                    fileName = leafsContent.substring(1, leafsContent.length()).trim();
                    inRedirectFile = new File(Jsh.getCurrentDirectory() + File.separator + fileName);
                    if (!inRedirectFile.exists()){
                        throw new RuntimeException("EvalVisitor: redirection file does not exist");
                    }
                    break;
                }

                else if (leafsContent.contains(">")){ //redirect out

                    // trim extracted fileName in case it is  >[ result.txt]
                    fileName = leafsContent.substring(1, leafsContent.length()).trim();
                    outRedirectFile = new File(Jsh.getCurrentDirectory() + File.separator + fileName);
                    break;
                }

                else {
                    String argument = ctx.getChild(count).getText().trim();
                    arguments.add(removeQuotes(argument));
                    break;
                }
            }
            count++; 
    }
        // System.out.println("args are " + arguments); 
        return new Call(application, arguments, inRedirectFile, outRedirectFile, subCommand);
    }

    private String removeQuotes(String string)
    {
        if(string.contains("\'"))
        {
            String replace = "\'"; 
            string = StringUtils.replace(string, replace, "");
            return string;
        }
        else if(string.contains("\""))
        {
            String replace = "\"";
            string = StringUtils.replace(string, replace, "");
            return string; 
        }
        else return string; 
    }
} 
