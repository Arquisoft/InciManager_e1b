package asw.webService.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Incidence additional information is required")
public class RequiredInformationErrorResponse extends ErrorResponse {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessageJSONFormat() {
		return "{\"reason\": \"Incidence additional information is required\"}";
	}

	@Override
	public String getMessageStringFormat() {
		return "Incidence additional information is required";
	}

}