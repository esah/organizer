package organizer.web;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import organizer.dao.AppointmentRepository;
import organizer.model.User;
import organizer.model.Appointment;

@RestController
@RequestMapping(value = "appointments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AppointmentController {
	private final AppointmentRepository appointmentRepo;

	@Autowired
	public AppointmentController(final AppointmentRepository appointmentRepo) {
		this.appointmentRepo = appointmentRepo;
	}

	private User getUser(final @RequestParam("userId") long userId) {
		final User user = appointmentRepo.findUser(userId);
		if (user == null) {
			throw new HttpMessageNotReadableException(String
					.format("User %1s not found", userId));
		}
		return user;
	}

	@GetMapping
	public Collection<Appointment> list(@RequestParam("userId") long userId) {
		return getUser(userId).getAppointments();
	}

	@GetMapping("upcoming")
	public Collection<Appointment> listUpcoming(@RequestParam("userId") long userId) {
		return list(userId).stream().filter(a -> !a.isConfirmed() && !a.isCancelled()).collect(Collectors.toList());
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Appointment create(@RequestParam("userId") long userId,
			@RequestBody Appointment appointment) {
		assert appointment.getId() == null;
		getUser(userId).addAppointment(appointment);
		return appointment;
	}

	@PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Appointment edit(@RequestParam("userId") long userId,
			@PathVariable("id") long id,
			@RequestBody Appointment appointment) {
		assert id == appointment.getId();
		getUser(userId).replaceAppointment(appointment);
		return appointment;
	}

	@DeleteMapping("{id}")
	public void delete(@RequestParam("userId") long userId,
			@RequestParam("id") long id) {
		getUser(userId).deleteAppointment(id);
	}

	@PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Appointment changeState(@RequestParam("userId") long userId,
			@PathVariable("id") long id,
			@RequestBody Map<String, Object> updates) {
		final Appointment appointment = getUser(userId).getAppointment(id);

		if (updates.containsKey("cancelled")) {
			appointment.setCancelled((Boolean) updates.get("cancelled"));
			return appointment;
		}
		if (updates.containsKey("confirmed")) {
			appointment.setConfirmed((Boolean) updates.get("confirmed"));
			appointment.setNote((String) updates.get("note"));
			return appointment;
		}
		return appointment;
	}

}
