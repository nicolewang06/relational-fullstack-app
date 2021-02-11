import React, { useEffect, useState } from 'react';
import axios from 'axios';

function Students() {
    const [students, setStudents] = useState(null);
    
    async function getStudents() {
        try{
            const res = await axios.get('http://localhost:8080/students');
            setStudents(res.data);
        } catch(e) {
            console.error(e, e.message);
        }
    }
    
    useEffect(() => {
        //console.log('hello');
        getStudents();
    }, [])

    const [form, setForm] = useState(null);

    function handleChange(e) {
        const { name, value } = e.target;
        setForm( { ...form, [name]: value } );
        // ...form is indicating take these values and put it here (tack on)
    }

    function handleSubmit(e) {
        e.preventDefault();
        createStudent();
    }

    async function createStudent() {
        try{
            const res = await axios.post('http://localhost:8080/students', form);
            // form (payload) is the body of the request
            //console.log(res.data)
            setStudents([...students, res.data]);
        } catch(e) {
            console.error(e, e.message);
        }
    }

    const [selectedStudent, setSelectedStudent] = useState(null);
    //const [editForm, setEditForm] = useState(null);

    function selectStudent(student) {
        setSelectedStudent(student)
    }

    function handleEditChange(e) {
        const { name, value } = e.target;
        setSelectedStudent({ ...selectedStudent, [name]: value });
    } 

    async function handleEditSubmit(e) {
        e.preventDefault();
        try{
            const res = await axios.patch('http://localhost:8080/students', selectedStudent);
            console.log(res.data);
            getStudents();
        } catch(e) {
            console.error(e, e.message);
        }
    } 

    async function deleteStudent(studentId) {
        try {
          const res = await axios.delete('http://localhost:8080/students/' + studentId);
          console.log(res.data);
          getStudents();
        } catch(e) {
          console.error(e, e.message);
        }
      }

    return(
        <div>
            { students && students.map(student => <Student student={ student } selectStudent={ selectStudent } deleteStudent={ deleteStudent } />)}

            <div>
                <h2>Enroll new student!</h2>
                <form
                    onChange={ (e) => handleChange(e) }
                    onSubmit={ (e) => handleSubmit(e) } >
                    <label>
                        First Name:
                    <input type="text" name="firstName" /> 
                    </label>
                    <label>
                        Last Name:
                        <input type="text" name="lastName" />
                    </label>
                    <label>
                        Age:
                        <input type="text" name="age" />
                    </label>
                    <label>
                        Grade Level:
                        <input type="text" name="grade" />
                    </label>
                    <label>
                        School Name:
                        <input type="text" name="schoolName" />
                    </label>
                    <input type="submit" value="Enroll student" />
                </form>

                { selectedStudent && <form
                    onChange={ (e) => handleEditChange(e) }
                    onSubmit={ (e) => handleEditSubmit(e) } >
                    <label>
                        First Name:
                        <input type="text" name="firstName" defaultValue={ selectedStudent.firstName } />
                    </label>
                    <label>
                        Last Name:
                        <input type="text" name="lastName" defaultValue={ selectedStudent.lastName }/>
                    </label>
                    <label>
                        Age:
                        <input type="text" name="age" defaultValue={ selectedStudent.age }/>
                    </label>
                    <label>
                        Grade Level:
                        <input type="text" name="grade" defaultValue={ selectedStudent.grade }/>
                    </label>
                    <label>
                        School Name:
                        <input type="text" name="schoolName" defaultValue={ selectedStudent.schoolName }/>
                    </label>
                    <input type="submit" value="Edit student information" />
                </form> }
            </div>
        </div>
    )
}

function Student({ student, selectStudent, deleteStudent }) {
    return (
      <div key={ student.id }>
        <h3>The students full name is <span className="first-name">{ student.firstName }</span> <span className="last-name">{ student.lastName }</span></h3>
        <h6>They are currently in grade <span className="grade-level">{ student.grade }</span></h6>
        <button onClick={ () => selectStudent(student) }>Edit student</button>
        <button onClick={ () => deleteStudent(student.id) }>Delete Student</button>
      </div>
    )
  }

export default Students;