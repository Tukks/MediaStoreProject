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

    
    public static void main(String args[]) throws ClassNotFoundException, SQLException{
        DeleteDbFiles.execute("~", "test", true);
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/mediaproject","root","root");
        java.sql.Statement stat = conn.createStatement();
        //stat.execute("create table test(id int primary key, name varchar(255))");
        //stat.execute("insert into FILES values(1,'Hello')");
        ResultSet rs;
        rs = stat.executeQuery("select * from FILES");
        while (rs.next()) {
            System.out.println(rs.getString("ID_FILE"));
            
        }
        System.out.println("ok bdd");
        stat.close();
        conn.close();
    }
    
    public void createTable() throws ClassNotFoundException, SQLException{
        DeleteDbFiles.execute("~", "test", true);
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test","root","root");
        java.sql.Statement stat = conn.createStatement();
        stat.execute("create table test(id int primary key, name varchar(255))");
         System.out.println("Table crée");
        stat.close();
        conn.close();
    }
}
