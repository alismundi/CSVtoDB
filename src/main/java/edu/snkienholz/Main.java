package edu.snkienholz;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, CsvValidationException {

        System.out.println("Hello I work!");

        CsvParser csvp = new CsvParser("src/data/SEOExample-1.csv");
        csvp.printCsv();
    }

}
