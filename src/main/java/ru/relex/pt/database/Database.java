package ru.relex.pt.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.jcip.annotations.ThreadSafe;

import ru.relex.pt.dto.PtApiKeys;
import ru.relex.pt.dto.PtUserMessages;
import ru.relex.pt.dto.PtUsersDTO;
import ru.relex.pt.dto.PwAbsentsDTO;
import ru.relex.pt.dto.PwEntrance;
import ru.relex.pt.dto.PwWorkTimesDTO;

public class Database {

    private static final String USER_MESSAGES_FILE = "user_messages.txt";
    private Queue<PtUsersDTO> users = new ConcurrentLinkedQueue<PtUsersDTO>();
    private Queue<PtApiKeys> keys = new ConcurrentLinkedQueue<PtApiKeys>();
    private Queue<PwAbsentsDTO> absents = new ConcurrentLinkedQueue<PwAbsentsDTO>();
    private Queue<PwEntrance> entrances = new ConcurrentLinkedQueue<PwEntrance>();
    private Queue<PwWorkTimesDTO> worktimes = new ConcurrentLinkedQueue<PwWorkTimesDTO>();

    private Queue<PtUserMessages> userMessages = new ConcurrentLinkedQueue<PtUserMessages>();

    private static Database instance;

    private List<String> messages;

    private Database() {
	// add users
	PtUsersDTO user1, user2, user3;
	users.add(user1 = new PtUsersDTO(BigDecimal.valueOf(1), "Бакланов", "Михаил", "Владимирович", "bmw", "", false,
		"", "", ""));
	users.add(user2 = new PtUsersDTO(BigDecimal.valueOf(2), "Лапин", "Денис", "Юрьевич", "ldu", "", false, "", "",
		""));
	users.add(user3 = new PtUsersDTO(BigDecimal.valueOf(3), "Мельников", "Вадим", "Митрофанович", "mvm", "", false,
		"", "", ""));

	// add keys;

	keys.add(new PtApiKeys(1L, user1, new Date(1000, 0, 1), "barcode:123"));
	keys.add(new PtApiKeys(2L, user1, new Date(1000, 0, 1), "qr:Hello,Mihael!"));
	keys.add(new PtApiKeys(3L, user1, new Date(1000, 0, 1), "mac:0023D4A33819"));
	keys.add(new PtApiKeys(4L, user2, new Date(1000, 0, 1), "qr:Hello,Denis!"));
	keys.add(new PtApiKeys(5L, user3, new Date(1000, 0, 1), "mac:30A8DB4CD9EB"));

	// user messages
	messages = loadMessages();

	userMessages.addAll(generateUserMessages(user1));
	userMessages.addAll(generateUserMessages(user2));
	userMessages.addAll(generateUserMessages(user3));
    }

    private List<String> loadMessages() {
	List<String> msgs = new ArrayList<String>(100);
	File text;
	if (System.getProperty("pt.home") != null)
	    text = new File(System.getProperty("pt.home") + File.separator + USER_MESSAGES_FILE);
	else
	    text = new File(USER_MESSAGES_FILE);

	BufferedReader reader = null;
	try {
	    if (text.exists()) {
		reader = new BufferedReader(new FileReader(text));
	    } else {
		reader = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader()
			.getResourceAsStream(USER_MESSAGES_FILE), Charset.forName("UTF-8")));
	    }
	    if (reader != null) {
		String str;
		str = reader.readLine();
		while (str != null) {
		    msgs.add(str);
		    str = reader.readLine();
		}
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    if (reader != null)
		try {
		    reader.close();
		} catch (IOException e) {

		}
	}

	return msgs;
    }

    private Collection<PtUserMessages> generateUserMessages(PtUsersDTO user) {
	List<PtUserMessages> msgs = new LinkedList<PtUserMessages>();
	for (int i = 0; i < Math.random() * 3; i++) {
	    msgs.add(new PtUserMessages(user, "", this.messages.get((int) Math.floor(Math.random()
		    * this.messages.size()))));
	}
	return msgs;
    }

    public static Database getInstance() {
	if (instance == null) {
	    instance = new Database();
	}
	return instance;
    }

    public Collection<PtUsersDTO> getUsers() {
	return users;
    }

    public Collection<PtApiKeys> getKeys() {
	return keys;
    }

    public Collection<PwAbsentsDTO> getAbsents() {
	return absents;
    }

    public Collection<PwEntrance> getEntrances() {
	return entrances;
    }

    public Collection<PwWorkTimesDTO> getWorktimes() {
	return worktimes;
    }

    public Collection<PtUserMessages> getUserMessages() {

	return userMessages;
    }
}
