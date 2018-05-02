package asw.webService.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Wrong location style")
public class WrongLocationStyleErrorResponse extends ErrorResponse {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessageJSONFormat() {
		return "{\"reason\": \"Wrong location style\"}";
	}

	@Override
	public String getMessageStringFormat() {
		return "Wrong location style";
	}

}
