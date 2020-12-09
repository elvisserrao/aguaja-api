import http from "./http-common";

const OrderService = {
  getAll: () => {
    return http.get("/order");
  },

  get: (id) => {
    return http.get(`/order/${id}`);
  },

  create: (data) => {
    return http.post("/order", data);
  },

  update: (id, data) => {
    return http.put(`/order/${id}`, data);
  },

  remove: (id) => {
    return http.delete(`/order/${id}`);
  },

  removeAll: () => {
    return http.delete(`/order`);
  },
};

export default OrderService;
