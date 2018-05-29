package Implements;

import Interfaces.DataConnectorInterface;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileImplementation implements DataConnectorInterface {

    File file = null;
    String path = null;
    FileWriter writer = null;
    BufferedWriter bw = null;

    public FileImplementation(String _os, boolean _whitelist) {
        String fileName = "";
        if(_os.toLowerCase().equals("windows")){
            fileName = "windows";
        }else if(_os.toLowerCase().equals("linux")){
            fileName = "linux";
        }else{
            fileName = "other";
        }
        
        if(_whitelist){
            fileName += "whitelist";
        }else{
            fileName += "blacklist";
        }
        
        fileName += ".txt";
        
        file = new File(fileName);
        createFile();
        path = file.getAbsolutePath();
    }

    private boolean createFile() {
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println(path);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean writeFile(String input) {
        try {
            writer = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(writer);
            bw.write("'" + input + "'\n");
        } finally {

            try {

                if (bw != null) {
                    bw.close();
                }

                if (writer != null) {
                    writer.close();
                }
                return true;

            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }

    private List readFile() {
        String word = "";
        boolean wordFinished = false;
        ArrayList<String> list = new ArrayList<String>();
        try {
            FileReader reader = new FileReader(file);
            char[] a = new char[(int) file.length()];
            reader.read(a);
            for (char c : a) {
                
                if (c != '\n') { //Making sure its not adding new lines
                    word += c;
                    wordFinished = true;
                } else if (wordFinished && c == '\n') {
                    wordFinished = false;
                    if (!word.isEmpty()) {
                        list.add(word.substring(1, (word.length()-1)));
                        word = "";
                    }
                }
            }
            return list;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List retrieveIllegalInputs() {
        ArrayList<String> list = (ArrayList<String>) readFile();
        return list;
    }

    @Override
    public boolean saveIllegalInput(String input) {
        return writeFile(input);
    }

}