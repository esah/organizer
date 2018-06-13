package organizer.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import organizer.model.User;

@ApplicationScope
@Service
public class AppointmentRepository {
	public static final long FOREFATHER_ID = 1L;

	private Map<Long, User> users = new ConcurrentHashMap<>();

	@PostConstruct
	private void initForefather() {
		users.put(FOREFATHER_ID, new User(1));
	}


	public User findUser(long id) {
		return users.get(id);
	}

}
