import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/auth';

export function registerAPI(register){
    return axios.post(REST_API_BASE_URL + "/register", register);
}

export function loginAPI(login){
    return axios.post(REST_API_BASE_URL + "/login", login);
}

export const storeToken = (token) => {
    localStorage.setItem('token', token);
}

export const getToken = () => {
    return localStorage.getItem('token');
}

export const saveLoggedInUser = (username) => {
    //TODO: session storage has the expiration date on contrary to local session, when tab closed all the session data is lost
    sessionStorage.setItem('authenticatedUser', username);
}

export const getLoggedInUser = () => {
    return sessionStorage.getItem('authenticatedUser');
}

export const isUserLoggedIn = () => {
    return sessionStorage.getItem('authenticatedUser') !== null;
}

export const logout = () => {
    sessionStorage.removeItem('authenticatedUser');
    localStorage.removeItem('token');
    window.location.reload(false);
}