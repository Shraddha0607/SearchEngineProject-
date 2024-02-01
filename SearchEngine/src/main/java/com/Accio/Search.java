package com.Accio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Search")
public class Search extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String keyword = request.getParameter("keyword");
        Connection connection = DatabaseConnection.getConnection();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("insert into history values(?,?)");
            preparedStatement.setString(1,keyword);
            preparedStatement.setString(2,"https://localhost:8080/SearchEngine/Search?keyword="+keyword);
            preparedStatement.execute();

            ResultSet resultSet = connection.createStatement().executeQuery("select pageTitle,pageLink,(length(lower(pageText))-length(replace(lower(pageText),'"+keyword.toLowerCase()+"','')))/length('"+keyword.toLowerCase()+"') as countoccurence from pages order by countoccurence desc limit 30;");
            ArrayList<SearchResult> results = new ArrayList<SearchResult>();
            while (resultSet.next()) {
                SearchResult searchResult = new SearchResult();
                searchResult.setTitle(resultSet.getString("pageTitle"));
                searchResult.setLink(resultSet.getString("pageLink"));
                results.add(searchResult);
            }
            request.setAttribute("results",results);
            request.getRequestDispatcher("search.jsp").forward(request,response);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
        }
        catch (SQLException | ServletException sqlException){
            sqlException.printStackTrace();
        }
    }
}
