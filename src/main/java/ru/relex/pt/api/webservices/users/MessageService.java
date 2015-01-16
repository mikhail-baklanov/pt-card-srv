package ru.relex.pt.api.webservices.users;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ru.relex.pt.database.DatabaseService;
import ru.relex.pt.dto.PtUserMessages;

@Path("/users/messages")
public class MessageService {

    private DatabaseService service = DatabaseService.getInstance();
    
    @GET
    @Produces("application/json")
    public List<UserMessage> getMessages(@QueryParam("userId")int userId)
    {
	List<UserMessage> msgs = new LinkedList<UserMessage>();
	for(PtUserMessages msg:service.getUserMessages(userId))
	    msgs.add(new UserMessage(msg.getSender(), msg.getText()));
	return msgs;
    }
}
