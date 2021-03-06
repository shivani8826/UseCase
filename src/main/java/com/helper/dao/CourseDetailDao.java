package com.helper.dao;

import com.helper.entity.CourseDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Cacheable;
import java.util.List;

@Repository
public class CourseDetailDao {

    @Autowired
    @Qualifier("hibernate4AnnotatedSessionFactory")
    private SessionFactory sessionFactory;


     public List<Object> getAllCourses()
     {
        Session session=this.sessionFactory.openSession();

        String query="select course_id ,course_name from course_detail where is_active = 1";
        NativeQuery nq = session.createSQLQuery(query);
        List<Object> allCourses = nq.list();

        session.close();
        return allCourses;

    }

    public String getCourse(Integer id){
        Session session = this.sessionFactory.openSession();
        CourseDetail temp = (CourseDetail) session.get(CourseDetail.class,id);
        if(temp==null)
            return "null";
        String course = temp.getCourseName();
        session.close();
        return course;
    }

}
