package ui;

import controller.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UI {
    public enum MenuOptions{
        NEW_ENTRY ("New Entry"),
        VIEW_SPECIFIC_ENTRY ("View Specific Entry"),
        VIEW_RANGE_OF_ENTRIES ("View Range of Entries"),
        EXIT ("Exit");

        public String name;

        MenuOptions(String name){
            this.name = name;
        }

        @Override
        public String toString(){
            return this.name;
        }
    }

    public void display(String str){
        System.out.println(str);
    }

    public void displayMenu(){
        String out = "";
        for (MenuOptions option : MenuOptions.values()){
            out += String.format("%d: %s\n", option.ordinal() + 1, option.name);
        }
        display(out);
    }

    public String askForUserStrInput(String request){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        display(request);
        do {
            try {
                input = br.readLine();
                if(input.isEmpty() || input == null) throw new IllegalStateException("User input unacceptable");
                return input;
            } catch (Exception exception) {
                display("Please try again.");
            }
        } while (true);
    }

    public int askForUserIntInput(String request){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input;
        display(request);
        do {
            try {
                input = Integer.parseInt(br.readLine());
                if(input < 0) throw new IllegalStateException("User input unacceptable");
                return input;
            } catch (Exception exception) {
                display("Please try again.");
            }
        } while (true);



    }
}
