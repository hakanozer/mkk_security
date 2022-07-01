package com.works.services;

import com.works.entities.Admin;
import com.works.repositories.AdminRepository;
import com.works.utils.Util;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Service
public class UserService {


    final DriverManagerDataSource source;
    final AdminRepository aRepo;
    public UserService(DriverManagerDataSource source, AdminRepository aRepo) {
        this.source = source;
        this.aRepo = aRepo;
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
            pre.setString(2,  Util.encrypt(password, 3));
            ResultSet rs = pre.executeQuery();
            status = rs.next();
            if ( status ) {
                int pid = rs.getInt("pid");
                Optional<Admin> oAdmin = aRepo.findById(pid);
                if (oAdmin.isPresent() ) {
                    Admin adm = oAdmin.get();
                    System.out.println( adm );
                }
            }
        }catch (Exception ex) {
            System.err.println("login Error : " + ex);
        }
        return status;
    }


    public Admin register( Admin admin ) {
        admin.setPassword( Util.encrypt( admin.getPassword(), 3 ) );
        aRepo.save(admin);
        admin.setPassword("Secur");
        return admin;
    }

}
