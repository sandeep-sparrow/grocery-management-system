import React, { useEffect, useState } from "react"
import { getAllCategory, deleteCategory } from "../services/CategoryService";
import { useNavigate } from "react-router-dom";

export default function ListCategoryComponent() {

    const[categoryList, setCategoryList] = useState([]);

    const navigator = useNavigate();

    useEffect(() => {
        getAllCategories();
    }, []);

    function getAllCategories(){
        getAllCategory().then((response) => {
            setCategoryList(response.data);
        }).catch(error => {
            console.log(error);
        });
    }

    const addNewCategoryItem = () => {
        navigator("/add-category-item");
    };

    function updateCategoryItem(categoryId) {
        navigator(`/edit-category-item/${categoryId}`)
    };  

    function deleteCategoryItem(categoryId) {
        deleteCategory(categoryId).then((response) => {
            getAllCategories();
            navigator("/category-items");
        }).catch((error) => {
            console.log("Error from server: ", error);
        });
    };

    const listCategoryItems = 
    <>
        {categoryList.map((category) => {
        return (
            <tr key={category.categoryId}>
                <td>{category.categoryName}</td>
                <td>{category.categoryDescription}</td>
                <td>
                    <button 
                        className="btn btn-info mb-2" 
                        style={{marginRight: "4px"}} 
                        onClick={() => updateCategoryItem(category.categoryId)}
                    >
                        Update
                    </button>
                    <button 
                        className="btn btn-danger mb-2" 
                        onClick={() => deleteCategoryItem(category.categoryId)}
                    >
                        Delete
                    </button>
                </td>
            </tr>
        )})}
    </>

    return (
        <div className="container">
            <h2 className="text-centre" style={{fontStyle: "unset"}}>List of Categories</h2>
            <nav className="navbar navbar-light bg-light justify-content-left">
                <div className="container">
                    <button 
                        className="btn btn-primary mb-2" 
                        onClick={addNewCategoryItem}
                    >
                        Add Category Item
                    </button>
                </div>
            </nav>
            <table className="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Category Name</th>
                        <th>Category Description</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {categoryList.length == 0? 
                        <tr><td colSpan="6">No Category Items Found</td></tr> : 
                        listCategoryItems
                    }
                </tbody>
            </table>
        </div>
    );
}
