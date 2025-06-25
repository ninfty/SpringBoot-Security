# 🌐 API Request Examples

## 🔐 Authentication Levels
| Endpoint    | Access Level | Description          |
|------------|-------------|----------------------|
| `GET /public`  | Public      | Accessible to anyone |
| `GET /private` | Authenticated | Requires x-secret header |
| `GET /admin`   | Admin       | For Master user only |
| `GET /hr/manager`   | Authenticated       | Requires HR_MANAGER permission |
| `GET /hr/assistant`   | Authenticated       | Requires HR_ASSISTANT permission |

---

## 👥 Groups Management

### Create New Group
```http
POST /groups
Content-Type: application/json

{
  "name": "HR_ASSISTANT"
}
```

### List All Groups
```http
GET /groups
Accept: application/json
```

---

## 👤 Users Management

### Create New User
```http
POST /users
Content-Type: application/json

{
  "user": {
    "name": "assistant",
    "login": "assistant",
    "password": "123"
  },
  "permissions": [
    "HR_ASSISTANT"
  ]
}
```

### Example Responses
**Success (201 Created):**
```json
{
  "id": "136f140b-3efe-4170-aa0c-708539c7f3af",
  "login": "assistant",
  "password": "$2a$10$UWFg4updcF.rfUmAEOo2ie0PMVHCh.GX2h1iWhJKAPU0aC48rVKQS",
  "name": "assistant",
  "permissions": null
}
```

---

## 📋 HR routes

### Manager
```http
GET /hr/manager
Accept: application/json
```

### Assistant
```http
GET /hr/assistant
Accept: application/json
```
