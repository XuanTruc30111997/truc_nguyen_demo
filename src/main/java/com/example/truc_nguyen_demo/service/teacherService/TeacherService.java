package com.example.truc_nguyen_demo.service.teacherService;

import com.example.truc_nguyen_demo.exception.ResourceNotFoundException;
import com.example.truc_nguyen_demo.model.Entity.Teacher;
import com.example.truc_nguyen_demo.repository.ITeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TeacherService implements ITeacherService {

    @Autowired
    ITeacherRepository teacherRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void deleteTeacher(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if(!teacher.isPresent())
        {
            throw new ResourceNotFoundException("Teacher not found");
        }

        Teacher myTeacher = teacher.get();

        myTeacher.removeClassAhihi();

        teacherRepository.delete(myTeacher);
    }

    @Override
    public List<Teacher> findTeacherByFirstNames(Set<String> firstNames) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Teacher> query = cb.createQuery(Teacher.class);
        Root<Teacher> teacher = query.from(Teacher.class);

        Path<String> firstNamePatch = teacher.get("firstName");

        List<Predicate> predicates = new ArrayList<>();
        for (String firstName : firstNames)
        {
            predicates.add(cb.like(firstNamePatch, firstName));
        }

        query.select(teacher).where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query).getResultList();
    }
}