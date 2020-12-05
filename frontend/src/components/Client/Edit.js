import React, { useState, useEffect } from "react";
import ClientService from "../../services/client.service";

const Client = (props) => {
  const initialClientState = {
    id: null,
    name: "",
    email: "",
    gender: 0,
    login: "",
    password: "",
  };
  const [currentClient, setCurrentClient] = useState(initialClientState);
  const [message, setMessage] = useState("");

  const getClient = (id) => {
    ClientService.get(id)
      .then((response) => {
        setCurrentClient(response.data);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  useEffect(() => {
    getClient(props.match.params.id);
  }, [props.match.params.id]);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setCurrentClient({ ...currentClient, [name]: value });
  };

  const updatePublished = (status) => {
    var data = {
      id: currentClient.id,
      name: currentClient.name,
      email: currentClient.email,
      gender: currentClient.gender,
      login: currentClient.login,
      password: currentClient.password,
      published: status,
    };

    ClientService.update(currentClient.id, data)
      .then((response) => {
        setCurrentClient({ ...currentClient, published: status });
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const updateClient = () => {
    ClientService.update(currentClient.id, currentClient)
      .then((response) => {
        console.log(response.data);
        setMessage("The client was updated successfully!");
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const deleteClient = () => {
    ClientService.remove(currentClient.id)
      .then((response) => {
        console.log(response.data);
        props.history.push("/client");
      })
      .catch((e) => {
        console.log(e);
      });
  };

  return (
    <div>
      {currentClient ? (
        <div className="edit-form">
          <h4>Client</h4>
          <form>
            <div className="form-group">
              <label htmlFor="name">Name</label>
              <input
                type="text"
                className="form-control"
                id="name"
                name="name"
                value={currentClient.name}
                onChange={handleInputChange}
              />
            </div>
            <div className="form-group">
              <label htmlFor="email">Email</label>
              <input
                type="text"
                className="form-control"
                id="email"
                name="email"
                value={currentClient.email}
                onChange={handleInputChange}
              />
            </div>
            <div className="form-group">
              <label htmlFor="gender">Gender</label>
              <input
                type="text"
                className="form-control"
                id="gender"
                name="gender"
                value={currentClient.gender}
                onChange={handleInputChange}
              />
            </div>
            <div className="form-group">
              <label htmlFor="login">Login</label>
              <input
                type="text"
                className="form-control"
                id="login"
                name="login"
                value={currentClient.login}
                onChange={handleInputChange}
              />
            </div>
            <div className="form-group">
              <label htmlFor="password">Password</label>
              <input
                type="text"
                className="form-control"
                id="password"
                name="password"
                value={currentClient.password}
                onChange={handleInputChange}
              />
            </div>

            <div className="form-group">
              <label>
                <strong>Status:</strong>
              </label>
              {currentClient.published ? "Published" : "Pending"}
            </div>
          </form>

          {currentClient.published ? (
            <button
              className="badge badge-primary mr-2"
              onClick={() => updatePublished(false)}
            >
              UnPublish
            </button>
          ) : (
            <button
              className="badge badge-primary mr-2"
              onClick={() => updatePublished(true)}
            >
              Publish
            </button>
          )}

          <button className="badge badge-danger mr-2" onClick={deleteClient}>
            Delete
          </button>

          <button
            type="submit"
            className="badge badge-success"
            onClick={updateClient}
          >
            Update
          </button>
          <p>{message}</p>
        </div>
      ) : (
        <div>
          <br />
          <p>Please click on a Client...</p>
        </div>
      )}
    </div>
  );
};

export default Client;
