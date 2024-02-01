package dto;

public class MovieDto {

	private String title;
	private double percent;
	
	public MovieDto() {
	}

	public MovieDto(String title, double percent) {
		super();
		this.title = title;
		this.percent = percent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	@Override
	public String toString() {
		return "MovieDto [title=" + title + ", percent=" + percent + "]";
	}
}
