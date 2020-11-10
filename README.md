# Água Já

Projeto desenvolvido na disciplina Projeto Integrador. 

## API RESTfull

API REST para integração de clientes web ou mobile com a plataforma Água Já.

## Instalação

* Requisitos
  - Java 1.8 (JDK 8)
  - IDE (IntelliJ IDEA, Eclipse)
  
* Versões utilizadas 
  - Spring Framework 2.3.5
  - Maven 4.0.0
  - Apache Tomcat 9.0.39
  
### Executando

Abra o projeto utiliando a IDE, em seguida, abra o arquivo `src/main/java/com/aguaja/api/ApiApplication.java`.

Na lateral esquerda do indicador da linha onde se encontra o método `main`, haverá um ícone (play) para executar a aplicação.
Quando executar, a IDE mostrará os logs e se tudo correr bem, o Tomcat estará escutando as requisições no `localhos:8080`.

Para verificar se está tudo em ordem, faça uma requisição `GET` no endpoint `http://localhost:8080/`.
Deverá retornar uma resposta com status `200 OK` e uma string.

## TO-DO

- [ ] Autenticação JWT
- [ ] Configuração com Banco de Dados
- [ ] Criar Models
- [ ] Criar Controllers