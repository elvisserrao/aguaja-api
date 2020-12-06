import React, { useState, useEffect } from "react";
import ProductService from "../../services/product.service";
import { Link } from "react-router-dom";

const ProductsList = () => {
  const [products, setProducts] = useState([]);
  const [currentProduct, setCurrentProduct] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);
  const [searchName, setSearchName] = useState("");

  useEffect(() => {
    retrieveProducts();
  }, []);

  const onChangeSearchName = (e) => {
    const searchName = e.target.value;
    setSearchName(searchName);
  };

  const retrieveProducts = () => {
    ProductService.getAll()
      .then((response) => {
        setProducts(response.data);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const refreshList = () => {
    retrieveProducts();
    setCurrentProduct(null);
    setCurrentIndex(-1);
  };

  const setActiveProduct = (product, index) => {
    setCurrentProduct(product);
    setCurrentIndex(index);
  };

  const removeAllProducts = () => {
    ProductService.removeAll()
      .then((response) => {
        console.log(response.data);
        refreshList();
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const findByName = () => {
    ProductService.findByName(searchName)
      .then((response) => {
        setProducts(response.data);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  return (
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
        <h4>Lista de produtos</h4>

        <ul className="list-group">
          {products &&
            products.map((product, index) => (
              <li
                className={
                  "list-group-item " + (index === currentIndex ? "active" : "")
                }
                onClick={() => setActiveProduct(product, index)}
                key={index}
              >
                {product.name}
              </li>
            ))}
        </ul>

        <button
          className="m-3 btn btn-sm btn-danger"
          onClick={removeAllProducts}
        >
          Remover todos
        </button>
      </div>
      <div className="col-md-6">
        {currentProduct ? (
          <div>
            <h4>Produtos</h4>
            <div>
              <label>
                <strong>Nome:</strong>
              </label>{" "}
              {currentProduct.name}
            </div>
            <div>
              <label>
                <strong>Descrição:</strong>
              </label>{" "}
              {currentProduct.description}
            </div>
            <div>
              <label>
                <strong>Litros:</strong>
              </label>{" "}
              {currentProduct.liters}
            </div>
            <div>
              <label>
                <strong>Url da imagem:</strong>
              </label>{" "}
              {currentProduct.urlImage}
            </div>

            <Link
              to={"admin/products/" + currentProduct.id}
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
  );
};

export default ProductsList;
