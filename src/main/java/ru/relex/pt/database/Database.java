package ru.relex.pt.database;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ru.relex.pt.dto.PtApiKeys;
import ru.relex.pt.dto.PtUsersDTO;
import ru.relex.pt.dto.PwAbsentsDTO;
import ru.relex.pt.dto.PwEntrance;
import ru.relex.pt.dto.PwWorkTimesDTO;

public class Database {

	private static List<PtUsersDTO> users = new LinkedList<PtUsersDTO>();
	private static List<PtApiKeys> keys = new LinkedList<PtApiKeys>();
	private static List<PwAbsentsDTO> absents = new LinkedList<PwAbsentsDTO>();
	private static List<PwEntrance> entrances = new LinkedList<PwEntrance>();
	private static List<PwWorkTimesDTO> worktimes= new LinkedList<PwWorkTimesDTO>();
	
	private static Database instance;
	
	private Database()
	{
		// add users
		users.add(new PtUsersDTO(BigDecimal.valueOf(1), "Бакланов", "Михаил", "Владимирович", "bmw", "",false,"","",""));
		users.add(new PtUsersDTO(BigDecimal.valueOf(2), "Лапин", "Денис", "Юрьевич", "ldu", "",false,"","",""));
		users.add(new PtUsersDTO(BigDecimal.valueOf(3), "Мельников", "Вадим", "Митрофанович", "mvm", "",false,"","",""));
		
		// add keys;
		
		keys.add(new PtApiKeys(1L, users.get(0), new Date(1000,0,1), "barcode:123"));
		keys.add(new PtApiKeys(2L, users.get(0), new Date(1000,0,1), "qr:Hello,Mihael!"));
		keys.add(new PtApiKeys(3L, users.get(0), new Date(1000,0,1), "mac:0023D4A33819!"));
		keys.add(new PtApiKeys(4L, users.get(1), new Date(1000,0,1), "qr:Hello,Denis!"));
		keys.add(new PtApiKeys(5L, users.get(2), new Date(1000,0,1), "mac:30A8DB4CD9EB"));
	}
	
	public static Database getInstance() {
		if (instance == null)
		{
			instance = new Database();
		}
		return instance;
	}

	public  List<PtUsersDTO> getUsers() {
		return users;
	}

	public  List<PtApiKeys> getKeys() {
		return keys;
	}

	public  List<PwAbsentsDTO> getAbsents() {
		return absents;
	}

	public  List<PwEntrance> getEntrances() {
		return entrances;
	}

	public  List<PwWorkTimesDTO> getWorktimes() {
		return worktimes;
	}
}
