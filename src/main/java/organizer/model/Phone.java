package organizer.model;

public class Phone {
	private String countryCode;
	private String areaCode;
	private String number;

	public Phone() {
	}

	public Phone(final String countryCode, final String areaCode, final String number) {
		this.countryCode = countryCode;
		this.areaCode = areaCode;
		this.number = number;
	}

	public String getPhoneAsText() {
		return "+" + countryCode + " " + areaCode + " " + number;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public String getNumber() {
		return number;
	}
}
