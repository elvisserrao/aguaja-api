import http from "./http-common";

const ProductService = {
  getAll: () => {
    return http.get("/product");
  },

  get: (id) => {
    return http.get(`/product/${id}`);
  },

  create: (data) => {
    return http.post("/product", data);
  },

  update: (id, data) => {
    return http.put(`/product/${id}`, data);
  },

  remove: (id) => {
    return http.delete(`/product/${id}`);
  },

  removeAll: () => {
    return http.delete(`/product`);
  },
};

export default ProductService;
