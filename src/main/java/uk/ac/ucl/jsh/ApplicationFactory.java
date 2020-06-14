package uk.ac.ucl.jsh;

public class ApplicationFactory {

    public static Application make(String applicationType){
        if (applicationType == null){
            throw new RuntimeException("Application Factory: application cannot be null");
        }
        if (applicationType.equalsIgnoreCase("cat")){
            return new Cat();
        }
        else if (applicationType.equalsIgnoreCase("cd")){
            return new Cd();
        }
        else if (applicationType.equalsIgnoreCase("echo")){
            return new Echo();
        }
        else if (applicationType.equalsIgnoreCase("grep")){
            return new Grep();
        }
        else if (applicationType.equalsIgnoreCase("head")){
            return new Head();
        }
        else if (applicationType.equalsIgnoreCase("ls")){
            return new Ls();
        }
        else if (applicationType.equalsIgnoreCase("pwd")){
            return new Pwd();
        }
        else if (applicationType.equalsIgnoreCase("tail")){
            return new Tail();
        }
        else if (applicationType.equalsIgnoreCase("find"))
        {
            return new Find();
        }
        else if (applicationType.equalsIgnoreCase("wc"))
        {
            return new Wc(); 
        }
        else if (applicationType.equalsIgnoreCase("sed"))
        {
            return new Sed();
        }
        else if (applicationType.equalsIgnoreCase("mkdir"))
        {
            return new MkDir();
        }
        else if (applicationType.equalsIgnoreCase("_mkdir"))
        {
            return new UnsafeDecorator(new MkDir());
        }
        if (applicationType.equalsIgnoreCase("_cat")){
            return new UnsafeDecorator(new Cat());
        }
        else if (applicationType.equalsIgnoreCase("_cd")){
            return new UnsafeDecorator(new Cd());
        }
        else if (applicationType.equalsIgnoreCase("_echo")){
            return new UnsafeDecorator(new Echo());
        }
        else if (applicationType.equalsIgnoreCase("_grep")){
            return new UnsafeDecorator(new Grep());
        }
        else if (applicationType.equalsIgnoreCase("_head")){
            return new UnsafeDecorator(new Head());
        }
        else if (applicationType.equalsIgnoreCase("_ls")){
            return new UnsafeDecorator(new Ls());
        }
        else if (applicationType.equalsIgnoreCase("_pwd")){
            return new UnsafeDecorator(new Pwd());
        }
        else if (applicationType.equalsIgnoreCase("_tail")){
            return new UnsafeDecorator(new Tail());
        }
        else if (applicationType.equalsIgnoreCase("_find"))
        {
            return new UnsafeDecorator(new Find());
        }
        else if (applicationType.equalsIgnoreCase("_wc"))
        {
            return new UnsafeDecorator(new Wc()); 
        }
        else if (applicationType.equalsIgnoreCase("_sed"))
        {
            return new UnsafeDecorator(new Sed());
        }
        else { throw new RuntimeException("Application Factory: application cannot be null");}    }
}