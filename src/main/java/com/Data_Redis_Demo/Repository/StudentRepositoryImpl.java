package com.Data_Redis_Demo.Repository;

import com.Data_Redis_Demo.Entity.Student;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class StudentRepositoryImpl {

    private static final String key = "Student";

    private HashOperations hashOperations;
    private RedisTemplate<Integer, Student> redisTemplate;

    public StudentRepositoryImpl(RedisTemplate<Integer, Student> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    public Student saveStudent(Student student) {
        hashOperations.put(key, student.getId(), student);
        return student;
    }

    public Map<Integer, Student> getAllStudent() {
        System.out.println("All from database");
        return hashOperations.entries(key);
    }

    public Student getStudent(int id) {
        System.out.println("Called getStudents from db");
        return (Student) hashOperations.get(key, id);
    }

    public String deleteStudent(int id) {
        hashOperations.delete(key, id);
        return "Student Data Deleted";
    }

    public String updateStudent(int id, Student student) {
        hashOperations.put(key, id, student);
        return "Student Data Updated ";
    }
}
