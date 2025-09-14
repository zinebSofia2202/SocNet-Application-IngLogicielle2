# 📱 SocNet - Mini Réseau Social avec Modération par IA

SocNet est une application Java EE simulant un réseau social basique, permettant aux utilisateurs de publier, commenter, réagir et interagir avec des posts. Une couche d'intelligence artificielle a été ajoutée pour détecter automatiquement les contenus violents ou inappropriés lors de la publication.

---

## ✨ Fonctionnalités principales

- Authentification utilisateur (connexion/session)
- Création de posts avec :
  - Titre, contenu, image
  - Analyse de contenu automatique via IA pour détecter la violence
- Affichage des posts avec image, contenu et auteur
- Réactions (LIKE / DISLIKE)
- Commentaires avec popup modale
- Uploads sécurisés (limite de taille, formats)
- Intégration IA pour modération automatique

---

## 🤖 Détection de contenu violent

Chaque fois qu’un utilisateur crée un post, le contenu est passé à une méthode d’analyse via `AICheck.checkViolentContent(content)`.

- ✅ Si le contenu est jugé **approprié**, le post est publié.
- ❌ Si le contenu est jugé **violent/inapproprié**, un drapeau `isViolent` est activé dans l’entité `Post`.

L’attribut `isViolent` permet :
- de signaler visuellement le contenu à modérer.

> 📌 L’analyse est faite côté serveur utilise un prompt structuré pour demander à OpenAI une classification binaire ou un score de toxicité, L’API nécessite une clé valide (OPENAI_API_KEY) à configurer dans une constante.

---

## 🛠️ Technologies utilisées

| Layer | Technologie |
|-------|-------------|
| Backend | Java EE (Servlets), Hibernate ORM |
| Frontend | JSP, HTML5, Bootstrap 4/5, jQuery |
| Base de données | MySQL (ou toute BDD relationnelle compatible JPA) |
| IA | Java class `AICheck` (rule-based detection, modifiable pour intégrer des modèles NLP) |

---

## 📂 Architecture du projet

socnet/
├── com/
│   ├── conf/               # Hibernate config
│   ├── controller/         # Servlets (Post, Reaction, Comment)
│   ├── dao/                # DAO
│   ├── entities/           # JPA entities
│   ├── metier/             # Business logic
│   └── ai/                 # ✅ AICheck.java (OpenAI integration)
│
├── accueil.jsp             # Page d’accueil
├── WEB-INF/                # web.xml



---

## Installation / Lancement

### Prérequis

- JDK 11+
- Apache Tomcat 9+
- MySQL 
- Clé OpenAI valide

 
