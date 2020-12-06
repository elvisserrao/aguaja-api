export default function authHeader() {
  const user = JSON.parse(localStorage.getItem("user"));

  if (user && user.token) {
    return {
      "Content-type": "application/json",
      Authorization: `Bearer ${user.token}`,
    };
  } else {
    return { "Content-type": "application/json" };
  }
}
