# Gym Management System

A gym management system built with Spring Cloud microservices architecture. Provides member management, employee management, equipment management, and class scheduling with a decoupled frontend/backend deployment.

## Tech Stack

**Backend:**
- Java 17
- Spring Boot 3.5.0
- Spring Cloud Alibaba 2025.0.0.0
- Spring Cloud Gateway
- Nacos Discovery (Service Registry)
- MyBatis-Plus 3.5.16
- MySQL 8
- Redis (Lettuce)
- JJWT 0.12.6 (JWT Authentication)
- OpenFeign (Service-to-Service Calls)

**Frontend:**
- Vue 3 + TypeScript
- Vite 8
- Element Plus 2.13
- Axios
- Vue Router

## Architecture

```
┌─────────────┐
│   Frontend  │  Vue 3 + Element Plus (port 5173)
└──────┬──────┘
       │ HTTP Request
       ▼
┌──────────────┐
│  API Gateway │  Spring Cloud Gateway (port 8080)
│  JWT Filter  │  ── Authentication & Routing
└──────┬───────┘
       │
       │  Nacos Service Discovery (192.168.1.147:8848)
       │
       ▼
┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐
│  user    │  │  member  │  │ employee │  │equipment │
│  Service │  │  Service │  │  Service │  │  Service │
│ (8084)   │  │  (8083)  │  │  (8081)  │  │  (8082)  │
└────┬─────┘  └────┬─────┘  └────┬─────┘  └────┬─────┘
     │             │             │             │
     └─────────────┴─────────────┴─────────────┘
                   │
              MySQL Databases
    gym-user  gym-member  gym-employee  gym-equipment
```

## Modules

| Module | Port | Description |
|--------|------|-------------|
| `gym-gateway` | 8080 | API Gateway, JWT authentication, request routing |
| `userService` | 8084 | User login, admin login, class management, service orchestration |
| `memberService` | 8083 | Member CRUD, member card management |
| `employeeService` | 8081 | Employee CRUD |
| `equipmentService` | 8082 | Equipment CRUD |
| `frontend` | 5173 | Vue 3 frontend application |

## Getting Started

### Prerequisites

- JDK 17
- Node.js 18+
- MySQL 8.0+
- Redis
- Nacos Server 2.x

### 1. Start Nacos

```bash
# Linux/Mac
sh startup.sh -m standalone

# Windows
startup.cmd -m standalone
```

### 2. Create Databases

```sql
CREATE DATABASE `gym-user` DEFAULT CHARACTER SET utf8mb4;
CREATE DATABASE `gym-member` DEFAULT CHARACTER SET utf8mb4;
CREATE DATABASE `gym-employee` DEFAULT CHARACTER SET utf8mb4;
CREATE DATABASE `gym-equipment` DEFAULT CHARACTER SET utf8mb4;
```

### 3. Start Backend Services

Start services in order:

```bash
# Start each service (IntelliJ IDEA or command line)
mvn spring-boot:run -pl gym-gateway
mvn spring-boot:run -pl employeeService
mvn spring-boot:run -pl equipmentService
mvn spring-boot:run -pl memberService
mvn spring-boot:run -pl userService
```

### 4. Start Frontend

```bash
cd frontend
npm install
npm run dev
```

### 5. Access

- **Frontend:** http://localhost:5173
- **Admin Login:** http://localhost:5173/#/
- **User Login:** http://localhost:5173/#/toUserLogin

## API Routes

All requests go through the API Gateway at `http://localhost:8080`.

### Authentication (Public)

| Method | Path | Description |
|--------|------|-------------|
| POST | `/api/userLogin` | Member login (returns JWT token) |
| POST | `/api/adminLogin` | Admin login (returns JWT token) |
| POST | `/api/logout` | Logout |

