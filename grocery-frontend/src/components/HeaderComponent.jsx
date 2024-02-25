import React from "react"
import "../App.css"
import { NavLink } from 'react-router-dom'
import "../css/GroceryAndCategory.css"

export default function HeaderComponent() {

  return (
    <div>
        <header>
            <nav className="navbar navbar-expand-lg" style={{backgroundColor: "#eec0c8", borderRadius: "10px"}}>
                <a className="navbar-brand" href="#">
                    <b>Grocery Management System</b>
                </a>
                <div className="collapse navbar-collapse" id="navbarNav">
                  <ul className="navbar-nav">
                    <li className="nav-item">
                      <NavLink className="nav-link" to="/grocery-items">Groceries</NavLink>
                    </li>
                    <li className="nav-item">
                      <NavLink className="nav-link" to="/category-items">Categories</NavLink>
                    </li>
                    <div style={{marginLeft: "400px", display: "flex"}}>
                      <li className="nav-item">
                        <NavLink className="nav-link" to="/register">Register</NavLink>
                      </li>
                      <li className="nav-item">
                        <NavLink className="nav-link" to="/login">Login</NavLink>
                      </li>
                      <li className="nav-item">
                        <NavLink className="nav-link" to="/logout">Logout</NavLink>
                      </li>
                    </div>
                    <li className="nav-item" style={{marginLeft: "100px"}}>
                      <a className="nav-link d-flex popping-text"
                        href="https://www.linkedin.com/in/sandeep-prajapati-ab574b8a/" 
                        tabindex="-1" aria-disabled="true" style={{color: "#318CE7"}} >
                        <span style={{marginTop: "3px"}}>LinkedIn</span>
                        <img src="https://img.icons8.com/color/48/000000/linkedin.png" 
                            width="30" height="30" className="d-inline-block align-top" alt="Icon"/>
                      </a>
                    </li>
                  </ul>
                </div>
            </nav>
        </header>
    </div>
  );
}
