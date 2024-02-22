package com.ssg.a.dto;

public class ReadCountDto {

	private int seq;
	private String id;
	private String category;
	private int checkseq;
	
	public ReadCountDto() {
	}

	public ReadCountDto(String id, int checkseq) {
		super();
		this.id = id;
		this.checkseq = checkseq;
	}

	public ReadCountDto(int seq, String id, String category, int checkseq) {
		super();
		this.seq = seq;
		this.id = id;
		this.category = category;
		this.checkseq = checkseq;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getCheckseq() {
		return checkseq;
	}

	public void setCheckseq(int checkseq) {
		this.checkseq = checkseq;
	}

	@Override
	public String toString() {
		return "ReadCountDto [seq=" + seq + ", id=" + id + ", category=" + category + ", checkseq=" + checkseq + "]";
	}
}
