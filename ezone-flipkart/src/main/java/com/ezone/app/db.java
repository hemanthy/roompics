package com.ezone.app;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mongodb.DB;
import com.mongodb.MongoClient;


public abstract class db {
        
        public DB db = null;
        
        public Connection con = null;
        
        public boolean initializeMongodb(){
                MongoClient mongo = new MongoClient("localhost", 27017);
                db = mongo.getDB("testdb");
                return db!=null;
        }
        
        public boolean initializeMysqldb() throws ClassNotFoundException, SQLException{
                
                Class.forName("com.mysql.jdbc.Driver");  
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ezone","root","hemanth");
                return con!=null;  
        
        }

}
 