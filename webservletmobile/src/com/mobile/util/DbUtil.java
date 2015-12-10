package com.mobile.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mobile.exception.MobileException;

public class DbUtil {
	private static Connection connection = null;

    public static Connection getConnection() throws MobileException {
            try {
            	Properties prop = new Properties();
                InputStream inputStream = DbUtil.class.getClassLoader().getResourceAsStream("/db.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                throw new MobileException(e);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return connection;
        }
    
    public static DataSource getDataSource() throws MobileException {
		InitialContext cxt = null;
		DataSource  lookup = null;
		try {
			cxt = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
			 throw new MobileException(e.getCause());
		}
		if ( cxt == null ) {
		 System.out.println( "Uh oh -- no context!");
		}
		
		try {
			 lookup = (DataSource) cxt.lookup( "java:/comp/env/jdbc/cla88inf" );
		} catch (NamingException e) {
			e.printStackTrace();
			throw new MobileException(e);
		}if ( lookup == null ) {
			   throw new MobileException("Data source not found!");
		}
		
		return lookup;
	}
}
