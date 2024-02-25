import React from "react"
import { useFormik } from "formik"
import "../css/RegisterComponent.css"
import { registerAPI } from "../services/RegisterService";

function RegisterComponent() {

    const initialValues = {
        name: "",
        username: "",
        email: "",
        password: "",
    };

    const onSubmit = (values) => {
        console.log(values);
        registerAPI(values).then((response) => {
            console.log(response);
            alert("Registration Successful");
        }).catch((error) => {
            console.log(error);
            alert("Registration Failed");
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
                                    <label className="col-md-3 control-label">Name: </label>
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
                                    <label className="col-md-3 control-label">User Name: </label>
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
                                    <label className="col-md-3 control-label">Email: </label>
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
                                    <label className="col-md-3 control-label">Password: </label>
                                    <div className="col-md-9">
                                        <input
                                        placeholder="Enter Name"
                                        type="text"
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
                                    </div>
                                </div>
                                <button type="submit" className="btn btn-primary">Register</button>
                                <br /> <br />
                                <a href="/login" className="text-center">Already have an account?</a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>  
        </div>
    );
}

export default RegisterComponent;