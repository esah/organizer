package organizer.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import organizer.model.User;

@ApplicationScope
@Service
public class AppointmentRepository {
	private Map<Long, User> users = new ConcurrentHashMap<>();

	private AtomicLong userId = new AtomicLong(0);

	public User findUser(long id) {
		return users.get(id);
	}

	public User newUser() {
		final long id = userId.incrementAndGet();
		final User result = new User(id);
		users.put(id, result);
		return result;
	}

}
