## Bootcamp DIO e Santander
Projeto final - Java Restful API Santander Dev Week 2024

### Autores

* [Venilton Falvo Jr](https://github.com/falvojr) - Instrutor responsável
* [Pedro Santiago](https://github.com/psantiago20) - Aluno

## Diagrama de Classes

```mermaid
classDiagram
    class User {
        +Client client
        +Account account
        +Card card
        +Feature[] features
        +News[] news
        -float balance
        -String userType
    }

    class Client {
        -String name
        -String phone
        -String document
        -String zip_code
        -String city
        -String country
    }

    class Account {
        -String number
        -String agency
        -float limit
    }

    class Card {
        -String number
        -float limit
    }

    class Feature {
        -String icon
        -String description
    }

    class News {
        -String icon
        -String description
    }

    User "1" *--> "1" Client
    User "1" *--> "1" Account
    User "1" *--> "1" Card
    User "1" *--> "N" Feature
    User "1" *--> "1" News
```