import React, { useState, useEffect } from "react";
import StockService from "../../services/stock.service";

const EditStock = (props) => {
  const initialStockState = {
    id: null,
    cost_price: "",
    cost_sell: "",
    cost_sellentry_date: "",
    quantity: "",
    product_id: "",
    seller_id: "",
  };
  const [currentStock, setCurrentStock] = useState(initialStockState);
  const [message, setMessage] = useState("");

  const getStock = (id) => {
    StockService.get(id)
      .then((response) => {
        setCurrentStock(response.data);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  useEffect(() => {
    getStock(props.match.params.id);
  }, [props.match.params.id]);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setCurrentStock({ ...currentStock, [name]: value });
  };

  const updatePublished = () => {
    var data = {
      id: currentStock.id,
      cost_price: currentStock.cost,
      cost_sell: currentStock.cost_sell,
      entry_date: currentStock.entry_date,
      quantity: currentStock.quantity,
      product_id: currentStock.product_id,
      seller_id: currentStock.seller_id,
    };

    StockService.update(currentStock.id, data)
      .then((response) => {
        setCurrentStock({ ...currentStock });
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const updateStock = () => {
    StockService.update(currentStock.id, currentStock)
      .then((response) => {
        console.log(response.data);
        setMessage("O estoque foi atualizado com sucesso!");
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const deleteStock = () => {
    StockService.remove(currentStock.id)
      .then((response) => {
        console.log(response.data);
        props.history.push("/stock");
      })
      .catch((e) => {
        console.log(e);
      });
  };

  return (
    <div>
      {currentStock ? (
        <div className="edit-form">
          <h4>Estoque</h4>
          <form>
            <div className="form-group">
              <label htmlFor="description">Descrição</label>
              <input
                type="text"
                className="form-control"
                id="description"
                name="description"
                value={currentStock.description}
                onChange={handleInputChange}
              />
            </div>
            <div className="form-group">
            <label htmlFor="cost_price">Preço de custo</label>
            <input
              type="text"
              className="form-control"
              id="cost_price"
              required
              value={currentStock.cost_price}
              onChange={handleInputChange}
              name="cost_price"
            />
          </div>

          <div className="form-group">
            <label htmlFor="cost_sell">Preço de venda</label>
            <input
              type="text"
              className="form-control"
              id="cost_sell"
              value={currentStock.cost_sell}
              onChange={handleInputChange}
              name="cost_sell"
            />
          </div>

          <div className="form-group">
            <label htmlFor="entry_date">Data de entrada</label>
            <input
              type="text"
              className="form-control"
              id="entry_date"
              value={currentStock.entry_date}
              onChange={handleInputChange}
              name="entry_date"
            />
          </div>

          <div className="form-group">
            <label htmlFor="quantity">Quantidade</label>
            <input
              type="text"
              className="form-control"
              id="quantity"
              value={currentStock.quantity}
              onChange={handleInputChange}
              name="quantity"
            />
          </div>

          <div className="form-group">
            <label htmlFor="product_id">Produto</label>
            <input
              type="text"
              className="form-control"
              id="product_id"
              value={currentStock.product_id}
              onChange={handleInputChange}
              name="product_id"
            />
          </div>

          <div className="form-group">
            <label htmlFor="seller_id">Vendedor</label>
            <input
              type="text"
              className="form-control"
              id="seller_id"
              value={currentStock.seller_id}
              onChange={handleInputChange}
              name="seller_id"
            />
          </div>
          </form>

          <button
            className="badge badge-primary mr-2"
            onClick={() => updatePublished()}
          >
            Atualizar
          </button>

          <button className="badge badge-danger mr-2" onClick={deleteStock}>
            Deletar
          </button>

          <button
            type="submit"
            className="badge badge-success"
            onClick={updateStock}
          >
            Atualizar
          </button>
          <p>{message}</p>
        </div>
      ) : (
        <div>
          <br />
          <p>Por favor clique em um produto...</p>
        </div>
      )}
    </div>
  );
};

export default EditStock;
