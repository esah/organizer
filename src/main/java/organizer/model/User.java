package organizer.model;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
	private long id;
	@JsonIgnore
	private final Map<Long, Appointment> appointments = new ConcurrentHashMap<>();
	@JsonIgnore
	private final AtomicLong appointmentId = new AtomicLong(0);

	public User() {
	}

	public User(final long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public Collection<Appointment> getAppointments() {
		return appointments.values();
	}

	public void addAppointment(final Appointment app) {
		final long id = appointmentId.incrementAndGet();
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
