import http from "./http-common";

const StockService = {
  getAll: () => {
    return http.get("/stock");
  },

  get: (id) => {
    return http.get(`/stock/${id}`);
  },

  create: (data) => {
    return http.post("/stock", data);
  },

  update: (id, data) => {
    return http.put(`/stock/${id}`, data);
  },

  remove: (id) => {
    return http.delete(`/stock/${id}`);
  },

  removeAll: () => {
    return http.delete(`/stock`);
  },
};

export default StockService;
