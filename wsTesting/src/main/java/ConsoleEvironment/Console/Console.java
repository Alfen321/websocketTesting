package ConsoleEvironment.Console;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Console implements IConsole {

    private ProcessBuilder builder = null;
    private Process p = null;

    private BufferedWriter p_stdin = null;
    private ConsoleReader conReader = null;

    private Thread readerThread = null;
    
    private IOutput output = null;
    

    @Override
    public boolean setup(IOutput output) {
        this.output = output;
        try {
            builder = new ProcessBuilder("cmd.exe");
            p = builder.start();

            p_stdin = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            conReader = new ConsoleReader(p.getInputStream(), output);
            readerThread = new Thread(conReader);

            readerThread.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isAlive();
    }
    
    @Override
    public void runCommand(String command) {
        try {
            p_stdin.write(command);
            p_stdin.newLine();
            p_stdin.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public boolean isAlive() {
        return p.isAlive();
    }

    @Override
    public void terminate() {
        conReader.terminate();
        p.destroyForcibly();
    }
}
