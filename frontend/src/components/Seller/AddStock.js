import React, { useEffect, useState } from "react";
import StockService from "../../services/stock.service";
import ProductService from "../../services/product.service";
import AuthService from "../../services/auth.service";

const AddStock = () => {
  const initialStockState = {
    id: null,
    costPrice: "",
    costSell: "",
    entryDate: Date.now(),
    quantity: "",
    productId: 0,
  };

  const [products, setProducts] = useState([]);
  const [stock, setStock] = useState(initialStockState);
  const [sellerId, setSellerId] = useState();
  const [submitted, setSubmitted] = useState(false);

  useEffect(() => {
    retrieveProducts();

    const user = AuthService.getCurrentUser();

    setSellerId(() => user.id);
  }, []);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setStock({ ...stock, [name]: value });
  };

  const retrieveProducts = () => {
    ProductService.getAll()
      .then((response) => {
        setProducts(response.data);
        setStock({
          ...stock,
          productId: response.data[0].id,
        });

        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const saveStock = () => {
    var data = {
      productId: stock.productId,
      quantity: stock.quantity,
      entryDate: stock.entryDate,
      costPrice: stock.costPrice,
      costSell: stock.costSell,
      sellerId: sellerId,
    };
    console.log(data);

    StockService.create(data)
      .then((response) => {
        setStock({
          id: response.data.id,
          costPrice: response.data.costPrice,
          costSell: response.data.costSell,
          entryDate: response.data.entryDate,
          quantity: response.data.quantity,
          productId: response.data.product,
          sellerId: response.data.seller,
        });
        setSubmitted(true);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const newStock = () => {
    setStock({
      ...stock,
      id: null,
      costPrice: "",
      costSell: "",
      entryDate: Date.now(),
      quantity: "",
      productId: products[0].id,
    });
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
            <label htmlFor="costPrice">Preço de custo</label>
            <input
              type="number"
              min="0"
              step="0.1"
              className="form-control"
              id="costPrice"
              required
              value={stock.costPrice}
              onChange={handleInputChange}
              name="costPrice"
            />
          </div>

          <div className="form-group">
            <label htmlFor="costSell">Preço de venda</label>
            <input
              type="number"
              min="0"
              step="0.1"
              className="form-control"
              id="costSell"
              required
              value={stock.costSell}
              onChange={handleInputChange}
              name="costSell"
            />
          </div>

          <div className="form-group">
            <label htmlFor="entryDate">Data de entrada</label>
            <input
              type="datetime-local"
              className="form-control"
              id="entryDate"
              required
              value={stock.entryDate}
              onChange={handleInputChange}
              name="entryDate"
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
            <label htmlFor="productId">Produto</label>
            <select
              value={stock.productId}
              onChange={handleInputChange}
              name="productId"
              required
              className="form-control"
              id="productId"
            >
              {products &&
                products.map((product) => (
                  <option key={product.id} value={product.id}>
                    {product.name}
                  </option>
                ))}
            </select>
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
