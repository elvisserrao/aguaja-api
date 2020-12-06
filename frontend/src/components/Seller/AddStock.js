import React, { useState } from "react";
import StockService from "../../services/stock.service";

const AddStock = () => {
  const initialStockState = {
    id: null,
    cost_price: 0,
    cost_sell: 0,
    entry_date: Date.now(),
    quantity: 0,
    product_id: "",
    seller_id: "",
  };
  const [stock, setStock] = useState(initialStockState);
  const [submitted, setSubmitted] = useState(false);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setStock({ ...stock, [name]: value });
  };

  const saveStock = () => {
    var data = {
      cost_price: stock.cost,
      cost_sell: stock.cost_sell,
      entry_date: stock.entry_date,
      quantity: stock.quantity,
      product_id: stock.product_id,
      seller_id: stock.seller_id,
    };

    StockService.create(data)
      .then((response) => {
        setStock({
          id: response.data.id,
          cost_price: response.data.cost_price,
          cost_sell: response.data.cost_sell,
          entry_date: response.data.entry_date,
          quantity: response.data.quantity,
          stock_id: response.data.stock_id,
          seller_id: response.data.seller_id,
        });
        setSubmitted(true);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const newStock = () => {
    setStock(initialStockState);
    setSubmitted(false);
  };

  return (
    <div className="submit-form">
      {submitted ? (
        <div>
          <h4>You submitted successfully!</h4>
          <button className="btn btn-success" onClick={newStock}>
            Adicionar
          </button>
        </div>
      ) : (
        <div>
          <div className="form-group">
            <label htmlFor="cost_price">Preço de custo</label>
            <input
              type="text"
              className="form-control"
              id="cost_price"
              required
              value={stock.cost_price}
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
              required
              value={stock.cost_sell}
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
              required
              value={stock.entry_date}
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
              required
              value={stock.quantity}
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
              required
              value={stock.product_id}
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
              required
              value={stock.seller_id}
              onChange={handleInputChange}
              name="seller_id"
            />
          </div>

          <button onClick={saveStock} className="btn btn-success">
            Salvar
          </button>
        </div>
      )}
    </div>
  );
};

export default AddStock;
