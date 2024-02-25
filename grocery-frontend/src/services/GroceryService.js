import axios from "axios";
import { getToken } from "./RegisterService";

const REST_API_BASE_URL = 'http://localhost:8080/api/grocery-item';

axios.interceptors.request.use(function (config) {
    config.headers.Authorization =  getToken();
    return config;
  }, function (error) {
    return Promise.reject(error);
  });

export function getAllGroceryItems() {
    return axios.get(REST_API_BASE_URL + "/getAll");
}

export function getAllGroceryItemsByCategoryId(categoryId) {
    return axios.get(REST_API_BASE_URL + "/getAll/" + categoryId);
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
