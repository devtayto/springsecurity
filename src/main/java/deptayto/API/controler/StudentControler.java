package deptayto.API.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import deptayto.API.dto.StudentDto;
import deptayto.API.entity.Student;
import deptayto.API.service.StudentService;
import deptayto.API.untils.ApiMessages;
import deptayto.API.untils.ConvertStudent;

@Controller
@RequestMapping("students")
public class StudentControler {
	@Autowired
	private StudentService studentService;

	@GetMapping("")
	public ResponseEntity<List<StudentDto>> getAll() {
		List<StudentDto> studentDtos = ConvertStudent.ListStudentToStudentDto(studentService.getAllStudent());
		return new ResponseEntity<List<StudentDto>>(studentDtos, HttpStatus.OK);
	}

	/*
	 * get student find by id
	 */
	@GetMapping("{id}")
	public ResponseEntity<StudentDto> getStudent(@PathVariable("id") long id) {
		if (studentService.getStudent(id) == null) {
			ApiMessages apiError = new ApiMessages("not found student id " + id);
			return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
		}
		StudentDto studentDto = ConvertStudent.StudentToStudentDto(studentService.getStudent(id));
		return new ResponseEntity<StudentDto>(studentDto, HttpStatus.OK);
	}

	/*
	 * add new student
	 */
	@PostMapping("")
	public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto) {

		Student student = ConvertStudent.StudentDtoToStudent(studentDto);
		StudentDto dataStudentDto = ConvertStudent.StudentToStudentDto(studentService.addStudent(student));
		return new ResponseEntity<StudentDto>(dataStudentDto, HttpStatus.OK);
	}

	/*
	 * update student
	 */
	@PutMapping("")
	public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto) {

		if (studentService.getStudent(studentDto.getId()) == null) {
			ApiMessages apiError = new ApiMessages("Student not found in database");
			return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
		}
		Student student = ConvertStudent.StudentDtoToStudent(studentDto);
		StudentDto dataStudentDto = ConvertStudent.StudentToStudentDto(studentService.updateStudent(student));
		return new ResponseEntity<StudentDto>(dataStudentDto, HttpStatus.OK);
	}

	/*
	 * delete student
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") long id) {
		if (studentService.deleteStudent(id)) {
			ApiMessages apiError = new ApiMessages("delete success student with id " + id);
			return new ResponseEntity(apiError, HttpStatus.OK);
		}
		ApiMessages apiError = new ApiMessages("delete dont success student with id " + id);
		return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
	}

}
