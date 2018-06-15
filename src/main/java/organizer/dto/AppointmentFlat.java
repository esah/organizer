package organizer.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppointmentFlat {
	long id;
	private Date date;
	private String companyName;
	private String companyUrl;
	private String postalCode;
	private String locality;
	private String street;
	private String house;
	private String title;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
}

