HW2: ArrayList Student Management System

-----------------------------------------

1) PROJECT OVERVIEW
-------------------
This program is a Student Management System that uses ArrayLists to manage:
- Students
- Courses
- Enrollments (including grades + GPA calculation)

It also demonstrates ArrayList-specific features and integrates Generics throughout.


2) FILES INCLUDED 
----------------------------------------
Core Models

- Student.java
- Course.java
- Enrollment.java

Managers (ArrayList-based)

- StudentManager.java
- CourseManager.java
- EnrollmentManager.java

Reporting & Integration

- ReportGenerator.java

ArrayList Feature Demos

- ArrayListOperationsDemo.java
- ArrayListVsArrayDemo.java
- ArrayListUtils.java
- GenericList.java
- GenericStack.java
- GenericQueue.java
- Pair.java

Main Runner
- StudentManagementSystemMain.java
  Creates managers, seeds sample data, and provides a menu-driven program.
  Also supports a "demo" command to run the demo classes and generic showcase.


3) HOW TO RUN
-------------------------
Open terminal in the folder containing all .java files.

Run:
  java StudentManagementSystemMain


4) HOW TO USE THE PROGRAM (MENU)
--------------------------------
The main menu supports:
1. Add Student
2. Remove Student
3. Find Student
4. List All Students
5. Add Course
6. Enroll Student in Course
7. Assign Grade
8. Calculate Student GPA
9. Generate Reports
10. Exit

Extra:
- Type "demo" at the main menu to run:
  ArrayListOperationsDemo + ArrayListVsArrayDemo + Generic Showcase


5) IMPORTANT DESIGN CHOICES
---------------------------
- Duplicate Prevention:
  StudentManager does not allow duplicate studentId.
  CourseManager does not allow duplicate courseCode.
  EnrollmentManager prevents duplicate (studentId + courseCode + semester) enrollments.

- Capacity Checking:
  EnrollmentManager checks course capacity using CourseManager (if provided).

- GPA Calculation:
  EnrollmentManager.calculateStudentGpa() uses ONLY graded enrollments.
  Ungraded enrollments are ignored for GPA.

- Prerequisite Logic:
  A prerequisite is considered met if the student has a passing grade
  (A/B/C/D) in the prerequisite course.


6) EDGE CASES HANDLED
---------------------
- Empty lists 
- Student/course not found returns null or prints message
- Invalid grade is rejected (only A/B/C/D/F)
- Enrollment drop on invalid ID returns false
- GPA calculation with no graded enrollments returns 0.0


7) WHAT I LEARNED 
-----------------------------------------
- ArrayLists simplify add/remove/resize compared to arrays.
- ArrayList utilities like contains(), indexOf(), subList(), toArray() are powerful.
- Generics enforce type safety and reduce duplicate code.
- Bounded generics and wildcards help write flexible reusable methods:
  - ? extends Number for reading
  - ? super Integer for writing
  - ? for unknown type printing


8) ARRAYLIST VS ARRAY (SUMMARY)
-------------------------------
Arrays:
- Fixed size (hard to grow)
- Remove/insert requires manual shifting and new arrays
- Best when size is known and performance is critical

ArrayLists:
- Dynamic sizing, easy add/remove
- Rich built-in methods (sort, contains, subList, etc.)
- Best for general-purpose collections with changing size
