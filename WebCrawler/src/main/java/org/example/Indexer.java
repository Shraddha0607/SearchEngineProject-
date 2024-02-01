package org.example;

import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Indexer {
    Connection connection = null;
    Indexer(Document document,String url){
        String Title = document.title();
        String link = url;
        String text = document.text();
        connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into pages values(?,?,?)");
            preparedStatement.setString(1, Title);
            preparedStatement.setString(2, link);
            preparedStatement.setString(3, text);
            preparedStatement.execute();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