### User Module (`userService`)

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/toUserMain` | Get user main page data |
| GET | `/api/toAdminMain` | Get admin dashboard data |
| GET | `/api/user/toUserInfo` | Get user info |
| GET | `/api/user/toUpdateInfo` | Get user info for update |
| POST | `/api/user/updateInfo` | Update user info |
| GET | `/api/user/toApplyClass` | Get available class list |
| POST | `/api/user/applyClass` | Apply for a class |
| GET | `/api/user/toUserClass` | Get user's class orders |
| POST | `/api/user/delUserClass` | Delete a class order |
| GET | `/api/class/selClass` | Query all classes |
| GET | `/api/class/selClassOrder` | Query class orders by class ID |
| POST | `/api/class/addClass` | Add a class |
| POST | `/api/class/delClass` | Delete a class |

### Member Module (`memberService`)

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/member/selMember` | Get all members |
| POST | `/api/member/addMember` | Add a member |
| POST | `/api/member/updateMember` | Update member info |
| GET | `/api/member/toUpdateMember` | Get member info for update |
| POST | `/api/member/delMember` | Delete a member |

### Employee Module (`employeeService`)

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/employee/selEmployee` | Get all employees |
| POST | `/api/employee/addEmployee` | Add an employee |
| POST | `/api/employee/updateEmployee` | Update employee info |
| GET | `/api/employee/toUpdateEmployee` | Get employee info for update |
| POST | `/api/employee/delEmployee` | Delete an employee |

### Equipment Module (`equipmentService`)

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/equipment/selEquipment` | Get all equipment |
| POST | `/api/equipment/addEquipment` | Add equipment |
| POST | `/api/equipment/updateEquipment` | Update equipment info |
| GET | `/api/equipment/toUpdateEquipment` | Get equipment info for update |
| POST | `/api/equipment/delEquipment` | Delete equipment |

## Frontend Routes

| Path | Page | Role |
|------|------|------|
| `/` | Admin Login | Public |
| `/toUserLogin` | User Login | Public |
| `/toAdminMain` | Admin Dashboard | Admin |
| `/toUserMain` | User Dashboard | User |
| `/member/selMember` | Member List | Admin |
| `/member/toAddMember` | Add Member | Admin |
| `/member/toUpdateMember` | Update Member | Admin |
| `/member/toSelByCard` | Search by Card | Admin |
| `/employee/selEmployee` | Employee List | Admin |
| `/employee/toAddEmployee` | Add Employee | Admin |
| `/employee/toUpdateEmployee` | Update Employee | Admin |
| `/equipment/selEquipment` | Equipment List | Admin |
| `/equipment/toAddEquipment` | Add Equipment | Admin |
| `/equipment/toUpdateEquipment` | Update Equipment | Admin |
| `/class/selClass` | Class List (Admin) | Admin |
| `/class/toAddClass` | Add Class | Admin |
| `/class/selClassOrder` | Class Orders (Admin) | Admin |
| `/user/toUserInfo` | User Info | User |
| `/user/toUpdateInfo` | Update Info | User |
| `/user/toUserClass` | My Classes | User |
| `/user/toApplyClass` | Apply Class | User |
| `/user/toChat` | Chat | User |

## Authentication

The system uses JWT-based authentication:

1. **Login:** User/admin sends credentials to `/api/userLogin` or `/api/adminLogin`
2. **Token:** Server returns a JWT token (`expires: 24h`) signed with HMAC-SHA256
3. **Storage:** Frontend stores the token in `localStorage` under key `gym_token`
4. **Request:** All subsequent requests include the header `Authorization: Bearer {token}`
5. **Gateway Validation:** API Gateway's `MyGlobalFilter` intercepts all requests:
   - Whitelisted paths (`/api/userLogin`, `/api/adminLogin`) → pass through
   - Missing token → `401 Unauthorized`
   - Invalid/expired token → `401 Unauthorized`
   - Valid token → forward request to target service

## Service Configuration

### Nacos

All services register with Nacos at `192.168.1.147:8848`. Update the address in each module's `application.yml` if needed.

### Database

| Service | Database | Table |
|---------|----------|-------|
| userService | `gym-user` | `user`, `admin`, `class_table`, `class_order` |
| memberService | `gym-member` | `member` |
| employeeService | `gym-employee` | `employee` |
| equipmentService | `gym-equipment` | `equipment` |

### Redis

userService uses Redis with Lettuce connection pool at `localhost:6379`.