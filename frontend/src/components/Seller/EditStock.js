import React, { useState, useEffect } from "react";
import StockService from "../../services/stock.service";
import ProductService from "../../services/product.service";
import AuthService from "../../services/auth.service";

const EditStock = (props) => {
  const initialStockState = {
    id: null,
    costPrice: "",
    costSell: "",
    entryDate: Date.now(),
    quantity: "",
    productId: "",
    sellerId: "",
  };
  const [products, setProducts] = useState([]);
  const [stock, setStock] = useState(initialStockState);
  const [sellerId, setSellerId] = useState();
  const [currentStock, setCurrentStock] = useState(initialStockState);
  const [message, setMessage] = useState("");

  useEffect(() => {
    retrieveProducts();

    const user = AuthService.getCurrentUser();

    setSellerId(() => user.id);
  }, []);

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
  const updatePublished = () => {
    var data = {
      id: currentStock.id,
      cost_price: currentStock.cost,
      costSell: currentStock.costSell,
      entryDate: currentStock.entryDate,
      quantity: currentStock.quantity,
      productId: currentStock.productId,
      sellerId: sellerId,
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
              <label htmlFor="costPrice">Preço de custo</label>
              <input
                type="text"
                className="form-control"
                id="costPrice"
                required
                value={currentStock.costPrice}
                onChange={handleInputChange}
                name="costPrice"
              />
            </div>

            <div className="form-group">
              <label htmlFor="costSell">Preço de venda</label>
              <input
                type="text"
                className="form-control"
                id="costSell"
                value={currentStock.costSell}
                onChange={handleInputChange}
                name="costSell"
              />
            </div>

            <div className="form-group">
              <label htmlFor="entryDate">Data de entrada</label>
              <input
                type="datatime-local"
                className="form-control"
                id="entryDate"
                value={currentStock.entryDate}
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
                value={currentStock.quantity}
                onChange={handleInputChange}
                name="quantity"
              />
            </div>

            
            <div className="form-group">
              <label htmlFor="productId">Produto</label>
              <select
                value={currentStock.productId}
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
