package asw.util;

import asw.factory.ErrorFactory;
import asw.factory.ErrorFactory.Errors;

public class Assert {

	public static boolean isIncidentNameEmpty(String incidentName) {
		if (incidentName.trim().isEmpty())
			throw ErrorFactory.getError(Errors.REQUIRED_INCIDENT_NAME);
		else
			return true;
	}

	public static boolean isIncidentAssignedToEmpty(String assignedTo) {
		if (assignedTo.trim().isEmpty())
			throw ErrorFactory.getError(Errors.REQUIRED_INCIDENT_ASSIGNEDTO);
		else
			return true;
	}

	public static boolean isIncidentDescriptionEmpty(String description) {
		if (description.trim().isEmpty())
			throw ErrorFactory.getError(Errors.REQUIRED_INCIDENT_DESCRIPTION);
		else
			return true;
	}

	public static boolean isIncidentExpirationEmpty(String expiration) {
		if (expiration.trim().isEmpty())
			throw ErrorFactory.getError(Errors.REQUIRED_INCIDENT_EXPIRATION);
		else
			return true;
	}

	public static boolean isIncidentInfomationEmpty(String information) {
		if (information.trim().isEmpty())
			throw ErrorFactory.getError(Errors.REQUIRED_INCIDENT_INFORMATION);
		else
			return true;
	}

	public static boolean isIncidentNotificationEmpty(String notification) {
		if (notification.trim().isEmpty())
			throw ErrorFactory.getError(Errors.REQUIRED_INCIDENT_NOTIFICATION);
		else
			return true;
	}

	public static boolean isIncidentPropertiesEmpty(String properties) {
		if (properties.trim().isEmpty())
			throw ErrorFactory.getError(Errors.REQUIRED_INCIDENT_PROPERTIES);
		else
			return true;
	}

	public static boolean isIncidentStateEmpty(String state) {
		if (state.trim().isEmpty())
			throw ErrorFactory.getError(Errors.REQUIRED_INCIDENT_STATE);
		else
			return true;
	}

	public static boolean isIncidentTagsEmpty(String tags) {
		if (tags.trim().isEmpty())
			throw ErrorFactory.getError(Errors.REQUIRED_INCIDENT_TAGS);
		else
			return true;
	}

	public static boolean areTagsValid(String ident) {
		if (!ident.matches("[a-zA-Z0-9]*(,|[a-zA-Z0-9])+")) {
			throw ErrorFactory.getError(Errors.WRONG_INCIDENT_TAGS);
		}
		return true;
	}

	public static boolean arePropertiesValid(String ident) {
		if (!ident.matches("([a-zA-Z0-9]*:[a-zA-Z0-9]*)(,|[a-zA-Z0-9]*:[a-zA-Z0-9]*)*")) {
			throw ErrorFactory.getError(Errors.WRONG_INCIDENT_PROPERTIES);
		}
		return true;
	}

	/**
	 * Comprueba que la localización que se le pasa sea válida. Solo le permite dos
	 * números enteros o flotantes separados por una coma comprendidos en [-90,90]
	 * 
	 * @param location
	 */
	public static boolean isLocationValid(String location) {
		String[] cords = location.split(",");

		if (cords.length == 2) {

			if (cords[0].matches("-?[0-9]*") || cords[0].matches("-?[0-9]*.[0-9]*") && cords[1].matches("-?[0-9]*")
					|| cords[1].matches("-?[0-9]*.[0-9]*")) {

				double d1 = Float.parseFloat(cords[0]);
				double d2 = Float.parseFloat(cords[1]);

				if (d1 >= -90.0 && d1 <= 90.0 && d2 >= -90.0 && d2 <= 90.0) {
					return true;
				} else {
					throw ErrorFactory.getError(Errors.WRONG_INCIDENT_LOCATION);
				}
			} else {
				throw ErrorFactory.getError(Errors.WRONG_INCIDENT_LOCATION);
			}

		} else {
			throw ErrorFactory.getError(Errors.WRONG_INCIDENT_LOCATION);
		}

	}

}
