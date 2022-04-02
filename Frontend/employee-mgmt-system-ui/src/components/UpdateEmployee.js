import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router';
import EmployeeService from '../services/EmployeeService';

const UpdateEmployee = () => {

    // here we have to fecth the fields by useParams

    const { id } = useParams();
    const [employee, setEmployee] = useState({
        id: id,
        firstName: "",
        lastName: "",
        emailId: ""
    });

    const navigate = useNavigate() ;

    const handleChange = (e) => {
        const value = e.target.value;
        // here we are setting employees with the existing employees 
        // update will be affect on states also
        setEmployee({ ...employee, [e.target.name]: value });

    }

    useEffect(() => {

        const fetchData = async () => {
            try {
                const response = await EmployeeService.getEmployeeById(id);
                setEmployee(response.data);
            }
            catch (error) {
                console.log(error);
            }
        };
        fetchData();
    }, [])



    const updateEmployee = (e) => {
        e.preventDefault();
        EmployeeService.updateEmployee(employee,id)
        .then((res)=>{
            navigate("/employeeList");
        })
        .catch((error)=>{
            console.log(error);
        })
    }

    return (
        <div className="flex max-w-2xl mx-auto shadow border-b">
            <div className="px-8 py-8">
                <div className="font-thin text-2xl tracking-wider">
                    <h1>Update Employee</h1>
                </div>
                <div className="items-center justify-center h-14 w-full my-4">
                    <label className="block text-gray-600 text-sm font-normal">
                        First Name
                    </label>
                    <input
                        type="text"
                        name="firstName"
                        // to use the state and update here we are using value
                        value={employee.firstName}
                        // events for change 
                        onChange={(e) => handleChange(e)}
                        className="h-10 w-96 border mt-2 px-2 py-2"></input>
                </div>
                {/* last name */}
                <div className="items-center justify-center h-14 w-full my-4">
                    <label className="block text-gray-600 text-sm font-normal">
                        Last Name
                    </label>
                    <input
                        type="text"
                        name="lastName"
                        value={employee.lastName}
                        onChange={(e) => handleChange(e)}
                        className="h-10 w-96 border mt-2 px-2 py-2"></input>
                </div>
                {/* email */}
                <div className="items-center justify-center h-14 w-full my-4">
                    <label className="block text-gray-600 text-sm font-normal">
                        Email
                    </label>
                    <input
                        type="email"
                        name="emailId"
                        value={employee.emailId}
                        onChange={(e) => handleChange(e)}
                        className="h-10 w-96 border mt-2 px-2 py-2"></input>
                </div>

                {/* buttons */}
                <div className="items-center justify-center h-14 w-full my-4 space-x-4 pt-4">

                    {/* here save button will pass fecth data from states to REST API */}
                    <button onClick={updateEmployee} className="rounded text-white font-semibold bg-green-400 hover:bg-green-700 py-2  px-6">
                        Update
                    </button>
                    <button className="rounded text-white font-semibold bg-red-400 hover:bg-red-700 py-2  px-6"
                        onClick={()=> navigate("/employeeList")}
                    >
                        Cancel
                    </button>
                </div>
            </div>
        </div>
    )
}

export default UpdateEmployee