import React, { useEffect, useState } from "react"
import { getAllGroceryItems, deleteGroceryItem } from "../services/GroceryService";
import { useNavigate } from "react-router-dom";

export default function ListGroceryComponent() {

    const[groceryList, setGroceryList] = useState([]);

    const navigator = useNavigate();

    useEffect(() => {
        getAllGrocerys()
    }, []);

    function getAllGrocerys(){
        getAllGroceryItems().then((response) => {
            setGroceryList(response.data);
        }).catch(error => {
            console.log(error);
        })
    }

    const addNewGroceryItem = () => {
        navigator("/add-grocery-item");
    };

    function updateGroceryItem(groceryId) {
        navigator(`/edit-grocery-item/${groceryId}`)

    };

    function deleteGroceryItemById(groceryId) {
        deleteGroceryItem(groceryId).then((response) => {
            console.log("Response from server: ", response);
            getAllGrocerys();
            navigator("/grocery-items");
        }).catch((error) => {
            console.log("Error from server: ", error);
        });
    };

  return (
    <div className="container">
        <h2 className="text-centre">List's of Grocery</h2>
        <div style={{ textAlign: "left"}}>
            <button className="btn btn-primary mb-2" onClick={addNewGroceryItem}>Add Grocery Item</button>
        </div>
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
                {groceryList.map((grocery) => {
                    return (
                        <tr key={grocery.groceryId}>
                            <td>{grocery.groceryName}</td>
                            <td>{grocery.groceryDescription}</td>
                            <td>{grocery.groceryQuantity}</td>
                            <td>{grocery.groceryUnit}</td>
                            <td>{grocery.groceryUnitPrice}</td>
                            <td>
                                <button className="btn btn-info mb-2"  style={{marginRight: "4px"}} onClick={() => updateGroceryItem(grocery.groceryId)}>Update</button>
                                <button className="btn btn-danger mb-2" onClick={() => deleteGroceryItemById(grocery.groceryId)}>Delete</button>
                            </td>
                        </tr>
                        )
                })}
            </tbody>
        </table>
    </div>
  );
}
