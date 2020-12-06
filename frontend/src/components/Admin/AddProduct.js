import React, { useState } from "react";
import ProductService from "../../services/product.service";

const AddProduct = () => {
  const initialProductState = {
    id: null,
    name: "",
    description: "",
    liters: 0,
    urlImage: "",
  };
  const [product, setProduct] = useState(initialProductState);
  const [submitted, setSubmitted] = useState(false);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setProduct({ ...product, [name]: value });
  };

  const saveProduct = () => {
    var data = {
      name: product.name,
      description: product.description,
      liters: product.liters,
      urlImage: product.urlImage,
    };

    ProductService.create(data)
      .then((response) => {
        setProduct({
          id: response.data.id,
          name: response.data.name,
          description: response.data.description,
          liters: response.data.liters,
          liters: response.data.urlImage,
        });
        setSubmitted(true);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const newProduct = () => {
    setProduct(initialProductState);
    setSubmitted(false);
  };

  return (
    <div className="submit-form">
      {submitted ? (
        <div>
          <h4>You submitted successfully!</h4>
          <button className="btn btn-success" onClick={newProduct}>
            Adicionar
          </button>
        </div>
      ) : (
        <div>
          <div className="form-group">
            <label htmlFor="name">Nome</label>
            <input
              type="text"
              className="form-control"
              id="name"
              required
              value={product.name}
              onChange={handleInputChange}
              name="name"
            />
          </div>

          <div className="form-group">
            <label htmlFor="description">Descrição</label>
            <input
              type="text"
              className="form-control"
              id="description"
              required
              value={product.description}
              onChange={handleInputChange}
              name="description"
            />
          </div>

          <div className="form-group">
            <label htmlFor="liters">Litros</label>
            <input
              type="text"
              className="form-control"
              id="liters"
              required
              value={product.liters}
              onChange={handleInputChange}
              name="liters"
            />
          </div>

          <div className="form-group">
            <label htmlFor="urlImage">Url da Imagem</label>
            <input
              type="text"
              className="form-control"
              id="urlImage"
              required
              value={product.urlImage}
              onChange={handleInputChange}
              name="urlImage"
            />
          </div>

          <button onClick={saveProduct} className="btn btn-success">
            Salvar
          </button>
        </div>
      )}
    </div>
  );
};

export default AddProduct;
