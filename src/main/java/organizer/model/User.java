package organizer.model;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class User {
	private final long id;
	private final Map<Long, Appointment> appointments = new ConcurrentHashMap<>();

	private final AtomicLong nextId = new AtomicLong(0);

	public User(final long id) {
		this.id = id;
	}


	public Collection<Appointment> getAppointments() {
		return appointments.values();
	}

	public void addAppointment(final Appointment app) {
		final long id = nextId.incrementAndGet();
		app.setId(id);
		appointments.put(id, app);
	}

	public Appointment getAppointment(long id) {
		return appointments.get(id);
	}

	public void replaceAppointment(final Appointment app) {
		appointments.replace(app.getId(), app);
	}

	public void deleteAppointment(long id) {
		appointments.remove(id);
	}
}
