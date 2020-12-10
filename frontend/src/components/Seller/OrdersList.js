import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import OrderService from "../../services/order.service";
import authService from "../../services/auth.service";

const OrdersList = () => {
  const [orders, setOrders] = useState([]);
  const [currentOrder, setCurrentOrder] = useState(null);
  const [currentUser, setCurrentUser] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);
  const [searchName, setSearchName] = useState("");

  useEffect(() => {
    const user = authService.getCurrentUser();
    setCurrentUser(user);

    retrieveOrders();
  }, []);

  const onChangeSearchName = (e) => {
    const searchName = e.target.value;
    setSearchName(searchName);
  };

  const retrieveOrders = () => {
    OrderService.getAll()
      .then((response) => {
        console.log(response.data);
        setOrders(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const setActiveOrder = (order, index) => {
    setCurrentOrder(order);
    setCurrentIndex(index);
  };

  const AcceptOrder = (order) => {
    const data = {
      ...order,
      orderStatus: 2,
    };

    OrderService.update(order.id, data)
      .then((response) => {
        setCurrentOrder(response.data);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const RejectOrder = () => {};

  const findByName = () => {
    OrderService.findByName(searchName)
      .then((response) => {
        setOrders(response.data);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  return (
    <>
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
          <h4>Lista de pedidos</h4>

          <ul className="list-group">
            {orders &&
              orders.map((order, index) => (
                <li
                  className={
                    "list-group-item " +
                    (index === currentIndex ? "active" : "")
                  }
                  onClick={() => setActiveOrder(order, index)}
                  key={index}
                >
                  {order.items[0].stock.product.name}
                </li>
              ))}
          </ul>
        </div>

        <div className="col-md-6">
          {currentOrder ? (
            <div>
              <h4>Pedido:</h4>
              <div>
                <label>
                  <strong>Produto:</strong>
                </label>{" "}
                {currentOrder.items[0].stock.product.name}
              </div>
              <div>
                <label>
                  <strong>Data:</strong>
                </label>{" "}
                {currentOrder.date}
              </div>
              <div>
                <label>
                  <strong>Custo:</strong>
                </label>{" "}
                R${currentOrder.price}
              </div>
              <div>
                <label>
                  <strong>Status:</strong>
                </label>{" "}
                {currentOrder.orderStatus === "OPEN" && "Em Aberto"}
                {currentOrder.orderStatus === "CONCLUDED" && "Concluído"}
                {currentOrder.orderStatus === "CANCELED" && "Cancelado"}
                {currentOrder.orderStatus === "IN_PROGRESS" && "Em Progresso"}
              </div>
              {currentOrder.orderStatus === "OPEN" && (
                <>
                  <Link
                    onClick={() => AcceptOrder(currentOrder)}
                    className="badge badge-warning"
                  >
                    Aceitar pedido
                  </Link>
                  <br />
                  <Link
                    onClick={() => RejectOrder}
                    className="badge badge-danger"
                  >
                    Rejeitar pedido
                  </Link>
                </>
              )}
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

export default OrdersList;
