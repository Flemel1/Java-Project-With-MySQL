/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lelu
 */

public class koneksiDB {
    public static Connection conn;
    public static String  driver = "com.mysql.jdbc.Driver";
    public static Connection configDB() throws SQLException {
        try{
            Class.forName(driver);
            String url = "jdbc:mysql://db4free.net/mahasiswa";
            String user = "flemel";
            String password = "12345678";
            conn = DriverManager.getConnection(url, user, password);
        }
        catch(Exception e){
            System.out.println("Gagal terkoneksi ke database");
        }
        return conn;
    }
}
