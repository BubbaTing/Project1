package project1.model;

import java.math.BigDecimal;

public class Reimbursement {
	private int reimb_id;
	private BigDecimal amount;
	private String submit;
	private String resolve;
	private String description;
	private String pic;
	private int author;
	private int resolver;
	private int status;
	private int type;
	public int getReimb_id() {
		return reimb_id;
	}
	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal i) {
		this.amount = i;
	}
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	public String getResolve() {
		return resolve;
	}
	public void setResolve(String resolve) {
		this.resolve = resolve;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	public int getResolver() {
		return resolver;
	}
	public void setResolver(int resolver) {
		this.resolver = resolver;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + author;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((pic == null) ? 0 : pic.hashCode());
		result = prime * result + reimb_id;
		result = prime * result + ((resolve == null) ? 0 : resolve.hashCode());
		result = prime * result + resolver;
		result = prime * result + status;
		result = prime * result + ((submit == null) ? 0 : submit.hashCode());
		result = prime * result + type;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (author != other.author)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (pic == null) {
			if (other.pic != null)
				return false;
		} else if (!pic.equals(other.pic))
			return false;
		if (reimb_id != other.reimb_id)
			return false;
		if (resolve == null) {
			if (other.resolve != null)
				return false;
		} else if (!resolve.equals(other.resolve))
			return false;
		if (resolver != other.resolver)
			return false;
		if (status != other.status)
			return false;
		if (submit == null) {
			if (other.submit != null)
				return false;
		} else if (!submit.equals(other.submit))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reimbursement [reimb_id=" + reimb_id + ", amount=" + amount + ", submit=" + submit + ", resolve="
				+ resolve + ", description=" + description + ", pic=" + pic + ", author=" + author + ", resolver="
				+ resolver + ", status=" + status + ", type=" + type + "]";
	}
	public Reimbursement(int reimb_id, BigDecimal amount, String submit, String resolve, String description, String pic,
			int author, int resolver, int status, int type) {
		super();
		this.reimb_id = reimb_id;
		this.amount = amount;
		this.submit = submit;
		this.resolve = resolve;
		this.description = description;
		this.pic = pic;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
}
