package kh.spring.dto;

public class ContactDTO {
	private int seq;
	private String name;
	private String contact;
	
	public ContactDTO() {}

	public ContactDTO(int seq, String name, String contact) {
		super();
		this.seq = seq;
		this.name = name;
		this.contact = contact;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
}
