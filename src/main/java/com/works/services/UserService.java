package com.works.services;

import com.works.entities.Admin;
import com.works.repositories.AdminRepository;
import com.works.utils.Util;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Service
public class UserService {


    final DriverManagerDataSource source;
    final AdminRepository aRepo;
    final public HttpServletRequest req;
    final HttpServletResponse res;
    public UserService(DriverManagerDataSource source, AdminRepository aRepo, HttpServletRequest req, HttpServletResponse res) {
        this.source = source;
        this.aRepo = aRepo;
        this.req = req;
        this.res = res;
    }



    public boolean userLogin( String email, String password, String remember ) {
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
                    String sessionID = req.getSession().getId();
                    req.getSession().setAttribute("user", adm);
                    if ( remember.equals("on") ) {
                        Cookie cookie = new Cookie("user",  Util.sifrele(""+adm.getPid(), 3) );
                        cookie.setMaxAge( 60 * 60 );
                        res.addCookie(cookie);
                    }
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

    public void logOut() {
        req.getSession().removeAttribute("user");
        cookieDelete();
    }


    public void cookieControl() {
        if ( req.getCookies() != null ) {
            Cookie[] cookies = req.getCookies();
            for ( Cookie cookie : cookies ) {
                if ( cookie.getName().equals("user") ) {
                    try {
                        String stId = Util.sifreCoz( cookie.getValue(), 3 );
                        int id = Integer.parseInt( stId );
                        Optional<Admin> oAdmin = aRepo.findById(id);
                        if ( oAdmin.isPresent() ) {
                            req.getSession().setAttribute("user", oAdmin.get());
                        }
                    }catch (Exception ex) {
                        System.err.println("Cookie Value Error");
                        cookieDelete();
                    }
                }
            }
        }
    }


    public void cookieDelete() {
        Cookie cookie = new Cookie("user", "");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
    }

}
