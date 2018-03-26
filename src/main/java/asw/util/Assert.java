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

}
