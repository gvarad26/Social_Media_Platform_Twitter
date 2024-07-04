# Social Media Platform

## Project Overview

This project is a Spring Boot-based social media platform that allows users to sign up, log in, create and manage posts and comments, and view user profiles and feeds.

## Features

- **User Authentication**: Users can sign up and log in using email and password.
- **User Profile**: Retrieve user details, including name, userID, and email.
- **Posts**: Create, edit, delete, and retrieve posts. Posts can be viewed in reverse chronological order.
- **Comments**: Create, edit, delete, and retrieve comments on posts.
- **User Feed**: View all posts by all users in reverse chronological order.
- **All Users**: Retrieve details of all existing users.

## Endpoints

### User Authentication

- **Login**
  - **URL**: `/login`
  - **Method**: `POST`
  - **Request Body**: `email`, `password`
  - **Response**: `Login Successful`, `Username/Password Incorrect`, `User does not exist`

- **Signup**
  - **URL**: `/signup`
  - **Method**: `POST`
  - **Request Body**: `email`, `name`, `password`
  - **Response**: `Account Creation Successful`, `Forbidden, Account already exists`

### User Profile

- **Get User Details**
  - **URL**: `/user`
  - **Method**: `GET`
  - **Query Parameter**: `userID`
  - **Response Body**: `name`, `userID`, `email`, `User does not exist`

### Posts

- **Create Post**
  - **URL**: `/post`
  - **Method**: `POST`
  - **Request Body**: `postBody`, `userID`
  - **Response**: `Post created successfully`, `User does not exist`

- **Retrieve Post**
  - **URL**: `/post`
  - **Method**: `GET`
  - **Query Parameter**: `postID`
  - **Response Body**: `postID`, `postBody`, `date`, `comments`, `Post does not exist`

- **Edit Post**
  - **URL**: `/post`
  - **Method**: `PUT`
  - **Request Body**: `postBody`, `postID`
  - **Response**: `Post edited successfully`, `Post does not exist`

- **Delete Post**
  - **URL**: `/post`
  - **Method**: `DELETE`
  - **Query Parameter**: `postID`
  - **Response**: `Post deleted`, `Post does not exist`

### Comments

- **Create Comment**
  - **URL**: `/comment`
  - **Method**: `POST`
  - **Request Body**: `commentBody`, `postID`, `userID`
  - **Response**: `Comment created successfully`, `User does not exist`, `Post does not exist`

- **Retrieve Comment**
  - **URL**: `/comment`
  - **Method**: `GET`
  - **Request Param**: `commentID`
  - **Response Body**: `commentID`, `commentBody`, `commentCreator`, `Comment does not exist`

- **Edit Comment**
  - **URL**: `/comment`
  - **Method**: `PUT`
  - **Request Body**: `commentBody`, `commentID`
  - **Response**: `Comment edited successfully`, `Comment does not exist`

- **Delete Comment**
  - **URL**: `/comment`
  - **Method**: `DELETE`
  - **Query Parameter**: `commentID`
  - **Response**: `Comment deleted`, `Comment does not exist`

### User Feed

- **Get User Feed**
  - **URL**: `/`
  - **Method**: `GET`
  - **Response Body**: `posts` (sorted in reverse chronological order)

### All Users

- **Get All Users**
  - **URL**: `/users`
  - **Method**: `GET`
  - **Response Body**: `name`, `userID`, `email`, `posts`

## Installation and Setup

1. **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/social-media-platform.git
    cd social-media-platform
    ```

2. **Build the project**:
    ```bash
    mvn clean install
    ```

3. **Run the application**:
    ```bash
    mvn spring-boot:run
    ```

4. **Access the application**:
    Open your web browser and go to `http://localhost:8080`

## Technologies Used

- **Spring Boot**
- **Java**
- **Maven**
