package ericson.anton.drinkcounter;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class DrinkCounter {

    public String createDrinkListFromFile(File drinkFile, boolean backupShouldBeCreated) {
        String outputFileName;
        try {
            if (backupShouldBeCreated){
                writeBackupFile(drinkFile);
            }
            Map<String,Integer> countedDrinks = countDrinks(drinkFile);
            outputFileName = createOutputFile(countedDrinks);
        } catch (IOException ioException) {
            return "An error occurred: " + ioException.getMessage();
        }
        return backupShouldBeCreated ? outputFileName + "\nBackup was created!" : outputFileName;
    }

    private Map<String, Integer> countDrinks(File drinkFile) throws IOException {
        FileReader fileReader = new FileReader(drinkFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        Map<String,Integer> countedDrinks = new HashMap<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            line = line.trim();
            if (countedDrinks.containsKey(line)) {
                countedDrinks.replace(line, countedDrinks.get(line) + 1);
            } else {
                countedDrinks.put(line, 1);
            }
        }
        fileReader.close();
        bufferedReader.close();

        return countedDrinks;
    }

    private String createOutputFile(Map<String, Integer> countedDrinks) throws IOException {
        String fileName = "Strecklista-" + getTimeDateNow() + ".txt";
        System.out.println("CREATED: " + fileName);
        File outputFile = new File(fileName);

        boolean[] fileWriteSuccess = {true};
        boolean fileCreated = outputFile.createNewFile();

        if (fileCreated) {

            FileWriter fileWriter = new FileWriter(outputFile);
            fileWriter.append("Strecklista genererad: ").append(getTimeDateNow()).append("\n");
            fileWriter.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

            countedDrinks.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEach(person -> {
                        try {
                            fileWriter.append(person.getKey())
                                    .append(": ")
                                    .append(person.getValue().toString())
                                    .append("\n");
                        } catch (IOException e) {
                            fileWriteSuccess[0] = false;
                        }
                    });
            fileWriter.close();
        }
        if (fileCreated && fileWriteSuccess[0]) {
            return fileName;
        } else {
            throw new IOException(!fileCreated ? "File already exists" : "Error writing to file");
        }
    }

    private void writeBackupFile(File drinkFile) throws IOException {
        Path originalFilePath = Paths.get(drinkFile.getAbsolutePath());
        String backupFileName = "DRINKS-BACKUP-CREATED-" + getTimeDateNow() + ".TXT";
        Path backupFilePath = Paths.get("./", backupFileName);
        if (backupFilePath.toFile().exists()) {
            throw new IOException("Backup file already exists");
        } else {
            Files.copy(originalFilePath, backupFilePath, StandardCopyOption.COPY_ATTRIBUTES);
        }
    }

    private String getTimeDateNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm"));
    }
}
