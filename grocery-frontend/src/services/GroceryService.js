import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/grocery-item';

export function getAllGroceryItems() {
    return axios.get(REST_API_BASE_URL + "/getAll");
}

export function getGroceryItem(groceryId) {
    return axios.get(REST_API_BASE_URL + "/" + groceryId);
}

export function addGroceryItem(groceryItem) {
    return axios.post(REST_API_BASE_URL + "/create", groceryItem);
}

export function deleteGroceryItem(groceryId) {
    return axios.delete(REST_API_BASE_URL + "/delete/" + groceryId);
}

export function updateGroceryItem(groceryId, groceryItem) {
    return axios.put(REST_API_BASE_URL + "/update/" + groceryId, groceryItem);
}
