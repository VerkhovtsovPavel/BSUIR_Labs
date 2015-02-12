package factory;

import gift.GiftElement;
import gift.Sweet;
import gift.Wrapping;

public class GiftFactory {
	
	private FactoryParametersParser parser = new FactoryParametersParser();

	public GiftElement getGiftElement(final String requaredGiftElement) {
		String objectType = parser.getObjectType(requaredGiftElement);
		String params = parser.getParams(requaredGiftElement);
		switch (objectType) {
		case "Sweet":
			long sweethess = Long.valueOf(params);
			return new Sweet(sweethess);
		case "Wrapping":
			String[] patternAndColor = params.split("[ \\t]+");
			String pattern = patternAndColor[0];
			String color = patternAndColor[1];
			return new Wrapping(pattern, color);
		default:
			return null;
		}
	}
}
