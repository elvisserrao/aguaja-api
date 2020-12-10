import React, { useState, useEffect } from "react";
import { Button } from "react-bootstrap";
import StockModal from "./Modal";

import AuthService from "../services/auth.service";
import ProductService from "../services/product.service";
import StockService from "../services/stock.service";

const Home = () => {
  const [modalShow, setModalShow] = useState(false);
  const [currentUser, setCurrentUser] = useState([]);
  const [products, setProducts] = useState([]);
  const [product, setProduct] = useState([]);
  const [stocks, setStocks] = useState([]);

  useEffect(() => {
    const user = AuthService.getCurrentUser();
    setCurrentUser(user);

    retrieveProducts();
  }, []);

  const findByProduct = (product) => {
    StockService.getByProduct(product.id)
      .then((response) => {
        console.log(response.data);
        setStocks(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const retrieveProducts = () => {
    ProductService.getAll()
      .then((response) => {
        setProducts(response.data);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  return (
    <>
      <header class="bg-primary py-5 mb-5">
        <div class="container h-100">
          <div class="row h-100 align-items-center">
            <div class="col-lg-12 text-center">
              <h1 class="display-4 text-white mt-5 mb-2 ">
                Aguaja - Levando água mineral até você!
              </h1>
              <br />
              <p class="lead mb-5 text-white-50">
                Disponibilizamos uma plataforma simples e intuitiva para
                comércio de água mineral, mais um conforto ao seu dia-dia.
              </p>
            </div>
          </div>
        </div>
      </header>

      <div class="container">
        <div class="row">
          <div class="col-md-8 mb-5 text-justify">
            <h2>O que nós fazemos?</h2>
            <hr />
            <p>
              Oferecemos o melhor serviço para você poder comprar sua água
              mineral no conforto de sua casa e sem precisar se preocupar com a
              procedência, porque o nosso sistema se encarrega disso pra você!
              Fazemos uma seleção apenas de vendedores que atendam os requisitos
              para manter o nosso padrão de qualidade!
            </p>
            <p>
              Você que se interessa em ser nosso vendedor parceiro, nosso
              sistema irá direcionar para você a demanda de sua região de acordo
              com a distância do cliente para seu estabelecimento. Aquele
              vendedor que aceitar primeiro o pedido terá preferência.
            </p>
            {!currentUser && (
              <a class="btn btn-primary btn-lg" href="/register">
                Cadastre-se já! &raquo;
              </a>
            )}
          </div>
          <div class="col-md-4 mb-5">
            <h2>Contato:</h2>
            <hr />
            <address>
              <strong>Endereço:</strong>
              <br />
              3481 Aguaja Place
              <br />
              Salvador, BA, Brasil
              <br />
            </address>
            <address>
              <abbr title="Phone">Telefone: </abbr>
              (123) 456-7890
              <br />
              <abbr title="Email">Email: </abbr>
              <a href="mailto:#">rh@aguaja.com</a>
            </address>
          </div>
        </div>

        <div class="row">
          {products &&
            products.map((product) => (
              <div class="col-md-4 mb-5">
                <div class="card h-100">
                  <img class="card-img-top" src={product.urlImage} alt=""></img>
                  <div class="card-body text-center">
                    <h4 class="card-title">{`${product.name} - ${product.liters}L`}</h4>
                    <br />
                    <h5 class="card-text">{product.description}</h5>
                  </div>
                  <div class="card-footer text-center">
                    <Button
                      variant="primary"
                      onClick={() => {
                        setProduct(product);
                        findByProduct(product);
                        setModalShow(true);
                      }}
                    >
                      Peça já a sua!
                    </Button>
                  </div>
                </div>
              </div>
            ))}

          <div class="col-md-4 mb-5">
            <div class="card h-100">
              <img
                class="card-img-top"
                src="https://i0.wp.com/www.bittencourtcardoso.com/wp-content/uploads/2016/06/Agua-mineral-20l-serra-maior.png?fit=1000%2C1000"
                alt=""
              ></img>
              <div class="card-body text-center">
                <h4 class="card-title">Água Mineral 20l Serra Maior</h4>
                <br />
                <h5 class="card-text">R$17.50</h5>
              </div>
              <div class="card-footer text-center">
                <a href="/register" class="btn btn-primary">
                  Peça já a sua!
                </a>
              </div>
            </div>
          </div>
          <div class="col-md-4 mb-5">
            <div class="card h-100">
              <img
                class="card-img-top"
                src="https://aguamineralhydrate.com.br/wp-content/uploads/2016/02/Galao-Agua-Mineral-20-litros-sem-gas.jpg"
                alt=""
              ></img>
              <div class="card-body text-center">
                <h4 class="card-title"> Água Mineral 20 Litros Hydrante</h4>
                <br />
                <h5 class="card-text ">R$17.50</h5>
              </div>
              <div class="card-footer text-center">
                <a href="#" class="btn btn-primary">
                  Peça já a sua!
                </a>
              </div>
            </div>
          </div>
          <div class="col-md-4 mb-5">
            <div class="card h-100">
              <img
                class="card-img-top"
                src="https://koch-img.azureedge.net/product/20459-agua-mineral-imperatriz-sem-gas-ret-20l-g.jpg"
                alt=""
              ></img>
              <div class="card-body text-center">
                <h4 class="card-title">Água Mineral 20l Imperatriz</h4>
                <br />
                <h5 class="card-text">R$17.50</h5>
              </div>
              <div class="card-footer text-center">
                <a href="#" class="btn btn-primary">
                  Peça já a sua!
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
      <StockModal
        user={currentUser}
        stocks={stocks}
        show={modalShow}
        onHide={() => setModalShow(false)}
        product={product}
      />
    </>
  );
};

export default Home;
