package deptayto.API.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deptayto.API.entity.Student;
import deptayto.API.repository.StudentRepository;
import deptayto.API.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudent() {
		List<Student> listStudent = new ArrayList<Student>();
		listStudent = studentRepository.findAll();
		return listStudent;
	}

	@Override
	public Student getStudent(long id) {
		Student student;
		student = studentRepository.findOne(id);
		return student;
	}

	@Override
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student updateStudent(Student student) {
		student = studentRepository.save(student);
		return student;

	}

	@Override
	public boolean deleteStudent(long id) {
		Student student;
		student = studentRepository.findOne(id);
		if (student != null) {
			studentRepository.delete(id);
			return true;
		}
		return false;

	}

}
