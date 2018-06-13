package organizer.model;

public class Company {
	private String name;
	private String url;
	private Address contactAddress;
	private Person contactPerson;

	public Company() {
	}
	public Company(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public Address getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(final Address contactAddress) {
		this.contactAddress = contactAddress;
	}

	public Person getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(final Person contactPerson) {
		this.contactPerson = contactPerson;
	}
}
