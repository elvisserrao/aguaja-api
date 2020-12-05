import http from "./http-common";

const ClientService = {
  getAll: () => {
    return http.get("/client");
  },

  get: (id) => {
    return http.get(`/client/${id}`);
  },

  create: (data) => {
    return http.post("/client", data);
  },

  update: (id, data) => {
    return http.put(`/client/${id}`, data);
  },

  remove: (id) => {
    return http.delete(`/client/${id}`);
  },

  removeAll: () => {
    return http.delete(`/client`);
  },
};

export default ClientService;
