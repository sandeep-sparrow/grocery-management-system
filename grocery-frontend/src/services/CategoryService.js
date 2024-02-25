import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/category';

export function getAllCategory(){
    return axios.get(REST_API_BASE_URL + "/getAll");
}

export function getCategoryById(categoryId){
    return axios.get(REST_API_BASE_URL + "/" + categoryId);
}

export function addCategory(category) {
    return axios.post(REST_API_BASE_URL + "/create", category);
}

export function deleteCategory(categoryId) {
    return axios.delete(REST_API_BASE_URL + "/delete/" + categoryId);
}

export function updateCategory(categoryId, category) {
    return axios.put(REST_API_BASE_URL + "/update/" + categoryId, category);
}