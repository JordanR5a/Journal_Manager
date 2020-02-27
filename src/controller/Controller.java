package controller;

import model.FileManager;
import ui.UI;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {


    private UI ui = new UI();
    private File folder = new File("C:\\Users\\Reece\\IntellijIDEAProjects\\Real-Deal_Journal\\JournalEntries");
    private FileManager fileManager = new FileManager(folder);
    private LocalDate date = LocalDate.now();

    public static void main(String[] args) {
        Controller controller = new Controller();
        try{
            controller.Journal();
        } catch (IOException ex) {}


    }

    private void Journal() throws IOException {
        int inputInt;
        String inputStr;
        do{
            ui.displayMenu();
            inputInt = ui.askForUserIntInput("");
            switch (inputInt){
                case 1:
                    newEntryDate();
                    inputStr = ui.askForUserStrInput("Please input your entry");
                    fileManager.write(date, inputStr);
                    break;
                case 2:
                    findEntry();
                    break;
                case 3:
                    ArrayList<String> files = fileManager.searchForFiles(LocalDate.of(setYear(), setMonth(), setDay()),
                            LocalDate.of(setYear(), setMonth(), setDay()));
                    for (String fileName : files){
                        ui.display(fileManager.read(fileName));
                    }
                    break;
            }
        } while (inputInt != UI.MenuOptions.values().length);

    }

    private void findEntry(){
        do{
            try{
                ui.display(fileManager.read(LocalDate.of(setYear(), setMonth(), setDay()).toString() + FileManager.DEFAULT_FILE_TYPE));
                return;
            } catch (Exception ex) {}
        } while (true);
    }

    private void newEntryDate(){
        do {
            String inputStr = ui.askForUserStrInput("Would you like to set a specific date?");
            if (inputStr.isEmpty() || inputStr.equalsIgnoreCase("no")) {
                date = LocalDate.now();
                return;
            } else if (inputStr.equalsIgnoreCase("yes")) {
                date = LocalDate.of(setYear(), setMonth(), setDay());
                return;
            }
        } while (true);
    }

    private int setYear(){
        do {
            try {
                int inputInt = ui.askForUserIntInput("Please enter the year");
                LocalDate temp = LocalDate.of(inputInt, LocalDate.MIN.getMonth(), LocalDate.MIN.getDayOfMonth());
                return inputInt;
            } catch (Exception ex) {}
        } while(true);
    }

    private int setMonth(){
        do {
            try {
                int inputInt = ui.askForUserIntInput("Please enter the month");
                LocalDate temp = LocalDate.of(LocalDate.MIN.getYear(), inputInt, LocalDate.MIN.getDayOfMonth());
                return inputInt;
            } catch (Exception ex) {}
        } while(true);
    }

    private int setDay(){
        do {
            try {
                int inputInt = ui.askForUserIntInput("Please enter the day");
                LocalDate temp = LocalDate.of(LocalDate.MIN.getYear(), LocalDate.MIN.getMonth(), inputInt);
                return inputInt;
            } catch (Exception ex) {}
        } while(true);
    }


}
