# 👤 API User

> API REST de gestion des utilisateurs et d'authentification développée avec **Java et Spring Boot**.

`api-user` constitue le **service utilisateur** de l'application Square Games.

Il est indépendant de `square-games` et communique avec celui-ci via **HTTP/REST**.

---

## 🏗️ Architecture

```text
                ┌─────────────────────────┐
                │      🎮 square-games    │
                │        Port 8080        │
                │                         │
                │  Jeux                   │
                │  Parties                │
                │  Interface web          │
                └────────────┬────────────┘
                             │
                          HTTP/REST
                             │
                             ▼
                ┌─────────────────────────┐
                │       👤 api-user       │
                │        Port 8081        │
                │                         │
                │  👤 Utilisateurs        │
                │  🔐 Authentification    │
                │  🎟️ JWT                │
                └────────────┬────────────┘
                             │
                             ▼
                           🗄️ MySQL
```

### 🎯 Responsabilités

`api-user` gère :

* 👤 les utilisateurs ;
* 🔑 les identifiants ;
* 🔐 l'authentification ;
* 🛡️ les rôles ;
* 🎟️ les JWT ;
* ✅ la validation des utilisateurs.

---

## 🛠️ Technologies

![Java](https://img.shields.io/badge/Java-ED8B00?logo=openjdk\&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot\&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?logo=springsecurity\&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql\&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?logo=jsonwebtokens\&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven\&logoColor=white)

---

## 🔐 Authentification

La connexion est réalisée avec un identifiant et un mot de passe.

### 🔑 Endpoint

```http
POST /auth/login
```

### 📤 Exemple de requête

```json
{
  "username": "Alice",
  "password": "motdepasse"
}
```

### 📥 Réponse

Lorsque les identifiants sont valides :

```json
{
  "token": "eyJ..."
}
```

Le token JWT contient notamment :

* 👤 l'utilisateur ;
* 🛡️ son rôle ;
* 🕐 sa date d'émission ;
* ⏳ sa date d'expiration.

---

## 👤 Gestion des utilisateurs

Les utilisateurs sont stockés dans une base **MySQL**.

Une entité utilisateur contient notamment :

```text
id
name
password
role
```

L'identifiant de l'utilisateur est utilisé comme identifiant unique.

---

## 🌐 API REST

| Méthode  | Endpoint            | Fonction                     |
| -------- | ------------------- | ---------------------------- |
| `POST`   | `/users`            | ➕ Créer un utilisateur       |
| `GET`    | `/users/{id}`       | 🔎 Récupérer un utilisateur  |
| `DELETE` | `/users/{id}`       | 🗑️ Supprimer un utilisateur |
| `GET`    | `/users/{id}/valid` | ✅ Vérifier un utilisateur    |
| `POST`   | `/auth/login`       | 🔐 Se connecter              |

---

## 💾 Persistance

La persistance utilise :

* Spring Data JPA
* Hibernate
* MySQL

Architecture simplifiée :

```text
👤 UserEntity
      │
      ▼
📋 UserDao
      │
      ▼
⚙️ JpaUserDao
      │
      ▼
🗃️ UserEntityRepository
      │
      ▼
🗄️ MySQL
```

---

## 🛡️ Sécurité

Le projet utilise **Spring Security** pour gérer l'authentification.

Les principaux composants sont :

```text
AuthenticationManager
        │
        ▼
UserDetailsService
        │
        ▼
UserDao
        │
        ▼
Utilisateur
```

Après authentification, `JwtService` génère un token JWT.

Ce token peut ensuite être vérifié par `square-games`.

---

## 🔄 Communication avec Square Games

Les deux applications fonctionnent ensemble :

```text
👤 Alice
   │
   │ identifiant + mot de passe
   ▼
🎮 square-games
   │
   │ POST /auth/login
   ▼
👤 api-user
   │
   │ Vérification
   │
   │ Génération du JWT
   ▼
🎮 square-games
   │
   │ JWT
   ▼
🍪 Navigateur
```

Ainsi, `square-games` n'a pas besoin de gérer directement les mots de passe des utilisateurs : l'authentification est centralisée dans `api-user`.

---

## 📁 Structure du projet

```text
api-user/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── fr.campus.apiuser/
│       │       ├── config/
│       │       ├── controllers/
│       │       ├── dao/
│       │       ├── entities/
│       │       ├── repositories/
│       │       └── services/
│       │
│       └── resources/
│           └── application.properties
│
├── pom.xml
└── README.md
```

---

## 🚀 Installation et démarrage

### 📋 Prérequis

* ☕ Java
* 📦 Maven
* 🗄️ MySQL

### 1️⃣ Démarrer MySQL

Vérifier que le serveur MySQL utilisé par l'application est disponible.

### 2️⃣ Démarrer `api-user`

Depuis le dossier du projet :

```bash
mvn spring-boot:run
```

L'application démarre sur :

```text
http://localhost:8081
```

---

## 🧪 Tester l'API

Les requêtes peuvent être testées avec **Bruno**.

### Exemple : connexion

```http
POST http://localhost:8081/auth/login
```

Body :

```json
{
  "username": "Alice",
  "password": "motdepasse"
}
```

Une connexion réussie renvoie un JWT.

### Exemple : récupérer un utilisateur

```http
GET http://localhost:8081/users/{id}
```

### Exemple : vérifier un utilisateur

```http
GET http://localhost:8081/users/{id}/valid
```

---

## 🔒 Configuration et données sensibles

La configuration se trouve dans :

```text
src/main/resources/application.properties
```

⚠️ Les mots de passe de base de données et autres informations sensibles ne doivent pas être publiés sur GitHub.

---

## 🔗 Projet associé

🎮 **square-games**

Application qui utilise `api-user` pour l'authentification et la gestion des utilisateurs.

---

## 👩‍💻 Projet

Projet réalisé dans le cadre d'une formation en développement web.

**Java • Spring Boot • REST • JPA • MySQL • Spring Security • JWT**
