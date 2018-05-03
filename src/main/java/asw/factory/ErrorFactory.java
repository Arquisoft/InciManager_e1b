package asw.factory;

import asw.webService.errors.ErrorResponse;
import asw.webService.errors.RequiredAssignedToErrorResponse;
import asw.webService.errors.RequiredDescriptionErrorResponse;
import asw.webService.errors.RequiredExpirationErrorResponse;
import asw.webService.errors.RequiredInformationErrorResponse;
import asw.webService.errors.RequiredNameErrorResponse;
import asw.webService.errors.RequiredNotificationErrorResponse;
import asw.webService.errors.RequiredPropertiesErrorResponse;
import asw.webService.errors.RequiredStateErrorResponse;
import asw.webService.errors.RequiredTagsErrorResponse;
import asw.webService.errors.UnknownErrorResponse;
import asw.webService.errors.WrongLocationStyleErrorResponse;
import asw.webService.errors.WrongPropertiesStyleErrorResponse;
import asw.webService.errors.WrongTagsStyleErrorResponse;

public class ErrorFactory {

	public static enum Errors {
		REQUIRED_INCIDENT_NAME, REQUIRED_INCIDENT_ASSIGNEDTO, REQUIRED_INCIDENT_DESCRIPTION, REQUIRED_INCIDENT_EXPIRATION, REQUIRED_INCIDENT_INFORMATION, REQUIRED_INCIDENT_NOTIFICATION, REQUIRED_INCIDENT_PROPERTIES, REQUIRED_INCIDENT_STATE, REQUIRED_INCIDENT_TAGS, WRONG_INCIDENT_TAGS, WRONG_INCIDENT_PROPERTIES, WRONG_INCIDENT_LOCATION
	}

	private ErrorFactory() {
	}

	public static ErrorResponse getError(Errors error) {
		switch (error) {
		case REQUIRED_INCIDENT_NAME:
			return new RequiredNameErrorResponse();
		case REQUIRED_INCIDENT_ASSIGNEDTO:
			return new RequiredAssignedToErrorResponse();
		case REQUIRED_INCIDENT_DESCRIPTION:
			return new RequiredDescriptionErrorResponse();
		case REQUIRED_INCIDENT_EXPIRATION:
			return new RequiredExpirationErrorResponse();
		case REQUIRED_INCIDENT_INFORMATION:
			return new RequiredInformationErrorResponse();
		case REQUIRED_INCIDENT_NOTIFICATION:
			return new RequiredNotificationErrorResponse();
		case REQUIRED_INCIDENT_PROPERTIES:
			return new RequiredPropertiesErrorResponse();
		case REQUIRED_INCIDENT_STATE:
			return new RequiredStateErrorResponse();
		case REQUIRED_INCIDENT_TAGS:
			return new RequiredTagsErrorResponse();
		case WRONG_INCIDENT_TAGS:
			return new WrongTagsStyleErrorResponse();
		case WRONG_INCIDENT_PROPERTIES:
			return new WrongPropertiesStyleErrorResponse();
		case WRONG_INCIDENT_LOCATION:
			return new WrongLocationStyleErrorResponse();
		default:
			return new UnknownErrorResponse();
		}
	}

}