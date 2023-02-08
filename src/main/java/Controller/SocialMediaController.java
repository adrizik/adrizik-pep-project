package Controller;
import Model.Account;
import Model.Message;

import Service.AccountService;
import Service.MessageService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postNewAccountHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getMessageHandler);
        app.get("/messages/{message_id}", this::getMessageIdHandler);
        app.patch("/messages/{message_id}", this::patchMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getAccountMessageHandler);
        
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postNewAccountHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account NewAccount = mapper.readValue(ctx.body(), Account.class);
        Account registeredAccount = accountService.newAccount(NewAccount);
        if(registeredAccount != null){
            ctx.json(mapper.writeValueAsString(registeredAccount));
        }else{
            ctx.status(400);
        }
        
    }

    private void postLoginHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account LoginAccount = mapper.readValue(ctx.body(), Account.class);
        Account LoggedInAccount = accountService.LoginAccount(LoginAccount);
        if(LoggedInAccount != null){
            ctx.json(mapper.writeValueAsString(LoggedInAccount));
        }else{
            ctx.status(401);
        }
        
    }

    private void postMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message insertMessage = messageService.message(message);
        if(insertMessage != null){
            ctx.json(mapper.writeValueAsString(insertMessage));
        }else{
            ctx.status(400);
        }
        
    }

}