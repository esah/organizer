package organizer.model;

import java.util.Date;

public class Appointment {
	private Long id;
	private Date date;
	private Company contact;

	private String note;
	private boolean cancelled;
	private boolean confirmed;

	public Appointment() {
	}

	public Appointment(final Date date, final Company contact) {
		this.id = id;
		this.date = date;
		this.contact = contact;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public Company getContact() {
		return contact;
	}

	public void setContact(final Company contact) {
		this.contact = contact;
	}

	public String getNote() {
		return note;
	}

	public void setNote(final String note) {
		this.note = note;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(final boolean cancelled) {
		this.cancelled = cancelled;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(final boolean confirmed) {
		this.confirmed = confirmed;
	}
}
