package deptayto.API.service;

import java.util.List;

import deptayto.API.entity.Student;

public interface StudentService {
	/**
	 * get all student
	 * 
	 * @return list student
	 */
	public List<Student> getAllStudent();

	/**
	 * 
	 * @param id input student id
	 * @return Student
	 */
	public Student getStudent(long id);

	/**
	 * 
	 * @param studentDto input information student
	 * @return return true if add success else return false
	 */
	public Student addStudent(Student student);

	/**
	 * 
	 * @return return true if update success else return false
	 */
	public Student updateStudent(Student student);

	/**
	 * 
	 * @param id input id of student
	 * @return return true if delete success else return false
	 */
	public boolean deleteStudent(long id);

}
