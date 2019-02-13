package es.iti.spring.core.service;

import es.iti.spring.model.entities.Gochi;
import es.iti.spring.persistence.dao.GochiDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GochiService {
    @Autowired
    private GochiDAO gochiDAO;
    
    public List<Gochi> getAll() {
        return this.gochiDAO.find();
    }
}
