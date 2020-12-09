import React, { useState, useEffect } from "react";
import StockService from "../../services/stock.service";
import { Link } from "react-router-dom";
import OrderService from "../../services/order.service";
import authService from "../../services/auth.service";

const StocksList = () => {
  const [stocks, setStocks] = useState([]);
  const [orders, setOrders] = useState([]);
  const [currentStock, setCurrentStock] = useState(null);
  const [currentUser, setCurrentUser] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);
  const [searchName, setSearchName] = useState("");

  useEffect(() => {
    const user = authService.getCurrentUser();
    setCurrentUser(user);

    retrieveStocks();
    retrieveOpenOrders();
  }, []);

  const onChangeSearchName = (e) => {
    const searchName = e.target.value;
    setSearchName(searchName);
  };

  const retrieveOpenOrders = () => {
    OrderService.getAllOpen()
      .then((response) => {
        console.log(response.data);
        setOrders(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const retrieveStocks = () => {
    StockService.getAll()
      .then((response) => {
        setStocks(response.data);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const refreshList = () => {
    retrieveStocks();
    setCurrentStock(null);
    setCurrentIndex(-1);
  };

  const setActiveStock = (stock, index) => {
    setCurrentStock(stock);
    setCurrentIndex(index);
  };

  const removeAllStocks = () => {
    StockService.removeAll()
      .then((response) => {
        console.log(response.data);
        refreshList();
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const findByName = () => {
    StockService.findByName(searchName)
      .then((response) => {
        setStocks(response.data);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  return (
    <>
      {orders.map((order) => {
        if (order.seller.id == currentUser.id) alert("Você tem um novo pedido");
      })}

      <div className="list row">
        <div className="col-md-8">
          <div className="input-group mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Search by name"
              value={searchName}
              onChange={onChangeSearchName}
            />
            <div className="input-group-append">
              <button
                className="btn btn-outline-secondary"
                type="button"
                onClick={findByName}
              >
                Procurar
              </button>
            </div>
          </div>
        </div>
        <div className="col-md-6">
          <h4>Lista de estoques</h4>

          <ul className="list-group">
            {stocks &&
              stocks.map((stock, index) => (
                <li
                  className={
                    "list-group-item " +
                    (index === currentIndex ? "active" : "")
                  }
                  onClick={() => setActiveStock(stock, index)}
                  key={index}
                >
                  {stock.product.name}
                </li>
              ))}
          </ul>

          <button
            className="m-3 btn btn-sm btn-danger"
            onClick={removeAllStocks}
          >
            Remover todos
          </button>
        </div>

        <div className="col-md-6">
          {currentStock ? (
            <div>
              <h4>Estoque</h4>
              <div>
                <label>
                  <strong>Produto:</strong>
                </label>{" "}
                {currentStock.product.name}
              </div>
              <div>
                <label>
                  <strong>Quantidade:</strong>
                </label>{" "}
                {currentStock.quantity}
              </div>
              <div>
                <label>
                  <strong>Custo:</strong>
                </label>{" "}
                {currentStock.costPrice}
              </div>
              <div>
                <label>
                  <strong>Custo de venda:</strong>
                </label>{" "}
                {currentStock.costSell}
              </div>

              <Link
                to={"/seller/stocks/" + currentStock.id}
                className="badge badge-warning"
              >
                Editar
              </Link>
            </div>
          ) : (
            <div>
              <br />
              <p>Por favor, clique em um produto...</p>
            </div>
          )}
        </div>
      </div>
    </>
  );
};

export default StocksList;
