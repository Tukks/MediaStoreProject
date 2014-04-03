/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.h2.tools.DeleteDbFiles;

/**
 *
 * @author Justin
 */
public class Bdd {

    Connection conn;

    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        addImage("test");
   
        //getImage(1);
        delImage(6);
        getAllImage();
    }

    public static void addImage(String path) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/mediaproject", "root", "root");
        java.sql.Statement stat = conn.createStatement();
        stat.execute("insert into FILES values(8,'"+ path  +"',1,1,'n','n',1,1)");
        System.out.println("Ajout de l'image");
        stat.close();
        conn.close();
    }

    public static void getAllImage() throws ClassNotFoundException, SQLException {
        DeleteDbFiles.execute("~", "test", true);
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/mediaproject", "root", "root");
        java.sql.Statement stat = conn.createStatement();
        ResultSet rs;
        rs = stat.executeQuery("select * from FILES");
        while (rs.next()) {
            System.out.println("Image numéro :");
            System.out.println(rs.getString("ID_FILE"));
            System.out.println("Path :");
            System.out.println(rs.getString("PATH"));
            System.out.println("Taille :");
            System.out.println(rs.getString("SIZE"));

        }
        stat.close();
        conn.close();
    }

    public static void getImage(int id) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/mediaproject", "root", "root");
        java.sql.Statement stat = conn.createStatement();
        String requeteSQL = "SELECT * FROM FILES WHERE ID_File = '";
        String concat = requeteSQL.concat(id + "'");
        ResultSet rs;
        rs = stat.executeQuery(concat);
        while (rs.next()) {
            System.out.println("Description de l'image :");
            System.out.println(rs.getString("ID_FILE"));
            System.out.println("Path :");
            System.out.println(rs.getString("PATH"));
            System.out.println("Taille :");
            System.out.println(rs.getString("SIZE"));

        }
        stat.close();
        conn.close();
    }

    public static void delImage(int id) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/mediaproject", "root", "root");
        java.sql.Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        String requeteSQL = "SELECT * FROM FILES WHERE ID_File = '";
        String concat = requeteSQL.concat(id + "'");
        ResultSet rs;
        rs = stat.executeQuery(concat);
        rs.absolute(1);
        rs.deleteRow();
        System.out.println("Image supprimer");
        stat.close();
        conn.close();
    }
}
