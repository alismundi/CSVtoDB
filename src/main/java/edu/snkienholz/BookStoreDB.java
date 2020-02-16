package edu.snkienholz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookStoreDB {

  Connection conn = null;

  // Connection method referenced from SQLite Tutorial https://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/
  public void connect() {

    try {
      // BookStore.db URL
      String url = "jdbc:sqlite:C:/Users/Sabrina/Developer/CSVtoDB/src/data/BookStore.db";
      conn = DriverManager.getConnection(url);

      System.out.println("Connection successful!");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void insertBooks(ArrayList<String[]> rows) {

    // Looping over each row in the bookstore_report2.csv file
    for (int i = 1; i < rows.size(); i++) {
      // Cast to String[] array like in printCsv()
      String[] row = rows.get(i);

      String isbn = row[0];
      String title = row[1];
      String authorName = row[2];
      String publisherName = row[3];

      String sql = "INSERT INTO book(isbn, publisher_name, author_name, book_title) VALUES(?, ?, ?, ?)";

      try {

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, isbn);
        stmt.setString(2, title);
        stmt.setString(3, authorName);
        stmt.setString(4, publisherName);

        stmt.executeUpdate();

      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public void insertAuthors(AuthorParser[] authors) {

    String sql = "INSERT INTO author(author_name, author_email, author_url) VALUES(?, ?, ?)";

    for (AuthorParser author: authors) {

      try {

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, author.getName());
        stmt.setString(2, author.getEmail());
        stmt.setString(3, author.getUrl());

        stmt.executeUpdate();

      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}