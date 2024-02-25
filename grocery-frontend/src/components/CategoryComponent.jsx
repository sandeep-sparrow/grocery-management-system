import React, { useEffect } from "react"
import { useFormik } from "formik"
import { addCategory, getCategoryById, updateCategory } from "../services/CategoryService";
import { useNavigate, useParams } from "react-router-dom";

export default function CategoryComponent() {

    const navigator = useNavigate();
    const { id } = useParams();

    const initialValues = {
        categoryName: "",
        categoryDescription: "",
    };

    const onSubmit = values => {
        console.log("Saved Category Item to: ", values);
        if(id){
            updateCategory(id, values).then((response) => {
                navigator("/category-items"); 
            }).catch((error) => {
                console.log("Error from server: ", error);
            });
        }else{
            addCategory(values).then((response) => {
                navigator("/category-items");
            }).catch((error) => {
                console.log("Error from server: ", error);
            });
        }
    };

    const validate = values => {
        let errors = {};
        if(!values.categoryName){
            errors.categoryName = "Name is Required";
        }
        if(!values.categoryDescription){
            errors.categoryDescription = "Description is Required";
        }
        return errors;
    };

    const formik = useFormik({
        initialValues: initialValues,
        onSubmit: onSubmit,
        validate: validate,
        validateOnBlur: true,
    });

    function pageTitle(){
        if(id){
            return "Edit Category Item";
        }
        return "Add Category Item";
    };

    useEffect(() => {
        if(id){
            console.log("Category Id: ", id);
            getCategoryById(id).then((response) => {
                formik.setValues(response.data);
            }).catch((error) => {
                console.log("Error from server: ", error);
            })
        }
    }, [id])


    return (
        <div className="container">
            <br /> <br />
            <div className="row">
                <div className="card col-md-6 offset-md-3 offset-md-3">
                    <h2 className="text-center">{pageTitle()}</h2>
                    <div className="card-body" style={{ textAlign: "left" }}>
                        <form onSubmit={formik.handleSubmit}>
                            <div className="form-group mb-2">
                                <label className="form-label">Category Name:</label>
                                <input 
                                    type="text"
                                    name="categoryName"
                                    className="form-control" 
                                    placeholder="Enter category name"
                                    onChange={formik.handleChange}
                                    value={formik.values.categoryName}
                                    onBlur={formik.handleBlur}
                                />
                                {formik.touched.categoryName && formik.errors.categoryName ? (
                                    <div className="error">{formik.errors.categoryName}</div>) : null}
                            </div>
                            <div className="form-group mb-2">
                                <label className="form-label">Category Description:</label>
                                <input 
                                    type="text"
                                    name="categoryDescription"
                                    className="form-control" 
                                    placeholder="Enter category description"
                                    onChange={formik.handleChange}
                                    value={formik.values.categoryDescription}
                                    onBlur={formik.handleBlur}
                                />
                                {formik.touched.categoryDescription && formik.errors.categoryDescription ? (
                                    <div className="error">{formik.errors.categoryDescription}</div>) : null}
                            </div>
                            <button type="submit" className="btn btn-success">
                                Submit
                            </button>
                        </form>
                    </div>
                </div>
            </div>
            <br /> <br />
        </div>
    );
}

