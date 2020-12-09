import React, { useState, useEffect } from "react";

import { Modal, Button, Table } from "react-bootstrap";
import OrderService from "../services/order.service";

function StockModal(props) {
  useEffect(() => {
    console.log(props.stocks, props.product);
  });

  const createOrder = (stock) => {
    const data = {
      price: stock.price,
      orderStatus: 1,
      date: Date.now(),
      client_id: props.user.id,
      seller_id: stock.seller_id,
      item: {
        stock_id: stock.id,
        quantity: 1,
      },
    };
    OrderService.create(data)
      .then((response) => {
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  return (
    <Modal
      {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">
          Escolha seu produto!
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Table striped bordered hover variant="dark">
          <thead>
            <tr>
              <th>#</th>
              <th>Produto</th>
              <th>Preço</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {props.stocks &&
              props.stocks.map((stock) => (
                <tr>
                  <td>1</td>
                  <td>{props.product.name}</td>
                  <td>R${stock.costPrice}</td>
                  <Button onClick={createOrder} variant="primary">
                    Realizar pedido!
                  </Button>
                </tr>
              ))}
          </tbody>
        </Table>
      </Modal.Body>
      <Modal.Footer>
        <Button onClick={props.onHide}>Close</Button>
      </Modal.Footer>
    </Modal>
  );
}

export default StockModal;
