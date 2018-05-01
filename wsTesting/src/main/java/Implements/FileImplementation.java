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

    File file = new File("test.txt");
    String path = file.getAbsolutePath();
    FileWriter writer = null;
    BufferedWriter bw = null;

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
        createFile();
        writeFile("test");
        ArrayList<String> list = (ArrayList<String>) readFile();
        System.out.println(list.size());
        for (String string : list) {
            System.out.println(string);
        }
        return list;
    }

    @Override
    public boolean saveIllegalInput(String input) {
        createFile();
        return writeFile(input);
    }

}