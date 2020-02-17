package ro.andrei.jersey.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.NotFoundException;
import ro.andrei.jersey.repository.dao.StudentDAO;
import ro.andrei.jersey.rest.dto.Address;
import ro.andrei.jersey.rest.dto.Student;

@Stateless
public class StudentService {

    @EJB
    private StudentDAO studentDAO;

    public List<Student> getAll() {
        return studentDAO.getAll().stream().map(student -> Student.builder()
                .id(student.getId())
                .location(student.getLocation())
                .name(student.getName())
                .address(student.getAddress() == null ? null : Address.builder()
                        .id(student.getAddress().getId())
                        .address(student.getAddress().getAddress()).build())
                .build()).collect(Collectors.toList());
    }

    public Student getById(Long id) {
        ro.andrei.jersey.repository.entity.Student student = studentDAO.getById(id);
        if(student == null) {
            throw new NotFoundException();
        }
        return Student.builder()
                .id(student.getId())
                .name(student.getName())
                .location(student.getLocation())
                .address(student.getAddress() == null ? null : Address.builder()
                        .id(student.getAddress().getId())
                        .address(student.getAddress().getAddress()).build()).build();
    }

    public void createStudent(Student student) {
        studentDAO.create(ro.andrei.jersey.repository.entity.Student.builder()
                .location(student.getLocation())
                .name(student.getName())
                .address(student.getAddress() == null ? null : ro.andrei.jersey.repository.entity.Address.builder()
                        .address(student.getAddress().getAddress()).build()).build());
    }

    public void deleteById(Long id) {
        ro.andrei.jersey.repository.entity.Student student = studentDAO.getById(id);
        if(student != null) {
            studentDAO.delete(student);
        }
    }
}
