package ssg.com.a.dto;

import java.io.Serializable;

public class StudentDto implements Serializable {
	private String name;
	private int age;
	private double height;

	public StudentDto() {
	}

	public StudentDto(String name, int age, double height) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "StudentDto [name=" + name + ", age=" + age + ", height=" + height + "]";
	}

}
