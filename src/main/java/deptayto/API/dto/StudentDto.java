package deptayto.API.dto;

public class StudentDto {
	private long id;

	private String schoolName;

	private String className;

	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StudentDto(long id, String schoolName, String className, String name) {
		this.id = id;
		this.schoolName = schoolName;
		this.className = className;
		this.name = name;
	}

	public StudentDto() {

	}

}
