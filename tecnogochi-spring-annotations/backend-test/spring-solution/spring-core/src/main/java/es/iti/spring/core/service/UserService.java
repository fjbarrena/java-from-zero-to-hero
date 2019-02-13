package es.iti.spring.core.service;

import es.iti.spring.persistence.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    
    
    
    public boolean login(String email, String unencryptedPassword) {
        String encryptedPassword = encryptPassword(unencryptedPassword);
        
//        User loggedInUser = this.userDAO.getUser(email, encryptedPassword);
//        
//        if(loggedInUser != null) {
//            // Las credenciales son correctas
//            
//            return true;
//        }
//        else {
//            // Las credenciales no son válidas
//            return false;
//        }

        return true;
    }
    
    private String encryptPassword(String unencryptedPassword) {
        return "";
    }
}
