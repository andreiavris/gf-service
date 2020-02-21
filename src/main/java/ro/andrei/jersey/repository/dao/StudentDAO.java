package ro.andrei.jersey.repository.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ro.andrei.jersey.repository.entity.Student;

public class StudentDAO {

    @PersistenceContext(unitName = "myPU")
    private EntityManager em;

    public Student getById(Long id) {
        return em.find(Student.class, id);
    }

    public List<Student> getAll() {
        return em.createQuery("select s from Student s").getResultList();
    }

    public void create(Student student) {
        em.persist(student);
    }

    public void delete(Student student) {
        em.remove(student);
    }
}
