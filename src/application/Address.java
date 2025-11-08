package application;

public class Address {
	AlertTypes Alert = new AlertTypes();
	private String street;
	private String city;
	private String country;

	public Address() {
	}

	public Address(String street, String city, String country) {
		setStreet(street);
		setCity(city);
		setCountry(country);
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		Validation1(country, "Country");
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		Validation2(city, "City ");
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		Validation1(street, "Street ");
		this.street = street;
	}

	public void Validation1(String value, String fieldName) {
		if (!value.matches("[a-zA-Z0-9 ]+")) {
			Alert.ErrorAlert("Error", fieldName + "Must not be contain a Special Characters");
		}
	}

	public void Validation2(String value, String fieldName) {
		if (!value.matches("[a-zA-Z0-9 ]+")) {
			Alert.ErrorAlert("Error", fieldName + "Must not be contain a Special Characters");
		}
	}

	@Override
	public String toString() {
		return "Address [street" + street + ", city=" + city + ", country=" + country + "]";
	}

}
