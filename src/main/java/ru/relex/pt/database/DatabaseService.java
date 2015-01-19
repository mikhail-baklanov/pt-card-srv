package ru.relex.pt.database;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ru.relex.pt.api.webservices.exceptions.IllegalOperationSequenceException;
import ru.relex.pt.api.webservices.exceptions.InvalidAbsentTypeException;
import ru.relex.pt.api.webservices.exceptions.NotAuthorizedException;
import ru.relex.pt.api.webservices.exceptions.NotFoundEntranceException;
import ru.relex.pt.api.webservices.exceptions.UnknownStatusException;
import ru.relex.pt.dto.PtApiKeys;
import ru.relex.pt.dto.PtUserMessages;
import ru.relex.pt.dto.PtUsersDTO;
import ru.relex.pt.dto.PwAbsentTypeDTO;
import ru.relex.pt.dto.PwAbsentsDTO;
import ru.relex.pt.dto.PwEntrance;
import ru.relex.pt.dto.PwWorkTimesDTO;

public class DatabaseService {
    private Database database;
    private static DatabaseService instance;
    private Long pkSequence = 0L;

    public static DatabaseService getInstance() {
	if (instance == null)
	    instance = new DatabaseService();
	return instance;
    }

    private DatabaseService() {
	database = Database.getInstance();
    }

    public PwEntrance createEntrance(String cardId, String direction) throws NotAuthorizedException {
	PtUsersDTO user = findUserByKey(cardId);
	if (user == null)
	    throw new NotAuthorizedException();
	PwEntrance entrance = new PwEntrance(nextPK(), cardId, new Date(), user, direction);
	database.getEntrances().add(entrance);
	return entrance;
    }

    public List<PwEntrance> getEntrances(long since, String status) {
	List<PwEntrance> result = new LinkedList<PwEntrance>();
	for (PwEntrance e : database.getEntrances())
	    if (e.getPassTime().getTime() > since && e.getStatus().equals(status))
		result.add(e);
	Collections.sort(result, new Comparator<PwEntrance>() {
	    public int compare(PwEntrance o1, PwEntrance o2) {
		return (int) (o2.getPassTime().getTime() - o1.getPassTime().getTime());
	    };
	});
	return result;
    }

    synchronized private Long nextPK() {
	return ++pkSequence;
    }

    private PtUsersDTO findUserByKey(String cardId) {
	for (PtApiKeys key : database.getKeys())
	    if (key.getKey().equals(cardId))
		return key.getUser();
	return null;
    }

    public void updateEntrance(long id, String status, int absentTimeMin, String absentType)
	    throws NotFoundEntranceException, UnknownStatusException, IllegalOperationSequenceException,
	    InvalidAbsentTypeException {
	PwEntrance entrance = findEntranceById(id);
	if (entrance == null)
	    throw new NotFoundEntranceException();
	if ("work".equals(status)) {
	    PwWorkTimesDTO worktime = findWorktimeByUser(entrance.getUser().getUserId());
	    if (worktime != null) {
		PwAbsentsDTO absent = findLastAbsentByUser(entrance.getUser().getUserId());
		if (absent == null || absent.getBack() != null)
		    throw new IllegalOperationSequenceException();
		absent.setBack(entrance.getPassTime());
	    } else {
		worktime = new PwWorkTimesDTO(BigDecimal.valueOf(nextPK()), entrance.getUser(), null, new Date());
		worktime.setWorkBegin(entrance.getPassTime());
		database.getWorktimes().add(worktime);
	    }
	    entrance.setStatus("completed");
	} else if ("away".equals(status)) {
	    PwWorkTimesDTO worktime = findWorktimeByUser(entrance.getUser().getUserId());
	    if (worktime != null) {
		PwAbsentsDTO absent = findLastAbsentByUser(entrance.getUser().getUserId());
		if (absent != null && absent.getBack() == null)
		    throw new IllegalOperationSequenceException();
		worktime.setWorkEnd(entrance.getPassTime());
	    } else {
		throw new IllegalOperationSequenceException();
	    }
	    entrance.setStatus("completed");
	} else if ("absent".equals(status)) {
	    PwWorkTimesDTO worktime = findWorktimeByUser(entrance.getUser().getUserId());
	    if (worktime != null) {
		PwAbsentsDTO absent = findLastAbsentByUser(entrance.getUser().getUserId());
		if (absent != null && absent.getBack() == null)
		    throw new IllegalOperationSequenceException();
		absent = new PwAbsentsDTO(BigDecimal.valueOf(nextPK()), worktime, getAbsentType(absentType));
		absent.setAway(entrance.getPassTime());
		absent.setAvgback(new Date(entrance.getPassTime().getTime() + absentTimeMin * 60 * 1000));
		database.getAbsents().add(absent);
	    } else {
		throw new IllegalOperationSequenceException();
	    }
	    entrance.setStatus("completed");
	} else if ("ignore".equals(status)) {
	    entrance.setStatus("ignored");
	} else
	    throw new UnknownStatusException();

    }

