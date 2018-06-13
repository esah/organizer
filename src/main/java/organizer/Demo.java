package organizer;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import organizer.model.Address;
import organizer.model.Appointment;
import organizer.model.Company;
import organizer.model.Person;
import organizer.model.Phone;

public class Demo {
	private static final Logger LOG = LoggerFactory.getLogger(Demo.class);


	public static void run(final RestTemplate restTemplate) {
		//create
		final Appointment demoAppointment = createDemoAppointment();
		final Appointment appointment = restTemplate
				.postForObject("http://localhost:8080/appointments?userId=1",
						demoAppointment, Appointment.class);

		//update
		appointment.getContact().getContactPerson().setLastName("Bond");

		Map<String, Object> params = new HashMap<>();
		params.put("id", appointment.getId());

		restTemplate.put("http://localhost:8080/appointments/{id}?userId=1",
				appointment,  params);


		//confirm
		Map<String, Object> updates = new HashMap<>();
		updates.put("confirmed", true);
		updates.put("note", "Everything went perfectly well");

		final Appointment confirmedAppointment = restTemplate
				.patchForObject("http://localhost:8080/appointments/{id}?userId=1",
						updates, Appointment.class, params);
		assert confirmedAppointment.isConfirmed();

		final Collection appointments = restTemplate
				.getForObject("http://localhost:8080/appointments?userId=1", Collection.class);
		LOG.info("GET http://localhost:8080/appointments?userId=1\n" + appointments.toString());
	}



	public static Appointment createDemoAppointment() {
		return new Appointment(new Date(), createDemoCompany());
	}

	public static Company createDemoCompany() {
		final Company contact = new Company("Ecco GmbH");
		contact.setUrl("http://ecco.de");
		contact.setContactAddress(createDemoAddress());
		contact.setContactPerson(createDemoPerson());
		return contact;
	}

	private static Person createDemoPerson() {
		final Person person = new Person();
		person.setLastName("Smith");
		person.setFirstName("James");
		person.setEmail("smith@ecco.de");
		person.setTitle("Mr");
		person.setPhone(new Phone("49", "30", "80492274"));
		return person;
	}

	private static Address createDemoAddress() {
		final Address address = new Address();
		address.setHouse("191");
		address.setLocality("Berlin, Charlottenburg");
		address.setStreet("Friedrichstraße");
		address.setPostalCode("10117");
		return address;
	}
}
