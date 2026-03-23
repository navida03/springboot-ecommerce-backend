#  Spring Boot E-Commerce Backend (JWT Secured)

A production-style backend application built using **Spring Boot**, implementing secure REST APIs for an e-commerce system with **JWT-based authentication**, **cart management**, and **clean DTO responses**.


## Features

*  JWT Authentication (Login + Token validation)
*  Secured APIs using Spring Security
*  Add to Cart / Remove from Cart
*  Product Management
*  Global Exception Handling (400, 403, 404, 500)
*  Clean API responses using DTOs
*  Layered Architecture (Controller → Service → Repository)

## Tech Stack

* Java
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* Hibernate / JPA
* MySQL
* Maven

##  Project Structure

```
com.ecommerce.ecommerce_backend
│
├── controller        # REST APIs
├── service           # Business logic
├── repository        # Database access
├── model             # Entities
├── dto               # Request & Response DTOs
├── security          # JWT & Security config
├── exception         # Global exception handling
├── config            # Swagger config
```

---

##  Authentication Flow

1. User logs in via `/auth/login`
2. Server returns JWT token
3. Token is passed in header:

   ```
   Authorization: Bearer <token>
   ```
4. Secured APIs validate token via JWT filter

---

## 📌 API Endpoints

---

### 🔐 1. Login

**POST** `/auth/login`

#### Request:

```json
{
  "username": "admin",
  "password": "admin123"
}
```

#### Response:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
}
```

---

### 🛒 2. Add to Cart

**POST** `/cart/add`

#### Headers:

```
Authorization: Bearer <token>
Content-Type: application/json
```

#### Request:

```json
{
  "productId": 3,
  "quantity": 2
}
```

#### Response (DTO):

```json
{
  "userName": "Navida",
  "productName": "face wash",
  "quantity": 2
}
```

---

### 🗑️ 3. Remove from Cart

**DELETE** `/cart/remove/{productId}`

#### Example:

```
DELETE /cart/remove/3
```

#### Response:

```json
{
  "message": "Product removed"
}
```

---

### ❌ 4. Unauthorized Access

If token is missing/invalid:

```json
403 Forbidden
```

---

# 5. Item Not Found

```json
{
  "status": 404,
  "message": "Cart item not found"
}
```

---

# Error Handling

Handled using `GlobalExceptionHandler`:

| Error Code | Description                     |
| ---------- | ------------------------------- |
| 400        | Bad Request                     |
| 403        | Forbidden (Unauthorized access) |
| 404        | Resource Not Found              |
| 500        | Internal Server Error           |

---

# Security Highlights

* Password is **hidden using @JsonIgnore**
* JWT tokens have expiration handling
* Secure endpoints require authentication
* Role-based authority structure implemented

---

# How to Run

1. Clone the repository:

https://github.com/navida03/springboot-ecommerce-backend.git

2. Configure database in `application.properties`

3. Run the application

4. Test APIs using:

* Postman
* Swagger UI (if enabled)

Added:

* Login API response
* Add to cart success
* Delete cart item
* Unauthorized request (403)

---

# Author

Navida Rajbhoj


# Future Enhancements

* Order & Checkout system
* Payment integration
* Role-based access (Admin/User)
* API documentation using Swagger UI

