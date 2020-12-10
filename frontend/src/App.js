import React, { useState, useEffect } from "react";
import { Switch, Route, Link } from "react-router-dom";

import AuthService from "./services/auth.service";

import Home from "./components/Home";
import Profile from "./components/Profile";
import Login from "./components/Login";
import Register from "./components/Register";
import Edit from "./components/Edit";
import AddProduct from "./components/Admin/AddProduct";
import ProductsList from "./components/Admin/ProductsList";
import EditProduct from "./components/Admin/EditProduct";
import AddStock from "./components/Seller/AddStock";
import EditStock from "./components/Seller/EditStock";
import StocksList from "./components/Seller/StocksList";
import OrdersList from "./components/Seller/OrdersList";

import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

const App = () => {
  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    if (user) {
      setCurrentUser(user);
    }
  }, []);

  const logOut = () => {
    AuthService.logout();
  };

  return (
    <div>
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <Link to={"/"} className="navbar-brand">
          Aguaja
        </Link>

        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/home"} className="nav-link">
              Pagina Inicial
            </Link>
          </li>
        </div>

        {currentUser && currentUser.admin && (
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/products"} className="nav-link">
                Lista de Produtos
              </Link>
            </li>
          </div>
        )}

        {currentUser ? (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/profile"} className="nav-link">
                Perfil
              </Link>
            </li>
            <li className="nav-item">
              <a href="/login" className="nav-link" onClick={logOut}>
                Sair
              </a>
            </li>
          </div>
        ) : (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/login"} className="nav-link">
                Entrar
              </Link>
            </li>

            <li className="nav-item">
              <Link to={"/register"} className="nav-link">
                Cadastrar
              </Link>
            </li>
          </div>
        )}
      </nav>

      <div className="container mt-3">
        <Switch>
          <Route exact path={["/", "/home"]} component={Home} />
          <Route exact path="/profile" component={Profile} />
          <Route exact path="/login" component={Login} />
          <Route exact path="/register" component={Register} />
          <Route exact path="/clients/:id" component={Edit} />
          <Route exact path="/admin/product/new" component={AddProduct} />
          <Route exact path="/admin/products" component={ProductsList} />
          <Route exact path="/admin/products/:id" component={EditProduct} />
          <Route exact path="/seller/stock/new" component={AddStock} />
          <Route exact path="/seller/stocks/:id" component={EditStock} />
          <Route exact path="/seller/stocks/" component={StocksList} />
          <Route exact path="/seller/orders/" component={OrdersList} />
        </Switch>
      </div>
    </div>
  );
};

export default App;
