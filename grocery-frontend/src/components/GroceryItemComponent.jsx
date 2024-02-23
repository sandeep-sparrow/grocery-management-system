import React, {useEffect} from "react"
import { useFormik } from "formik"
import { addGroceryItem, getGroceryItem, updateGroceryItem } from "../services/GroceryService";
import { useNavigate, useParams } from "react-router-dom";

export default function GroceryItemComponent() {

    const navigator = useNavigate();
    
    const { id } = useParams();

    const initialValues = {
        groceryName: "",
        groceryDescription: "",
        groceryQuantity: "",
        groceryUnit: "",
        groceryUnitPrice: ""
    };

    const onSubmit = values => {
        console.log("Saved Grocery Item tobe: ", values);
        if(id){
            updateGroceryItem(id, values).then((response) => {
                console.log("Response from server: ", response);
                navigator("/grocery-items"); 
            }).catch((error) => {
                console.log("Error from server: ", error);
            });
        }else{
            addGroceryItem(values).then((response) => {
                console.log("Response from server: ", response);
                navigator("/grocery-items");
            }).catch((error) => {
                console.log("Error from server: ", error);
            });
        }
    };

    const validate = values => {
        let errors = {};
        if(!values.groceryName){
            errors.groceryName = "Name is Required";
        }
        if(!values.groceryDescription){
            errors.groceryDescription = "Description is Required";
        }
        if(!values.groceryQuantity){
            errors.groceryQuantity = "Qunatity is Required";
        }
        if(!values.groceryUnit){
            errors.groceryUnit = "No of Unit is Required";
        }
        if(!values.groceryUnitPrice){
            errors.groceryUnitPrice = "Unit Price is Required";
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
            return "Edit Grocery Item";
        }
        return "Add Grocery Item";
    };

    useEffect(() => {
        if(id){
            console.log("Grocery Id: ", id);
            getGroceryItem(id).then((response) => {
                console.log("Response from server: ", response);
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
                            <label className="form-label">Grocery Name:</label>
                            <input 
                                type="text"
                                name="groceryName"
                                className="form-control" 
                                placeholder="Enter grocery name"
                                onChange={formik.handleChange}
                                value={formik.values.groceryName}
                                onBlur={formik.handleBlur}
                            />
                            {formik.touched.groceryName && formik.errors.groceryName ? (
                                <div className="error">{formik.errors.groceryName}</div>) : null}
                        </div>
                        <div className="form-group mb-2">
                            <label className="form-label">Grocery Description:</label>
                            <input 
                                type="text"
                                name="groceryDescription"
                                className="form-control" 
                                placeholder="Enter grocery description"
                                onChange={formik.handleChange}
                                value={formik.values.groceryDescription}
                                onBlur={formik.handleBlur}
                            />
                            {formik.touched.groceryDescription && formik.errors.groceryDescription ? (
                                <div className="error">{formik.errors.groceryDescription}</div>) : null}
                        </div>
                        <div className="form-group mb-2">
                            <label className="form-label">Grocery Quantity:</label>
                            <input 
                                type="number"
                                name="groceryQuantity"
                                className="form-control" 
                                placeholder="Enter grocery quantity"
                                onChange={formik.handleChange}
                                value={formik.values.groceryQuantity}
                                onBlur={formik.handleBlur}
                            />
                            {formik.touched.groceryQuantity && formik.errors.groceryQuantity ? (
                                <div className="error">{formik.errors.groceryQuantity}</div>) : null}
                        </div>
                        <div className="form-group mb-2">
                            <label className="form-label">Grocery Unit:</label>
                            <input 
                                type="number"
                                name="groceryUnit"
                                className="form-control" 
                                placeholder="Enter grocery unit"
                                onChange={formik.handleChange}
                                value={formik.values.groceryUnit}
                                onBlur={formik.handleBlur}
                            />
                            {formik.touched.groceryUnit && formik.errors.groceryUnit ? (
                                <div className="error">{formik.errors.groceryUnit}</div>) : null}
                        </div>
                        <div className="form-group mb-2">
                            <label className="form-label">Grocery Unit Price:</label>
                            <input 
                                type="number"
                                name="groceryUnitPrice"
                                className="form-control" 
                                placeholder="Enter grocery unit price"
                                onChange={formik.handleChange}
                                value={formik.values.groceryUnitPrice}
                                onBlur={formik.handleBlur}
                            />
                            {formik.touched.groceryUnitPrice && formik.errors.groceryUnitPrice ? (
                                <div className="error">{formik.errors.groceryUnitPrice}</div>) : null}
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

