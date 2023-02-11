package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Service.MessageService;
import Util.ConnectionUtil;


public class MessageDAO {

    public Message insertMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();

        try{ 
            String checksql = "SELECT * FROM account WHERE account_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checksql);
            checkStatement.setInt(1, message.getPosted_by());
            ResultSet checkResult = checkStatement.executeQuery();
            if(!checkResult.next()){
                return null;
            }

            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                int generated_message_id = (int) resultSet.getLong(1);
                System.out.println(generated_message_id);
                return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
       
        return null;
    }

    public List<MessageService> getAllMessages() {
        Connection connection = ConnectionUtil.getConnection();
        List <MessageService> allMesagges = new ArrayList<>();

        try{
            String sql = "SELECT * FROM message";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet allResults = preparedStatement.executeQuery();
            
            while(allResults.next()){
                MessageService messageService = new MessageService(allResults.getInt("message_id"), allResults.getInt("posted_by"), allResults.getString("message_text"));
            }
        }catch{

        }
        
        
        return null;
    }
    
}
