# Projeto Market System

Para acesso ao projeto com deploy realizado no próprio github acesse

https://mustang-roy.github.io/ui-marketsystem/

## Endpoints
---

### Usuários
- /compradores (GET) - [Lista de compradores](https://marketsystem.herokuapp.com/compradores)

- /vendedores (GET) - [Lista de vendedores](https://marketsystem.herokuapp.com/vendedores)

- /comprador?id= (GET) - [Comprador por cpf](https://marketsystem.herokuapp.com/comprador?id=11111)

- /vendedor?id= (GET) - [Vendedor pro cnpj](https://marketsystem.herokuapp.com/vendedor?id=4444)

---

### Produtos

- /produtos (GET) - [Lista de produtos](https://marketsystem.herokuapp.com/produtos)

- /produtos-vendedor?id= (GET) - [Lista de compradores](https://marketsystem.herokuapp.com/produtos-vendedor?id=4444)

---
### Pagamentos

Os campos enviados aos pagamentos são descritos abaixo de cada endpoint no body da requisição

- /pix (POST)- [Lista de compradores](https://marketsystem.herokuapp.com/pix)
~~~ javascript
//Exemplo
{
	"cpfComprador": "11111",
	"cnpjVendedor": "4444",
	"codigosProdutos": [ "produto1", "produto1" ],
}
~~~

- /credito (POST)- [Lista de compradores](https://marketsystem.herokuapp.com/pix)
~~~ javascript
//Exemplo
{
	"cpfComprador": "11111",
	"cnpjVendedor": "4444",
	"codigosProdutos": [ "produto1", "produto1" ],
}
~~~

- /debito (POST)- [Lista de compradores](https://marketsystem.herokuapp.com/pix)
~~~ javascript
//Exemplo
{
	"cpfComprador": "11111",
	"cnpjVendedor": "4444",
	"codigosProdutos": [ "produto1", "produto1" ],
}
~~~

- /pix (POST)- [Lista de compradores](https://marketsystem.herokuapp.com/pix)
~~~ javascript
//Exemplo
{
	"cpfComprador": "11111",
	"cnpjVendedor": "4444",
	"codigosProdutos": [ "produto1", "produto1" ],
  "dateBoleto": "01/01/2030"
}
~~~
---

A definição do CORS da aplicação apenas permite acesso a aplicações com domínio de origem https://mustang-roy.github.io

Demais testes podem ser realizados pelo próprio navegador, POSTMAN e INSOMNIA
