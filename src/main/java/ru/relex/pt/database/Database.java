package ru.relex.pt.database;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import ru.relex.pt.dto.PtApiKeys;
import ru.relex.pt.dto.PtUsersDTO;
import ru.relex.pt.dto.PwAbsentsDTO;
import ru.relex.pt.dto.PwEntrance;
import ru.relex.pt.dto.PwWorkTimesDTO;

public class Database {

	private static Queue<PtUsersDTO> users = new ConcurrentLinkedQueue<PtUsersDTO>();
	private static Queue<PtApiKeys> keys = new ConcurrentLinkedQueue<PtApiKeys>();
	private static Queue<PwAbsentsDTO> absents = new ConcurrentLinkedQueue<PwAbsentsDTO>();
	private static Queue<PwEntrance> entrances = new ConcurrentLinkedQueue<PwEntrance>();
	private static Queue<PwWorkTimesDTO> worktimes= new ConcurrentLinkedQueue<PwWorkTimesDTO>();
	
	private static Database instance;
	
	private Database()
	{
		// add users
	    	PtUsersDTO user1, user2, user3;
		users.add(user1 = new PtUsersDTO(BigDecimal.valueOf(1), "Бакланов", "Михаил", "Владимирович", "bmw", "",false,"","",""));
		users.add(user2 = new PtUsersDTO(BigDecimal.valueOf(2), "Лапин", "Денис", "Юрьевич", "ldu", "",false,"","",""));
		users.add(user3 = new PtUsersDTO(BigDecimal.valueOf(3), "Мельников", "Вадим", "Митрофанович", "mvm", "",false,"","",""));
		
		// add keys;
		
		keys.add(new PtApiKeys(1L, user1, new Date(1000,0,1), "barcode:123"));
		keys.add(new PtApiKeys(2L, user1, new Date(1000,0,1), "qr:Hello,Mihael!"));
		keys.add(new PtApiKeys(3L, user1, new Date(1000,0,1), "mac:0023D4A33819"));
		keys.add(new PtApiKeys(4L, user2, new Date(1000,0,1), "qr:Hello,Denis!"));
		keys.add(new PtApiKeys(5L, user3, new Date(1000,0,1), "mac:30A8DB4CD9EB"));
	}
	
	public static Database getInstance() {
		if (instance == null)
		{
			instance = new Database();
		}
		return instance;
	}

	public  Collection<PtUsersDTO> getUsers() {
		return users;
	}

	public  Collection<PtApiKeys> getKeys() {
		return keys;
	}

	public  Collection<PwAbsentsDTO> getAbsents() {
		return absents;
	}

	public  Collection<PwEntrance> getEntrances() {
		return entrances;
	}

	public  Collection<PwWorkTimesDTO> getWorktimes() {
		return worktimes;
	}
}
