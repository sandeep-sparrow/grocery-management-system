import FooterComponent from './components/FooterComponent';
import GroceryItemComponent from './components/GroceryItemComponent';
import HeaderComponent from './components/HeaderComponent';
import ListGroceryComponent from './components/ListGroceryComponent'
import { BrowserRouter, Routes, Route } from 'react-router-dom';

function App() {

  return (
    <>
        <BrowserRouter>
            <HeaderComponent />
            <Routes>
                <Route path='/' element= {<ListGroceryComponent />}></Route>
                <Route path='/grocery-items' element={<ListGroceryComponent />}></Route>
                <Route path='/add-grocery-item' element={<GroceryItemComponent />}></Route>
                <Route path='/edit-grocery-item/:id' element={<GroceryItemComponent />}></Route>
            </Routes>
            <FooterComponent />
        </BrowserRouter>
    </>
  );
}

export default App
