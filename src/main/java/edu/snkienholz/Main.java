package edu.snkienholz;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
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

        // Load the json
        /* From https://github.com/jsgreenwell/CsvToDatabase/blob/master/src/main/java/edu/fgcu/dataengineering/Main.java
        1. Create instance of GSON
        2. Create a JsonReader object using FileReader
        3. Array of class instances of AuthorParser, assign data from our JsonReader
        4. foreach loop to check data
         */
        Gson gson = new Gson();
        JsonReader jread = new JsonReader(new FileReader("src/Data/authors.json"));
        AuthorParser[] authors = gson.fromJson(jread, AuthorParser[].class);

        bsdb.insertAuthors(authors);
    }
}
