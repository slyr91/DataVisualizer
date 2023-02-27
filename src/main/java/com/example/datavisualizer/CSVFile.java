package com.example.datavisualizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CSVFile {

    private File file;
    public CSVFile(File file) {
        this.file = file;
    }

    public int rowCount = 0;
    public int columnCount = 0;
    private Map<Integer, String> rows = new HashMap<>();
    private Map<Integer, String> columns = new HashMap<>();

    public void ingestFile() {
        try {
            Scanner scanner = new Scanner(file);

            String headers = scanner.nextLine();

            String[] headerArray = headers.split(",");

            for(int i = 1; i < headerArray.length; i++) {
                columns.put(i - 1, headerArray[i]);
                columnCount++;
            }

            while(scanner.hasNext()) {
                rows.put(rowCount, scanner.nextLine());
                rowCount++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getRow(int rowNumber) {
        return rows.get(rowNumber);
    }

    public String getColumn(int columnNumber) {
        return columns.get(columnNumber);
    }

}
