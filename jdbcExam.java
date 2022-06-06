import jdk.nashorn.internal.parser.DateParser;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class jdbcExam {
    public static void main(String[] args) throws SQLException {
        String sqlDelete = "delete from movies where movieID = ?";
//        String sqlUpdate = "UPDATE movies SET ? = ? WHERE ? = ?";
        String sqlInsert = "INSERT INTO movies VALUES(?, ?, ?, ?, ?)";
        String sqlPrint = "select * from movies";
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/movie",
                        "root", ""
                );

                PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete);
//                PreparedStatement preparedStatement0 = connection.prepareStatement(sqlUpdate);
                PreparedStatement preparedStatement1 = connection.prepareStatement( sqlInsert);
                PreparedStatement preparedStatement2 = connection.prepareStatement(sqlPrint);
                Statement statement = connection.createStatement();
        ) {
//          Xoa du lieu movie
            Scanner scanner = new Scanner(System.in);  // Create a Scanner scanner0
            System.out.println("Enter movieID colum to delete: ");
            String idDelete = scanner.next();
            System.out.println("movieID to delete is: " + idDelete);
            System.out.println();
//
            preparedStatement.setString(1,idDelete);
            int rowDelete = preparedStatement.executeUpdate();
            System.out.println(rowDelete+" Colum delete done");
            ResultSet resultset = preparedStatement2.executeQuery();
            while (resultset.next()){
                String movieID = resultset.getString("movieID");
                String movieName = resultset.getString("movieName");
                Date premiereDate = resultset.getDate("premiereDate");
                String director = resultset.getString("director");
                String capacity = resultset.getString("capacity");
                System.out.println("Movie:"+movieID + ", " + movieName + ", " + premiereDate + ", " + director + ", " + capacity);
            }
            System.out.println();

//            Cap nhap du lieu

//          Them du lieu movie
            Scanner scanner0 = new Scanner(System.in);  // Create a Scanner scanner0
            System.out.println("Enter movieID to Insert: ");
            String id = scanner0.next();
            System.out.println("movieID to Insert is: " + id);
            System.out.println();
//
            Scanner scanner1 = new Scanner(System.in);  // Create a Scanner scanner1
            System.out.println("Enter movieName to Insert: ");
            String name = scanner1.next();
            System.out.println("movieName to Insert is: " + name);
            System.out.println();
//
            Scanner scanner2 = new Scanner(System.in);  // Create a Scanner scanner2
            System.out.println("Enter movieDate to Insert: ");
            String stringDate = scanner2.next();
            System.out.println("movieDate to Insert is: " + stringDate);
            System.out.println();
//
            Scanner scanner3 = new Scanner(System.in);  // Create a Scanner scanne3
            System.out.println("Enter movieDirector to Insert: ");
            String edit = scanner3.next();
            System.out.println("movieDirector to Insert is: " + edit);
            System.out.println();
////
            Scanner scanner4 = new Scanner(System.in);  // Create a Scanner scanne4
            System.out.println("Enter movieCapacity to Insert: ");
            int time = scanner4.nextInt();
            System.out.println("movieCapacity to Insert is: " + time);
            System.out.println();
//

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(stringDate);
            java.sql.Date sql = new java.sql.Date(date.getTime());
            java.sql.Date datesql = new java.sql.Date(date.getTime());
//
            preparedStatement1.setString(1,id);
            preparedStatement1.setString(2,name);
            preparedStatement1.setDate(3, datesql);
            preparedStatement1.setString(4,edit);
            preparedStatement1.setInt(5,time);
            int rowInsert = preparedStatement1.executeUpdate();
            System.out.println(rowInsert+" Colum delete done");
            ResultSet resultSet = preparedStatement2.executeQuery();
            while (resultSet.next()){
                String movieID = resultSet.getString("movieID");
                String movieName = resultSet.getString("movieName");
                Date premiereDate = resultSet.getDate("premiereDate");
                String director = resultSet.getString("director");
                String capacity = resultSet.getString("capacity");
                System.out.println("Movie:"+movieID + ", " + movieName + ", " + premiereDate + ", " + director + ", " + capacity);
            }
            System.out.println();

//          Tim kiem movie theo yeu cau
            Scanner scanner5 = new Scanner(System.in);  // Create a Scanner scanner5
            System.out.println("Enter information movie to search: ");
            String findMovie = scanner5.next();
            System.out.println("Information movie to search is: " + findMovie);
            System.out.println();
////
            String findbyID = "SELECT * FROM movies where movieID like '%"+findMovie+"%'";
            String findbyName = "SELECT * FROM movies where movieName like '%"+findMovie+"%'";
            String findbyDate = "SELECT * FROM movies where premiereDate like '%"+findMovie+"%'";
            String findbyDirector = "SELECT * FROM movies where director like '%"+findMovie+"%'";
            String findbyCapacity = "SELECT * FROM movies where capacity like '%"+findMovie+"%'";
            String query[] ={findbyID,
                    findbyName,findbyDate
                    ,findbyDirector,findbyCapacity};
            for(String q : query){
////
                ResultSet resultSet1 = statement.executeQuery(q);
                System.out.println("Corresponding searched movie at column: "+q+":");
////
                while (resultSet1.next()) {
                    String movieID = resultSet1.getString("movieID");
                    String movieName = resultSet1.getString("movieName");
                    Date premiereDate = resultSet1.getDate("premiereDate");
                    String director = resultSet1.getString("director");
                    int capacity = resultSet1.getInt("capacity");
                    System.out.println("Movie to search:"
                            +movieID + ", " + movieName + ", " + premiereDate
                            + ", " + director + ", " + capacity);
                }
                System.out.println();
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }
}



