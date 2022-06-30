package com.works.services;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class UserService {


    final DriverManagerDataSource source;
    public UserService(DriverManagerDataSource source) {
        this.source = source;
    }



    public boolean userLogin( String email, String password ) {
        boolean status = false;
        try {
            // select * from admin where email = 'a@a.com' and password = ''or 1 = 1 --'
            //String sql = "select * from admin where email = '"+email+"' and password = '"+password+"'";
            // Statement st = source.getConnection().createStatement();
            String sql = "select * from admin where email = ? and password = ?";
            PreparedStatement pre = source.getConnection().prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            status = rs.next();
        }catch (Exception ex) {
            System.err.println("login Error : " + ex);
        }
        return status;
    }

}