    private PwAbsentTypeDTO getAbsentType(String absentType) throws InvalidAbsentTypeException {
	if ("personal".equals(absentType))
	    return new PwAbsentTypeDTO(BigDecimal.valueOf(nextPK()), absentType);
	else if ("work".equals(absentType))
	    return new PwAbsentTypeDTO(BigDecimal.valueOf(nextPK()), absentType);
	else if (absentType==null)
	    return new PwAbsentTypeDTO(BigDecimal.valueOf(nextPK()), "personal");
	throw new InvalidAbsentTypeException();
    }

    private PwAbsentsDTO findLastAbsentByUser(BigDecimal userId) {
	List<PwAbsentsDTO> res = new ArrayList<PwAbsentsDTO>();
	for (PwAbsentsDTO a : database.getAbsents())
	    if (a.getPwWorkTimes().getPtUsers().getUserId().equals(userId))
		res.add(a);
	if (res.size() == 0)
	    return null;
	Collections.sort(res, new Comparator<PwAbsentsDTO>() {
	    @Override
	    public int compare(PwAbsentsDTO o1, PwAbsentsDTO o2) {

		return o1.getAway().compareTo(o2.getAway());
	    }
	});
	return res.get(res.size() - 1);
    }

    private PwWorkTimesDTO findWorktimeByUser(BigDecimal userId) {

	for (PwWorkTimesDTO wkt : database.getWorktimes())
	    if (wkt.getPtUsers().getUserId().equals(userId))
		return wkt;
	return null;
    }

    private PwEntrance findEntranceById(long id) {

	for (PwEntrance e : database.getEntrances())
	    if (e.getId() == id)
		return e;
	return null;
    }

    public List<PwAbsentsDTO> getActiveAbsents() {
	List<PwAbsentsDTO> res = new LinkedList<PwAbsentsDTO>();
	for (PwAbsentsDTO absent : database.getAbsents())
	    if (absent.getBack() == null)
		res.add(absent);
	return res;
    }

    public String getUserStatus(PtUsersDTO user) {
	String status;
	PwWorkTimesDTO worktime = findWorktimeByUser(user.getUserId());
	if (worktime != null) {
	    PwAbsentsDTO absent = findLastAbsentByUser(user.getUserId());
	    if (absent == null || absent.getBack() != null) {
		if (worktime.getWorkEnd() != null)
		    status = "away";
		else
		    status = "work";
	    } else
		status = "apsent";
	} else {
	    status = "none";
	}
	return status;
    }

    public List<PtUserMessages> getUserMessages(int userId) {
	List<PtUserMessages> msgs = new LinkedList<PtUserMessages>();
	
	for(PtUserMessages m: database.getUserMessages())
	{
	    if (m.getUser().getUserId().intValue()==userId)
		msgs.add(m);
	}
	return msgs;
    }
}
