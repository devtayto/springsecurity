package deptayto.API.untils;

import java.util.ArrayList;
import java.util.List;

import deptayto.API.dto.StudentDto;
import deptayto.API.entity.Student;

public class ConvertStudent {
	/**
	 * Covert from studentDto to Student entity
	 * 
	 * @param studentDto
	 * @return
	 */
	public static Student StudentDtoToStudent(StudentDto studentDto) {
		return new Student(studentDto.getId(), studentDto.getSchoolName(), studentDto.getClassName(),
				studentDto.getName());
	}

	/**
	 * Covert list from studentDto to Student entity
	 * 
	 * @param studentDtos
	 * @return
	 */
	public static List<Student> ListStudentDtoToStudent(List<StudentDto> studentDtos) {
		List<Student> students = new ArrayList<Student>();
		for (StudentDto studentDto : studentDtos) {
			students.add(new Student(studentDto.getId(), studentDto.getSchoolName(), studentDto.getClassName(),
					studentDto.getName()));
		}
		return students;
	}

	/**
	 * Covert from Student entity to tostudentDto
	 * 
	 * @param studentDto
	 * @return
	 */
	public static StudentDto StudentToStudentDto(Student student) {
		return new StudentDto(student.getId(), student.getSchoolName(), student.getClassName(), student.getName());
	}

	/**
	 * Covert list from Student entity to tostudentDto
	 * 
	 * @param studentDtos
	 * @return
	 */
	public static List<StudentDto> ListStudentToStudentDto(List<Student> students) {
		List<StudentDto> studentDtos = new ArrayList<StudentDto>();
		for (Student student : students) {
			studentDtos.add(new StudentDto(student.getId(), student.getSchoolName(), student.getClassName(),
					student.getName()));
		}
		return studentDtos;
	}

}
