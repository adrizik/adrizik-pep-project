package Service;

import Model.Message;

import java.util.List;

import DAO.MessageDAO;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public Message postMessage(Message message) {
        
        if(message.message_text.length() > 0 && message.message_text.length() < 255){
            return messageDAO.insertMessage(message);
        }
        return null;
    }

    public List<MessageService> getAllMessages() {
        return messageDAO.getAllMessages();
    }
    
}
