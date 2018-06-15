package organizer.converter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import organizer.dto.AppointmentFlat;

public class CsvHttpMessageConverter
		extends AbstractHttpMessageConverter<Collection<AppointmentFlat>> {
	private final ObjectWriter writer;

	public CsvHttpMessageConverter() {
		final CsvMapper mapper = new CsvMapper();
		final CsvSchema schema = mapper.schemaFor(AppointmentFlat.class).withColumnSeparator(';')
				.withHeader();
		writer = mapper.writer(schema);

	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return Arrays.asList(new MediaType("text", "csv"));
	}

	@Override
	protected boolean supports(final Class<?> clazz) {
		return true;
	}

	@Override
	protected Collection<AppointmentFlat> readInternal(
			final Class<? extends Collection<AppointmentFlat>> clazz,
			final HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		return null;
	}

	@Override
	protected void writeInternal(final Collection<AppointmentFlat> appointments,
			final HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		System.out.println(appointments);
		final OutputStream body = outputMessage.getBody();
		writer.writeValue(body, appointments);
		body.close();
		System.out.println("bla");
	}
}
