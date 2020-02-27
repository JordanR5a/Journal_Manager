package model;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileManager {
    public static final String DEFAULT_FILE_TYPE = ".txt";
    private File folder;

    public FileManager(File folder) {
        this.folder = folder;
        folder.mkdir();
    }

    public void write(LocalDate date, String string) throws FileNotFoundException {
        if (date.isAfter(LocalDate.now())) throw new IllegalStateException();
        PrintStream out = new PrintStream(String.format("%s\\%s.txt", folder.getAbsolutePath(), date.toString()));
        try{
            out.println(string);
        }finally {
            out.close();
        }
    }

    public String read(String fileName) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(folder.getAbsolutePath() + "\\" + fileName)));
        String content = "";
        try{
            while(in.ready()){
                content += in.readLine() + "\n";
            }
        } finally {
            in.close();
        }
        return content;
    }

    public ArrayList<String> searchForFiles(LocalDate min, LocalDate max){
        ArrayList<String> fileNames = new ArrayList<>();
        for (File file : folder.listFiles()){
            if (parseDate(file.getName()).isAfter(min) && parseDate(file.getName()).isBefore(max)) fileNames.add(file.getName());
        }
        return fileNames;
    }

    private LocalDate parseDate(String str){
        String[] date = str.substring(0, str.indexOf(".")).split("-");
        return LocalDate.of(Integer.parseInt(date[0].trim()), Integer.parseInt(date[1].trim()),
                Integer.parseInt(date[2].trim()));
    }
}
