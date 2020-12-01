/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Hau Huong
 */
public class MyConnection {

    public static Connection getConnection() throws NamingException, SQLException {
        Context context = new InitialContext();
        Context tomcateCtx = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) tomcateCtx.lookup("MiniSocialNetworkDS");
        if(ds!=null){
            Connection con = ds.getConnection();
            return con;
        }
        return null;
    }
}
