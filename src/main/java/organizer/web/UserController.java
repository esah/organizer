package organizer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import organizer.dao.AppointmentRepository;
import organizer.model.User;

@RestController
@RequestMapping(value = "users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

	private final AppointmentRepository appointmentRepo;

	@Autowired
	public UserController(final AppointmentRepository appointmentRepo) {
		this.appointmentRepo = appointmentRepo;
	}

	@PostMapping
	public User create() {
		return appointmentRepo.newUser();
	}

}
