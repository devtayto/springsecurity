package deptayto.API.controler;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import deptayto.API.entity.Student;
import deptayto.API.service.StudentService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentControler.class, secure = false)
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService studentService;

	/**
	 * Test api get all student pass if status return 200 else false case test 
	 * 
	 * @throws Exception
	 */
	@Test
	public void getAllStudent001() throws Exception {
		// data mock
		List<Student> students = new ArrayList<Student>();
		students.add(new Student(1, "thang", "thang", "thang"));

		Mockito.when(studentService.getAllStudent()).thenReturn(students);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(result.getResponse().getStatus(), 200);
	}
	/**
	 * test find by student id =1 if pass if status result = 200 else false case test 
	 * @throws Exception
	 */
	@Test
	public void getStudentFindByID001() throws Exception {
		// data mock
		Student student = new Student(1,"devtayto","devtayto","devtayto");

		Mockito.when(studentService.getStudent(1)).thenReturn(student);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/1").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(result.getResponse().getStatus(), 200);
	}

}
