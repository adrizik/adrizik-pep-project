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

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int message_id) {
        return messageDAO.getMessageById(message_id);
    }

    public Message deleteMessageById(int messageId) {
        return messageDAO.deleteMessageById(messageId);
    }

    public Message updateMessage(int messageId, Message message) {
        Message getOldMessage = messageDAO.getMessageById(messageId);
        
        if(getOldMessage != null && message.message_text.length() > 0 && message.message_text.length() < 255){

        return messageDAO.updateMessage(messageId, message);
        }
        return null;
    }
    
}
