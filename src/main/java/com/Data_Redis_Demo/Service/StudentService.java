package com.Data_Redis_Demo.Service;

import com.Data_Redis_Demo.Entity.Student;
import com.Data_Redis_Demo.Repository.StudentRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@EnableCaching
public class StudentService {

    @Autowired
    StudentRepositoryImpl studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.saveStudent(student);
    }

    public Map<Integer, Student> getAllStudents() {
        return studentRepository.getAllStudent();

    }

   // @Cacheable(value = "Student", key = "#id")
    public Student getStudent(int id) {
        return studentRepository.getStudent(id);
    }

    @CacheEvict(key = "#id", value = "Student")
    public void deleteStudent(int id) {
        studentRepository.deleteStudent(id);
    }

    @CachePut(key = "#id", value = "Student")
    public Student updateStudent(int id, Student student) {
        Student student1 = studentRepository.getStudent(id);
        student1.setId(student.getId());
        student1.setName(student.getName());
        student1.setMarks(student.getMarks());
        studentRepository.updateStudent(id, student1);
        return student1;
    }
}