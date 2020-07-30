package com.example.truc_nguyen_demo.controller;

import com.example.truc_nguyen_demo.builder.StudentBuilder;
import com.example.truc_nguyen_demo.exception.ResourceNotFoundException;
import com.example.truc_nguyen_demo.model.Entity.Student;
import com.example.truc_nguyen_demo.repository.IStudentRepository;
import com.example.truc_nguyen_demo.service.studentService.IStudentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class StudentControllerTest {

    @TestConfiguration
    static class TemplateControllerTestContextConfiguration {
        @Bean
        StudentController studentController() {
            return new StudentController();
        }
    }

   @MockBean
   StudentController studentController;

   @Mock
   private IStudentRepository studentRepository;

   private final Long studentId = 1L;
   final Student expectStudent = new StudentBuilder().withFirstName("Truc")
           .withLastName("Nguyen")
           .withAge(23)
           .build();

   @Test
    public void getById_ValidId_Success()
   {
//       Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(expectStudent));

       Mockito.doReturn(Optional.of(expectStudent)).when(studentRepository).findById(studentId);
       ResponseEntity<Student> response = studentController.getById(studentId);

       Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
       Assert.assertEquals(expectStudent, response.getBody());
   }

}
