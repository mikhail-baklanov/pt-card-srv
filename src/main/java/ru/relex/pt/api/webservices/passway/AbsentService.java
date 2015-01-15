package ru.relex.pt.api.webservices.passway;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ru.relex.pt.database.DatabaseService;
import ru.relex.pt.dto.PwAbsentsDTO;

@Path("/passway/absent")
public class AbsentService {

	private DatabaseService service = DatabaseService.getInstance();

	@GET()
	@Produces("application/json")
	public List<AbsentUserInfo> getAbsentUsers() {
		List<AbsentUserInfo> absentUserInfos = new ArrayList<AbsentUserInfo>();

		for (PwAbsentsDTO absent : service.getActiveAbsents()) {

			absentUserInfos
					.add(new AbsentUserInfo(absent.getPwWorkTimes()
							.getPtUsers().getUserId().longValue(), absent
							.getPwWorkTimes().getPtUsers().getFirstName(),
							absent.getPwWorkTimes().getPtUsers().getLastName(),
							absent.getPwWorkTimes().getPtUsers()
									.getMiddleName(), absent.getAway()
									.getTime(),
							(absent.getAvgback().getTime() - absent.getAway()
									.getTime()) / 60 / 1000, absent
									.getPwAbsentType().getName()));
		}
		return absentUserInfos;
	}
}
