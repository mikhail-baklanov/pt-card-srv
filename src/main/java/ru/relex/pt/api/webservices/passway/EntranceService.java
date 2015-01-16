package ru.relex.pt.api.webservices.passway;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import ru.relex.pt.database.DatabaseService;
import ru.relex.pt.database.IllegalOperationSequenceException;
import ru.relex.pt.database.InvalidAbsentTypeException;
import ru.relex.pt.database.NotAuthorizedException;
import ru.relex.pt.database.NotFoundEntranceException;
import ru.relex.pt.database.UnknownStatusException;
import ru.relex.pt.dto.PwEntrance;

@Path("/passway/entrance")
public class EntranceService {

    private DatabaseService service = DatabaseService.getInstance();

    @GET()
    @Produces("application/json")
    public PassesResponse getPasses(@QueryParam("since") @DefaultValue("0") long since,
	    @QueryParam("status") @DefaultValue("active") String status) {
	List<Pass> passes = new ArrayList<Pass>();

	PassesResponse passesResponse = new PassesResponse(passes, new Date().getTime());
	for (PwEntrance entrance : service.getEntrances(since, status)) {
	    Pass newPass = new Pass();
	    newPass.setId(entrance.getId());
	    newPass.setCardId(entrance.getCardId());
	    newPass.setDirection(entrance.getDirection());
	    newPass.setUserId(entrance.getUser().getUserId().longValue());
	    newPass.setFirstName(entrance.getUser().getFirstName());
	    newPass.setLastName(entrance.getUser().getLastName());
	    newPass.setMiddleName(entrance.getUser().getMiddleName());
	    newPass.setPassTime(entrance.getPassTime().getTime());
	    newPass.setStatus(service.getUserStatus(entrance.getUser()));
	    passes.add(newPass);
	}
	return passesResponse;
    }

    @POST()
    @Consumes("application/json")
    @Produces("application/json")
    public Pass registerPass(Pass pass) throws NotAuthorizedException {
	PwEntrance entrance = service.createEntrance(pass.getCardId(), pass.getDirection());
	Pass newPass = new Pass();
	newPass.setId(entrance.getId());
	newPass.setCardId(pass.getCardId());
	newPass.setDirection(pass.getDirection());
	newPass.setUserId(entrance.getUser().getUserId().longValue());
	newPass.setFirstName(entrance.getUser().getFirstName());
	newPass.setLastName(entrance.getUser().getLastName());
	newPass.setMiddleName(entrance.getUser().getMiddleName());
	newPass.setPassTime(entrance.getPassTime().getTime());
	newPass.setStatus(service.getUserStatus(entrance.getUser()));
	return newPass;
    }

    @PUT
    @Consumes("application/json")
    @Produces("text/plain")
    public String processPass(PassProcessInfo passInfo) throws NotFoundEntranceException, UnknownStatusException,
	    IllegalOperationSequenceException, InvalidAbsentTypeException {
	service.updateEntrance(passInfo.getId(), passInfo.getStatus(), passInfo.getAbsentTimeMin(),
		passInfo.getAbsentType());
	return "OK";
    }
}
