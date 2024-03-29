import React, { useState } from "react"
import { useFormik } from "formik"
import "../css/LoginRegisterComponent.css"
import { registerAPI } from "../services/AuthenticationService";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';

function RegisterComponent() {

    const [showPassword, setShowPassword] = useState();
    const [registrationsuccess, setRegistrationsuccess] = useState(false);
    const [registrationfailed, setRegistrationfailed] = useState(false);

    const initialValues = {
        name: "",
        username: "",
        email: "",
        password: "",
    };

    function togglePasswordVisibility(){
        setShowPassword(!showPassword);
    }

    const onSubmit = (values) => {
        registerAPI(values).then((response) => {
            setRegistrationsuccess(true);
            setRegistrationfailed(false);
        }).catch((error) => {
            console.log(error);
            setRegistrationfailed(ture);
            setRegistrationsuccess(false);
        });
    };

    const validate = (values) => {
        let errors = {};
        if (!values.name) {
            errors.name = "Name is required";
        } else if (!values.username) {
            errors.username = "Username is required";
        } else if (!values.email) {
            errors.email = "Email is required";
        } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(values.email)){
            errors.email = "Email Address is invalid";
        } else if (!values.password) {
            errors.password = "XXXXXXXX is required";
        }
        return errors;
    };

    const formik = useFormik({
        initialValues: initialValues,
        onSubmit: onSubmit,
        validate: validate,
        validateOnBlur: true,
    });

    return (
        <div className="container">
            <br /> <br />
            <div className="row">
                <div className="col-md-6 offset-md-3">
                    <div className="card" style={{marginBottom: "40px"}}>
                        <div className="card-header">
                            <h3 className="text-center">User Registration Form</h3>
                        </div>
                        <div className="card-body">
                            <form onSubmit={formik.handleSubmit}>
                                <div className="row mb-3">
                                    <label className="col-md-3 control-label">Name</label>
                                    <div className="col-md-9">
                                        <input
                                        placeholder="Enter Name"
                                        type="text"
                                        className="form-control"
                                        id="name"
                                        name="name"
                                        onChange={formik.handleChange}
                                        onBlur={formik.handleBlur}
                                        value={formik.values.name}
                                        />
                                        {formik.touched.name && formik.errors.name ? (
                                            <div className="alert alert-danger mt-2">
                                                {formik.errors.name}
                                            </div>
                                        ) : null}
                                    </div>
                                </div>
                                <div className="row mb-3">
                                    <label className="col-md-3 control-label">UserName</label>
                                    <div className="col-md-9">
                                        <input
                                        placeholder="Enter User Name"
                                        type="text"
                                        className="form-control"
                                        id="username"
                                        name="username"
                                        onChange={formik.handleChange}
                                        onBlur={formik.handleBlur}
                                        value={formik.values.username}
                                        />
                                        {formik.touched.username && formik.errors.username ? (
                                            <div className="alert alert-danger mt-2">
                                                {formik.errors.username}
                                            </div>
                                        ) : null}
                                    </div>
                                </div>
                                <div className="row mb-3">
                                    <label className="col-md-3 control-label">Email</label>
                                    <div className="col-md-9">
                                        <input
                                        placeholder="Enter Email Address"
                                        type="text"
                                        className="form-control"
                                        id="email"
                                        name="email"
                                        onChange={formik.handleChange}
                                        onBlur={formik.handleBlur}
                                        value={formik.values.email}
                                        />
                                        {formik.touched.email && formik.errors.email ? (
                                            <div className="alert alert-danger mt-2">
                                                {formik.errors.email}
                                            </div>
                                        ) : null}
                                    </div>
                                </div>
                                <div className="row mb-3">
                                    <label className="col-md-3 control-label">Password</label>
                                    <div className="col-md-9"  style={{ position: 'relative' }}>
                                        <input
                                        placeholder="Enter your password"
                                        type={showPassword ? "test" : "password"}
                                        className="form-control"
                                        id="password"
                                        name="password"
                                        onChange={formik.handleChange}
                                        onBlur={formik.handleBlur}
                                        value={formik.values.password}
                                        />
                                        {formik.touched.password && formik.errors.password ? (
                                            <div className="alert alert-danger mt-2">
                                                {formik.errors.password}
                                            </div>
                                        ) : null}
                                        <FontAwesomeIcon
                                        icon={showPassword ? faEyeSlash : faEye}
                                        onClick={togglePasswordVisibility}
                                        style={{
                                            position: 'absolute',
                                            right: '20px',
                                            top: '55%',
                                            transform: 'translateY(-50%)',
                                            cursor: 'pointer',
                                        }}
                                        />
                                    </div>
                                </div>
                                <button type="submit" className="btn btn-primary">Register</button>
                                <a href="/login" className="text-center" style={{marginLeft: "20px"}}>Already have an account?</a>
                                <br /> <br />
                                <div> 
                                    {registrationsuccess ? (
                                        <div className="alert alert-success mt-2">
                                            Registration was successful!
                                        </div>
                                    ) : null }
                                    { registrationfailed ? (
                                        <div className="alert alert-danger mt-2">
                                            Registration was not successful!
                                        </div>
                                    ) : null}
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>  
        </div>
    );
}

export default RegisterComponent;