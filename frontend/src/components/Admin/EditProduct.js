import React, { useState, useEffect } from "react";
import ProductService from "../../services/product.service";

const Product = (props) => {
  const initialProductState = {
    id: null,
    name: "",
    description: "",
    liters: "",
    urlImage: "",
  };

  const [currentProduct, setCurrentProduct] = useState(initialProductState);
  const [message, setMessage] = useState("");

  const getProduct = (id) => {
    ProductService.get(id)
      .then((response) => {
        setCurrentProduct(response.data);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  useEffect(() => {
    getProduct(props.match.params.id);
  }, [props.match.params.id]);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setCurrentProduct({ ...currentProduct, [name]: value });
  };

  const updatePublished = () => {
    var data = {
      id: currentProduct.id,
      name: currentProduct.name,
      description: currentProduct.description,
      liters: currentProduct.liters,
      urlImage: currentProduct.urlImage,
    };

    ProductService.update(currentProduct.id, data)
      .then((response) => {
        setCurrentProduct({ ...currentProduct });
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const updateProduct = () => {
    ProductService.update(currentProduct.id, currentProduct)
      .then((response) => {
        console.log(response.data);
        setMessage("O produto foi atualizado com sucesso!");
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const deleteProduct = () => {
    ProductService.remove(currentProduct.id)
      .then((response) => {
        console.log(response.data);
        props.history.push("/product");
      })
      .catch((e) => {
        console.log(e);
      });
  };

  return (
    <div>
      {currentProduct ? (
        <div className="edit-form">
          <h4>Produto</h4>
          <form>
            <div className="form-group">
              <label htmlFor="name">Nome</label>
              <input
                type="text"
                className="form-control"
                id="name"
                name="name"
                value={currentProduct.name}
                onChange={handleInputChange}
              />
            </div>
            <div className="form-group">
              <label htmlFor="description">Descrição</label>
              <input
                type="text"
                className="form-control"
                id="description"
                name="description"
                value={currentProduct.description}
                onChange={handleInputChange}
              />
            </div>
            <div className="form-group">
              <label htmlFor="liters">Litros</label>
              <input
                type="text"
                className="form-control"
                id="liters"
                name="liters"
                value={currentProduct.liters}
                onChange={handleInputChange}
              />
            </div>
            <div className="form-group">
              <label htmlFor="urmImage">Url da Imagem</label>
              <input
                type="text"
                className="form-control"
                id="urmImage"
                name="urmImage"
                value={currentProduct.urlImage}
                onChange={handleInputChange}
              />
            </div>
          </form>

          <button
            className="badge badge-primary mr-2"
            onClick={() => updatePublished()}
          >
            Atualizar
          </button>

          <button className="badge badge-danger mr-2" onClick={deleteProduct}>
            Deletar
          </button>

          <button
            type="submit"
            className="badge badge-success"
            onClick={updateProduct}
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

export default Product;
