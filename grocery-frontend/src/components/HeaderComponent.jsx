import React from "react"
import "../App.css"
import { NavLink, useNavigate } from 'react-router-dom'
import "../css/GroceryAndCategory.css"
import { getLoggedInUser, isUserLoggedIn, logout } from "../services/AuthenticationService"
import "../css/GroceryAndCategory.css"

export default function HeaderComponent() {

    const isAuthentic = isUserLoggedIn();
    const usernameOrEmail = getLoggedInUser();
    const navigator = useNavigate();

    const handlelogout = () => {
      logout();
      navigator("/login");
    }

    return (
      <div>
          <header>
              <nav className="navbar navbar-expand-lg" style={{backgroundColor: "#eec0c8", borderRadius: "10px"}}>
                  <a className="navbar-brand" href="#">
                      <b>Grocery Management System</b>
                  </a>
                  <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                      {isAuthentic &&
                        <li className="nav-item">
                          <NavLink className="nav-link" to="/grocery-items">Groceries</NavLink>
                        </li>
                      }
                      {isAuthentic &&
                        <li className="nav-item">
                        <NavLink className="nav-link" to="/category-items">Categories</NavLink>
                        </li>
                      }
                      {!isAuthentic &&
                        <div style={{marginLeft: "500px", display: "flex"}}>
                        <li className="nav-item">
                          <NavLink className="nav-link" to="/register">Register</NavLink>
                        </li>
                        <li className="nav-item">
                          <NavLink className="nav-link" to="/login">Login</NavLink>
                        </li>
                      </div>
                      }
                      {isAuthentic &&
                        <div style={{marginLeft: "300px", marginTop: "8px", whiteSpace: "nowrap"}}>
                          <li className="nav-item">
                            <span className="popping-text">User: {usernameOrEmail}</span>
                          </li>
                        </div>
                      }
                      {isAuthentic &&
                        <div style={{marginLeft: "50px", display: "flex"}}>
                        <li className="nav-item">
                          <NavLink className="nav-link" to="/login" onClick={handlelogout}>Logout</NavLink>
                        </li>
                      </div>
                      }
                      <li className="nav-item" style={{marginLeft: "30px"}}>
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
