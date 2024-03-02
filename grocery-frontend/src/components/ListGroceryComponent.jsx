import React, { useEffect, useState } from "react"
import { getAllGroceryItems, deleteGroceryItem, getAllGroceryItemsByCategoryId } from "../services/GroceryService";
import { useNavigate } from "react-router-dom";
import { getAllCategory } from "../services/CategoryService";
import "../css/GroceryAndCategory.css"

export default function ListGroceryComponent() {

    const[groceryList, setGroceryList] = useState([]);
    const[categories, setCategories] = useState(["All"]);
    const[selectedCategoryId, setSelectedCategoryId] = useState();

    const navigator = useNavigate();

    useEffect(() => {
        getAllGrocerys();
        getAllCategories();
    }, []);

    useEffect(() => {
        getAllGrocerysByCategoryId();
    }, [selectedCategoryId]);

    function getAllGrocerys(){
        getAllGroceryItems().then((response) => {
            setGroceryList(response.data);
        }).catch(error => {
            console.log(error);
        });
    }

    function getAllCategories(){
        getAllCategory().then((response) => {
            console.log(response.data);
            setCategories(response.data);
        }).catch(error => {
            console.log(error);
        });
    }

    function getAllGrocerysByCategoryId(){
        if(selectedCategoryId != undefined){
            getAllGroceryItemsByCategoryId(selectedCategoryId).then((response) => {
                setGroceryList(response.data);
            }).catch(error => {
                console.log(error);
            });
        }
    }

    const handleCategoryChange = (event) => {
        setSelectedCategoryId(event.target.value);
    };

    const addNewGroceryItem = () => {
        navigator("/add-grocery-item");
    };

    function updateGroceryItem(groceryId) {
        navigator(`/edit-grocery-item/${groceryId}`)

    };

    function deleteGroceryItemById(groceryId) {
        deleteGroceryItem(groceryId).then((response) => {
            getAllGrocerys();
            navigator("/grocery-items");
        }).catch((error) => {
            console.log("Error from server: ", error);
        });
    };

    const listGroceryItems = 
    <>
        {groceryList.map((grocery) => {
        return (
            <tr key={grocery.groceryId}>
                <td>{grocery.groceryName}</td>
                <td>{grocery.groceryDescription}</td>
                <td>{grocery.groceryQuantity} per Unit</td>
                <td>{grocery.groceryUnit}</td>
                <td>{grocery.groceryUnitPrice} {"\u20B9"}</td>
                <td>
                    <button 
                        className="btn btn-info mb-2" 
                        style={{marginRight: "4px"}} 
                        onClick={() => updateGroceryItem(grocery.groceryId)}
                    >
                        Update
                    </button>
                    <button 
                        className="btn btn-danger mb-2" 
                        onClick={() => deleteGroceryItemById(grocery.groceryId)}
                    >
                        Delete
                    </button>
                </td>
            </tr>
        )})}
    </>

    return (
        <div className="container">
            <h2 className="text-centre" style={{fontStyle: "unset"}}>List of Groceries</h2>
            <nav className="navbar navbar-light bg-light justify-content-left">
                <div className="container">
                    <button 
                        className="btn btn-primary mb-2" 
                        onClick={addNewGroceryItem}
                    >
                        Add Grocery Item
                    </button>
                    <div style={{display: "flex", gap: "25px"}}>
                        <label className="form-label" style={{marginTop: "5px"}}>
                            <span className="popping-text" style={{color: "black", fontStyle: "unset", backgroundColor: "pink", borderRadius: "3px"}}>
                                Select Category:
                            </span>
                        </label>
                        <select 
                            value={selectedCategoryId}
                            onChange={handleCategoryChange}
                            name="categoryId"
                            className="form-select mb-2"
                        >
                        {categories.map(category => (
                            <option 
                                key={category.categoryId} 
                                value={category.categoryId}
                                style={{fontFamily: "sans-serif"}}
                            >
                                {category.categoryName}
                            </option>
                        ))}
                        </select>
                    </div>
                </div>
            </nav>
            <table className="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Grocery Name</th>
                        <th>Grocery Description</th>
                        <th>Grocery Quantity</th>
                        <th>Grocery Unit</th>
                        <th>Grocery Unit Price</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {groceryList.length == 0? 
                        <tr><td colSpan="6">No Grocery Items Found</td></tr> : 
                        listGroceryItems
                    }
                </tbody>
            </table>
        </div>
    );
}
