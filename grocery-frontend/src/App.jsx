import CategoryComponent from './components/CategoryComponent';
import FooterComponent from './components/FooterComponent';
import GroceryItemComponent from './components/GroceryItemComponent';
import HeaderComponent from './components/HeaderComponent';
import ListCategoryComponent from './components/ListCategoryComponent';
import ListGroceryComponent from './components/ListGroceryComponent'
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import RegisterComponent from './components/RegisterComponent';
import LoginComponent from './components/LoginComponent';
import { isUserLoggedIn } from './services/AuthenticationService';

function App() {

  function AuthenticatedRoute({children}){
    const isAuthentic = isUserLoggedIn();

    if(isAuthentic){
      return children;
    }

    return <Navigate to="/" />
  }

  return (
    <>
        <BrowserRouter>
            <HeaderComponent />
            <Routes>
                <Route path='/' element= {<LoginComponent />}></Route>
                <Route path='/register' element={<RegisterComponent />}></Route>
                <Route path='/login' element={<LoginComponent />}></Route>
                <Route path='/grocery-items' element={
                  <AuthenticatedRoute>
                    <ListGroceryComponent />
                  </AuthenticatedRoute>}>
                </Route>
                <Route path='/add-grocery-item' element={
                  <AuthenticatedRoute>
                    <GroceryItemComponent />
                  </AuthenticatedRoute>}>  
                </Route>
                <Route path='/edit-grocery-item/:id' element={
                  <AuthenticatedRoute>
                    <GroceryItemComponent />
                  </AuthenticatedRoute>}>
                </Route>
                <Route path='/category-items' element={
                  <AuthenticatedRoute>
                    <ListCategoryComponent />
                  </AuthenticatedRoute>}>
                </Route>
                <Route path='/add-category-item' element={
                  <AuthenticatedRoute>
                    <CategoryComponent />
                  </AuthenticatedRoute>}>
                </Route>
                <Route path='/edit-category-item/:id' element={
                  <AuthenticatedRoute>
                    <CategoryComponent />
                  </AuthenticatedRoute>}>
                </Route>
            </Routes>
            <FooterComponent />
        </BrowserRouter>
    </>
  );
}

export default App
