package net.team5.pocketchef.Business;

import net.team5.pocketchef.Business.Objects.RecipeObject;
import net.team5.pocketchef.Database.hsqldb.RecipeHandler;

import java.util.ArrayList;

public class AccessRecipe {

//    private static RecipeHandler recipePersistence;
//    private static ArrayList<RecipeObject> recipes;
//
//    public AccessRecipe() {}
//    {
//        coursePersistence = Services.getCoursePersistence();
//        courses = null;
//        course = null;
//        currentCourse = 0;
//    }

}


//
//public class AccessCourses
//{
//
//    public AccessCourses()
//    {
//        coursePersistence = Services.getCoursePersistence();
//        courses = null;
//        course = null;
//        currentCourse = 0;
//    }
//
//    public AccessCourses(final CoursePersistence coursePersistence) {
//        this();
//        this.coursePersistence = coursePersistence;
//    }
//
//    public List<Course> getCourses()
//    {
//        courses = coursePersistence.getCourseSequential();
//        return Collections.unmodifiableList(courses);
//    }
//
//    public Course getSequential()
//    {
//        String result = null;
//        if (courses == null)
//        {
//            courses = coursePersistence.getCourseSequential();
//            currentCourse = 0;
//        }
//        if (currentCourse < courses.size())
//        {
//            course = (Course) courses.get(currentCourse);
//            currentCourse++;
//        }
//        else
//        {
//            courses = null;
//            course = null;
//            currentCourse = 0;
//        }
//        return course;
//    }
//
//    public Course getRandom(String courseID)
//    {
//        courses = coursePersistence.getCourseRandom(new Course(courseID));
//        currentCourse = 0;
//        if (currentCourse < courses.size())
//        {
//            course = courses.get(currentCourse);
//            currentCourse++;
//        }
//        else
//        {
//            courses = null;
//            course = null;
//            currentCourse = 0;
//        }
//        return course;
//    }
//
//    public Course insertCourse(Course currentCourse)
//    {
//        return coursePersistence.insertCourse(currentCourse);
//    }
//
//    public Course updateCourse(Course currentCourse)
//    {
//        return coursePersistence.updateCourse(currentCourse);
//    }
//
//    public void deleteCourse(Course currentCourse)
//    {
//        coursePersistence.deleteCourse(currentCourse);
//    }
//}
