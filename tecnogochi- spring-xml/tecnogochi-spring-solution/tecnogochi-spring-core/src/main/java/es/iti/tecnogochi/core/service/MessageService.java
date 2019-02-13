package es.iti.tecnogochi.core.service;

import es.iti.formacion.tecnogochi.model.Message;
import es.iti.formacion.tecnogochi.model.MyGochis;
import es.iti.formacion.tecnogochi.persistence.dao.MessageDAO;
import java.util.List;



public class MessageService {

    private MessageDAO messageDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
    }

    public List<Message> getUnreadedMessages(MyGochis myGochi) {
        return messageDAO.findUnreadedMessages();
    }

    public void sendMessage(Message msg) {
        messageDAO.create(msg);
        //TODO: Enviar mensaje
    }
    
    public void saveMessage(Message msg){
        messageDAO.create(msg);
    }

    public MessageDAO getMessageDAO() {
        return messageDAO;
    }

    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

}
