package com.example.truc_nguyen_demo.service.teachService;

import com.example.truc_nguyen_demo.exception.ArgumentNotValidException;
import com.example.truc_nguyen_demo.exception.ResourceNotFoundException;
import com.example.truc_nguyen_demo.model.Entity.Class;
import com.example.truc_nguyen_demo.model.Teach;
import com.example.truc_nguyen_demo.model.Entity.Teacher;
import com.example.truc_nguyen_demo.repository.IClassRepository;
import com.example.truc_nguyen_demo.repository.ITeacherRepository;
import com.example.truc_nguyen_demo.service.teacherService.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeachService implements ITeachService {

    @Autowired
    ITeacherRepository teacherRepository;

    @Autowired
    IClassRepository classRepository;

    @Autowired
    TeacherService teacherService;

    @Override
    public Class teach(Teach teach) {

        if(teach.getIdTeacher() == null || teach.getIdClass() == null)
        {
            throw new ArgumentNotValidException("Not valid");
        }
        Optional<Teacher> myTeacher = teacherRepository.findById(teach.getIdTeacher());
        if(!myTeacher.isPresent())
        {
            throw new ResourceNotFoundException("Teacher not found");
        }

        Optional<Class> myClass = classRepository.findById(teach.getIdClass());
        if(!myClass.isPresent())
        {
            throw new ResourceNotFoundException("Class not found");
        }

        return createTeach(myTeacher.get(), myClass.get());
    }

    @Override
    public Class deleteTeach(Teach teach) {
        if(teach.getIdTeacher() == null || teach.getIdClass() == null)
        {
            throw new ArgumentNotValidException("Not valid");
        }
        Optional<Teacher> myTeacher = teacherRepository.findById(teach.getIdTeacher());
        if(!myTeacher.isPresent())
        {
            throw new ResourceNotFoundException("Teacher not found");
        }

        Optional<Class> myClass = classRepository.findById(teach.getIdClass());
        if(!myClass.isPresent())
        {
            throw new ResourceNotFoundException("Class not found");
        }

        myTeacher.get().removeClass(myClass.get());
        teacherRepository.save(myTeacher.get());

        return classRepository.findById(teach.getIdClass()).get();
    }

    private Class createTeach(Teacher teacher, Class clss)
    {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession())
//        {
//            transaction = session.beginTransaction();
//
//            String hql = "INSERT INTO Teacher";
//        }
//        catch (Exception e){
//            if(transaction != null)
//            {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }

        clss.addTeacher(teacher);

        Class myClass = classRepository.save(clss);
        return myClass;
    }
}
