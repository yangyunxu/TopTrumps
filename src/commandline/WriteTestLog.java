package commandline;

import basic.Game;
import basic.Model;

import java.io.FileWriter;
import java.io.IOException;

public class WriteTestLog {


    protected FileWriter fw;

    public WriteTestLog() {
        try {
            fw = new FileWriter("CLITopTrumps.txt", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeString(String s){
        try {
            fw.write(s);
            fw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void writeInitial() {
        try {
            fw.write("Do you want to see past results or play a game?\n" +
                    "   1: Print Game Statistics\n" +
                    "   2: Play game" +
                    "   3: Quit game" +
                    "\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeStart(int num) {
        try {
            fw.write(String.format("Enter the number for your selection: %d\n", num) +
                    "\n" +
                    "\n" +
                    "Game Start" +
                    "\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void closeWriter() {
        if (fw != null) {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
