/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.library;

import Model.adminModel;
import Model.userModel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Lelu
 */
public class Main {
    
    private static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    private static final String URL_DB="jdbc:mysql://db4free.net/mahasiswa";
    private static final String Username="flemel";
    private static final String Password="12345678";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static  InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    private static  BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    private static Scanner scan = new Scanner(System.in);
    private static ArrayList<adminModel> arrayAdmin = new ArrayList<>();
    private static ArrayList<userModel> arrayUser = new ArrayList<>();
    private static String username;
    private static String password;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Bla");
        try{
           Class.forName(JDBC_DRIVER);
           connection = DriverManager.getConnection(URL_DB,Username,Password);
           statement = connection.createStatement();
           System.out.println("Sukses");
           login();
//          while(!connection.isClosed()){
//               //showMenu();
//               System.out.println("Sukses");
//               System.out.flush();
//           }
//           connection.close();
//           statement.close();
       }
       catch(Exception exception){
           exception.printStackTrace();
       }
    }
    
    private static void login(){
        try {
            System.out.println("Masukkan Username Anda : ");
            username = scan.nextLine();
            System.out.println("Masukkan Password Anda : ");
            password = bufferedReader.readLine();
            String sqlAdmin = "select * from admin";
            String sqlUser = "select * from user";
            resultSet = statement.executeQuery(sqlAdmin);
            while(resultSet.next()){
                adminModel adminmodel = new adminModel();
                adminmodel.setId(resultSet.getInt(1));
                adminmodel.setUsername(resultSet.getString(2));
                adminmodel.setPassword(resultSet.getString(3));
                arrayAdmin.add(adminmodel);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        if(authentication(username,password,arrayAdmin) == true){
            System.out.println("Selamat Datang, Anda berhasil login");
        }
        else {
            System.out.println("Anda gagal login");
        }
    }
    
    private static boolean authentication(String username,String password,ArrayList<adminModel> arraytAdmin){
        boolean login = false;
        for (int i = 0; i < arraytAdmin.size(); i++) {
            String usernameAdmin = arraytAdmin.get(i).getUsername();
            String passwordAdmin = arraytAdmin.get(i).getPassword();
            if(username.equals(usernameAdmin) && password.equals(passwordAdmin)){
                login = true;
                break;
            }
        }
        return login;
    }
}
