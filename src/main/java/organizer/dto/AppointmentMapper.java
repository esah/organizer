package organizer.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import organizer.model.Appointment;

@Mapper
public interface AppointmentMapper {

	AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

	@Mapping(source = "appointment.contact.name", target = "companyName")
	@Mapping(source = "appointment.contact.url", target = "companyUrl")
	@Mapping(source = "appointment.contact.contactAddress.postalCode", target = "postalCode")
	@Mapping(source = "appointment.contact.contactAddress.locality", target = "locality")
	@Mapping(source = "appointment.contact.contactAddress.street", target = "street")
	@Mapping(source = "appointment.contact.contactAddress.house", target = "house")
	@Mapping(source = "appointment.contact.contactPerson.title", target = "title")
	@Mapping(source = "appointment.contact.contactPerson.firstName", target = "firstName")
	@Mapping(source = "appointment.contact.contactPerson.lastName", target = "lastName")
	@Mapping(source = "appointment.contact.contactPerson.email", target = "email")
	@Mapping(source = "appointment.contact.contactPerson.phone.phoneAsText", target = "phoneNumber")
	AppointmentFlat toFlat(Appointment appointment);
}
