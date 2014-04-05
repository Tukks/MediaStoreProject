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

public class Bdd {

    Connection conn;

    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        //génère un ID aléatoire
        int nb = (int) (Math.random() * 100);
        addImage(nb, "c:\\", 1, 1, "testmd5", "testhash", 1, 1);
        //Pour avoir l'image avec l'id 8
        //getImage(8);
        //pour effacer image avec id 6
        //delImage(50);
        getAllImage();
    }

    public static void addImage(int nb, String path, int size, int mtime, String md5, String hash, int lat, int lon) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/mediaproject", "root", "root");
        java.sql.Statement stat = conn.createStatement();
        stat.execute("insert into FILES values(" + nb + ",'" + path + "'," + size + "," + mtime + ",'" + md5 + "','" + hash + "'," + lat + "," + lon + ")");
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
            System.out.println("Crée le :");
            System.out.println(rs.getString("MTIME"));
            System.out.println("MD5 :");
            System.out.println(rs.getString("MD5"));
            System.out.println("HASH :");
            System.out.println(rs.getString("HASH"));
            System.out.println("LAT :");
            System.out.println(rs.getString("LAT"));
            System.out.println("LON :");
            System.out.println(rs.getString("LON"));

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
            System.out.println("Image numéro :");
            System.out.println(rs.getString("ID_FILE"));
            System.out.println("Path :");
            System.out.println(rs.getString("PATH"));
            System.out.println("Taille :");
            System.out.println(rs.getString("SIZE"));
            System.out.println("Crée le :");
            System.out.println(rs.getString("MTIME"));
            System.out.println("MD5 :");
            System.out.println(rs.getString("MD5"));
            System.out.println("HASH :");
            System.out.println(rs.getString("HASH"));
            System.out.println("LAT :");
            System.out.println(rs.getString("LAT"));
            System.out.println("LON :");
            System.out.println(rs.getString("LON"));
        }
        stat.close();
        conn.close();
    }

    public static void delImage(int id) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/mediaproject", "root", "root");
        java.sql.Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
