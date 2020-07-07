package com.example.truc_nguyen_demo.service.teachService;

import com.example.truc_nguyen_demo.model.Entity.Class;
import com.example.truc_nguyen_demo.model.Teach;
import com.example.truc_nguyen_demo.model.Entity.Teacher;

public interface ITeachService {
    Class teach(Teach teach);
    Class deleteTeach(Teach teach);
}
