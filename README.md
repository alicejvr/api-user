# 👤 API User

API REST développée avec **Java** et **Spring Boot** permettant de gérer des utilisateurs.

Cette application constitue un service indépendant utilisé notamment par l'application **square-games** pour vérifier l'existence d'un utilisateur.

> 📚 Projet réalisé dans le cadre d'une formation en développement web / Java.

---

## 🛠️ Technologies utilisées

* ☕ Java
* 🌱 Spring Boot
* 🌐 Spring Web
* 🗄️ Spring Data JPA
* 🐬 MySQL
* 📖 Springdoc OpenAPI / Swagger
* 🧪 Bruno pour les tests de l'API
* 📦 Maven

---

# 🏗️ Architecture

L'application suit une organisation en plusieurs couches :

```text
Client HTTP
     │
     ▼
UserController
     │
     ▼
UserService
     │
     ▼
UserDao
     │
     ▼
UserEntityRepository
     │
     ▼
   MySQL
```

L'application expose une API REST accessible sur le port **8081**.

---

# 🚀 Installation

## Prérequis

Installer au préalable :

* Java
* Maven
* MySQL
* Git

---

## 📥 Cloner le projet

```bash
git clone <URL_DU_REPOSITORY>
cd api-user
```

---

## 🗄️ Configuration MySQL

Créer une base de données MySQL pour l'application.

Puis renseigner les informations de connexion dans :

```text
src/main/resources/application.properties
```

Exemple :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/api_user
spring.datasource.username=VOTRE_UTILISATEUR
spring.datasource.password=VOTRE_MOT_DE_PASSE

spring.jpa.hibernate.ddl-auto=update
```

Adaptez les valeurs à votre environnement.

> ⚠️ Ne committez pas de mot de passe ou d'autres informations sensibles dans un dépôt GitHub public.

---

# ▶️ Démarrer l'application

Depuis la racine du projet :

```bash
mvn spring-boot:run
```

L'application démarre sur :

```text
http://localhost:8081
```

Le port **8081** permet à cette application de fonctionner en parallèle de `square-games`, qui utilise le port 8080.

---

# 📖 Documentation Swagger

Une documentation interactive des endpoints est disponible avec Swagger.

Une fois l'application démarrée :

```text
http://localhost:8081/swagger-ui.html
```

Swagger permet de consulter la documentation et de tester les endpoints directement depuis l'interface web.

---

# 🔌 Endpoints

## 👤 Créer un utilisateur

```http
POST /users
```

Exemple de corps :

```json
{
  "id": "24c31cdf-33d5-78d8-93a4-43054311453b",
  "name": "Alice"
}
```

---

## 🔎 Récupérer un utilisateur

```http
GET /users/{id}
```

Exemple :

```text
GET /users/24c31cdf-33d5-78d8-93a4-43054311453b
```

---

## ❌ Supprimer un utilisateur

```http
DELETE /users/{id}
```

Exemple :

```text
DELETE /users/24c31cdf-33d5-78d8-93a4-43054311453b
```

---

## ✅ Vérifier l'existence d'un utilisateur

```http
GET /users/{id}/valid
```

Cette route est notamment utilisée par `square-games`.

Exemple :

```text
GET /users/24c31cdf-33d5-78d8-93a4-43054311453b/valid
```

Réponse :

```json
true
```

ou :

```json
false
```

---

# 🧪 Tester avec Bruno

Les différentes requêtes de test peuvent être regroupées dans une collection Bruno.

Les tests permettent notamment de vérifier :

* la création d'un utilisateur ;
* la récupération d'un utilisateur ;
* la suppression d'un utilisateur ;
* la vérification de l'existence d'un utilisateur ;
* les réponses de l'API pour différents identifiants.

---

# 🔗 Utilisation avec square-games

`api-user` fonctionne comme un service indépendant auquel `square-games` peut envoyer des requêtes HTTP.

```text
┌─────────────────────┐
│    square-games     │
│       :8080         │
└──────────┬──────────┘
           │
           │ GET /users/{id}/valid
           ▼
┌─────────────────────┐
│      api-user       │
│       :8081         │
└─────────────────────┘
```

Pour permettre à `square-games` de communiquer avec ce service, l'URL est configurée dans son fichier `application.properties` :

```properties
user-service.url=http://localhost:8081
```

---

# 📁 Structure du projet

```text
src/
└── main/
    ├── java/
    │   └── ...
    │       ├── controller/
    │       ├── service/
    │       ├── dao/
    │       ├── entity/
    │       └── repository/
    │
    └── resources/
        └── application.properties
```

---

# 👩‍💻 Projet

Projet réalisé dans le cadre d'une formation de développement web.

Technologies principales : **Java · Spring Boot · REST API · JPA · MySQL · Swagger**
