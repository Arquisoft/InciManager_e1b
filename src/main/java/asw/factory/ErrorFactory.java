package asw.factory;

import asw.webService.errors.ErrorResponse;
import asw.webService.errors.RequiredNameErrorResponse;
import asw.webService.errors.UnknownErrorResponse;

public class ErrorFactory {

	public static enum Errors {
		REQUIRED_INCIDENT_NAME
	}

	private ErrorFactory() {
	}

	public static ErrorResponse getError(Errors error) {
		switch (error) {
		case REQUIRED_INCIDENT_NAME:
			return new RequiredNameErrorResponse();
		default:
			return new UnknownErrorResponse();
		}
	}

}
