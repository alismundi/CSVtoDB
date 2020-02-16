package edu.snkienholz;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, CsvValidationException {

        CsvParser csvp = new CsvParser("src/data/bookstore_report2.csv");
        csvp.printCsv();

        // Getting the rows
        ArrayList<String[]> rows = csvp.getFileRows();

        // DB variable and connecting it to DB
        BookStoreDB bsdb = new BookStoreDB();
        bsdb.connect();
        bsdb.insertBooks(rows);
    }
}
