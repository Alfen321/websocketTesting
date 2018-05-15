package testEnv;

import java.util.Scanner;
import java.io.*;

public class MyExec {
    public static void main(String[] args)
    {
        //init shell
        ProcessBuilder builder = new ProcessBuilder( "cmd.exe" );
        Process p=null;
        try {
            p = builder.start();
        }
        catch (IOException e) {
            System.out.println(e);
        }
        //get stdin of shell
        BufferedWriter p_stdin = 
          new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

        // execute the desired command (here: ls) n times
        int n=5;
        for (int i=0; i<n; i++) {
            try {
            //single execution
            Scanner sc = new Scanner(System.in);
            String command = sc.next();
            p_stdin.write(command);
            p_stdin.newLine();
            p_stdin.flush();
            }
            catch (IOException e) {
            System.out.println(e);
            }
            try {
            //single execution
            p_stdin.write("cd ..");
            p_stdin.newLine();
            p_stdin.flush();
            }
            catch (IOException e) {
            System.out.println(e);
            }
        }

        // finally close the shell by execution exit command
        try {
            p_stdin.write("exit");
            p_stdin.newLine();
            p_stdin.flush();
        }
        catch (IOException e) {
            System.out.println(e);
        }

    // write stdout of shell (=output of all commands)
    Scanner s = new Scanner( p.getInputStream() );
    while (s.hasNext())
    {
        System.out.println( s.next() );
    }
       s.close();
    }
}