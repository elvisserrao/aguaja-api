import http from "./http-common";

const sellerRegister = (data) => {
  return http.post("/seller/register", data);
};

const clientRegister = (data) => {
  return http.post("/client/register", data);
};

const login = async (data) => {
  const response = await http.post("authenticate", data);
  if (response.data.token) {
    localStorage.setItem("user", JSON.stringify(response.data));
  }
  return response.data;
};

const logout = () => {
  localStorage.removeItem("user");
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

export default {
  clientRegister,
  sellerRegister,
  login,
  logout,
  getCurrentUser,
};
