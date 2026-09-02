# Gym Management System (健身房管理系统)

<p align="center">
  <a href="README.md">English</a>
</p>

基于 Spring Cloud 微服务架构的健身房管理系统，提供会员管理、员工管理、设备管理、课程管理等功能，前后端分离部署。

## 技术栈

**后端：**
- Java 17
- Spring Boot 3.5.0
- Spring Cloud Alibaba 2025.0.0.0
- Spring Cloud Gateway
- Nacos Discovery（服务注册与发现）
- MyBatis-Plus 3.5.16
- MySQL 8
- Redis（Lettuce 连接池）
- JJWT 0.12.6（JWT 认证）
- OpenFeign（服务间调用）

**前端：**
- Vue 3 + TypeScript
- Vite 8
- Element Plus 2.13
- Axios
- Vue Router

## 系统架构

```
┌─────────────┐
│   前端应用   │  Vue 3 + Element Plus（端口 5173）
└──────┬──────┘
       │ HTTP 请求
       ▼
┌──────────────┐
│  API 网关    │  Spring Cloud Gateway（端口 8080）
│  JWT 过滤器  │  ── 认证拦截 & 请求路由
└──────┬───────┘
       │
       │  Nacos 服务发现（192.168.1.147:8848）
       │
       ▼
┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐
│  用户服务  │  │  会员服务  │  │  员工服务  │  │  设备服务  │
│  (8084)  │  │  (8083)  │  │  (8081)  │  │  (8082)  │
└────┬─────┘  └────┬─────┘  └────┬─────┘  └────┬─────┘
     │             │             │             │
     └─────────────┴─────────────┴─────────────┘
                   │
              MySQL 数据库
    gym-user  gym-member  gym-employee  gym-equipment
```

## 模块说明

| 模块 | 端口 | 说明 |
|------|------|------|
| `gym-gateway` | 8080 | API 网关，JWT 认证拦截，请求路由转发 |
| `userService` | 8084 | 用户登录、管理员登录、课程管理、服务编排 |
| `memberService` | 8083 | 会员增删改查、会员卡管理 |
| `employeeService` | 8081 | 员工增删改查 |
| `equipmentService` | 8082 | 设备增删改查 |
| `frontend` | 5173 | Vue 3 前端应用 |

## 快速开始

### 环境要求

- JDK 17
- Node.js 18+
- MySQL 8.0+
- Redis
- Nacos Server 2.x

### 1. 启动 Nacos

```bash
# Linux/Mac
sh startup.sh -m standalone

# Windows
startup.cmd -m standalone
```

### 2. 创建数据库

```sql
CREATE DATABASE `gym-user` DEFAULT CHARACTER SET utf8mb4;
CREATE DATABASE `gym-member` DEFAULT CHARACTER SET utf8mb4;
CREATE DATABASE `gym-employee` DEFAULT CHARACTER SET utf8mb4;
CREATE DATABASE `gym-equipment` DEFAULT CHARACTER SET utf8mb4;
```

### 3. 启动后端服务

按以下顺序启动各服务：

```bash
# 在 IntelliJ IDEA 中逐个启动，或使用命令行
mvn spring-boot:run -pl gym-gateway
mvn spring-boot:run -pl employeeService
mvn spring-boot:run -pl equipmentService
mvn spring-boot:run -pl memberService
mvn spring-boot:run -pl userService
```

### 4. 启动前端

```bash
cd frontend
npm install
npm run dev
```

### 5. 访问地址

- **前端页面：** http://localhost:5173
- **管理员登录：** http://localhost:5173/
- **会员登录：** http://localhost:5173/#/toUserLogin

## 数据库表结构

| 服务 | 数据库 | 主要表 |
|------|--------|--------|
| userService | `gym-user` | `user`（用户）、`admin`（管理员）、`class_table`（课程）、`class_order`（课程订单） |
| memberService | `gym-member` | `member`（会员） |
| employeeService | `gym-employee` | `employee`（员工） |
| equipmentService | `gym-equipment` | `equipment`（设备） |

## API 接口说明

所有请求通过 API 网关统一访问，地址为 `http://localhost:8080`。

### 认证接口（无需登录）

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/api/userLogin` | 会员登录（返回 JWT token） |
| POST | `/api/adminLogin` | 管理员登录（返回 JWT token） |
| POST | `/api/logout` | 退出登录 |

### 用户模块（`userService`）

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/toUserMain` | 获取用户主页数据 |
| GET | `/api/toAdminMain` | 获取管理员仪表盘数据 |
| GET | `/api/user/toUserInfo` | 获取用户个人信息 |
| GET | `/api/user/toUpdateInfo` | 获取用户信息（编辑用） |
| POST | `/api/user/updateInfo` | 更新用户信息 |
| GET | `/api/user/toApplyClass` | 获取可选课程列表 |
| POST | `/api/user/applyClass` | 报名课程 |
| GET | `/api/user/toUserClass` | 查看已报名课程 |
| POST | `/api/user/delUserClass` | 取消报名课程 |
| GET | `/api/class/selClass` | 查询所有课程（管理端） |
| GET | `/api/class/selClassOrder` | 按课程ID查询报名记录 |
| POST | `/api/class/addClass` | 添加课程 |
| POST | `/api/class/delClass` | 删除课程 |

### 会员模块（`memberService`）

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/member/selMember` | 查询所有会员 |
| POST | `/api/member/addMember` | 添加会员 |
| POST | `/api/member/updateMember` | 更新会员信息 |
| GET | `/api/member/toUpdateMember` | 获取会员信息（编辑用） |
| POST | `/api/member/delMember` | 删除会员 |

### 员工模块（`employeeService`）

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/employee/selEmployee` | 查询所有员工 |
| POST | `/api/employee/addEmployee` | 添加员工 |
| POST | `/api/employee/updateEmployee` | 更新员工信息 |
| GET | `/api/employee/toUpdateEmployee` | 获取员工信息（编辑用） |
| POST | `/api/employee/delEmployee` | 删除员工 |

### 设备模块（`equipmentService`）

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/equipment/selEquipment` | 查询所有设备 |
| POST | `/api/equipment/addEquipment` | 添加设备 |
| POST | `/api/equipment/updateEquipment` | 更新设备信息 |
| GET | `/api/equipment/toUpdateEquipment` | 获取设备信息（编辑用） |
| POST | `/api/equipment/delEquipment` | 删除设备 |

## 前端页面路由

| 路径 | 页面 | 权限 |
|------|------|------|
| `/` | 管理员登录页 | 公开 |
| `/toUserLogin` | 会员登录页 | 公开 |
| `/toAdminMain` | 管理员首页 | 管理员 |
| `/toUserMain` | 会员首页 | 会员 |
| `/member/selMember` | 会员列表 | 管理员 |
| `/member/toAddMember` | 添加会员 | 管理员 |
| `/member/toUpdateMember` | 编辑会员 | 管理员 |
| `/member/toSelByCard` | 按卡查询会员 | 管理员 |
| `/employee/selEmployee` | 员工列表 | 管理员 |
| `/employee/toAddEmployee` | 添加员工 | 管理员 |
| `/employee/toUpdateEmployee` | 编辑员工 | 管理员 |
| `/equipment/selEquipment` | 设备列表 | 管理员 |
| `/equipment/toAddEquipment` | 添加设备 | 管理员 |
| `/equipment/toUpdateEquipment` | 编辑设备 | 管理员 |
| `/class/selClass` | 课程列表（管理端） | 管理员 |
| `/class/toAddClass` | 添加课程 | 管理员 |
| `/class/selClassOrder` | 课程报名记录 | 管理员 |
| `/user/toUserInfo` | 个人信息 | 会员 |
| `/user/toUpdateInfo` | 编辑信息 | 会员 |
| `/user/toUserClass` | 我的课程 | 会员 |
| `/user/toApplyClass` | 报名课程 | 会员 |
| `/user/toChat` | 聊天 | 会员 |

## 认证机制

系统采用 JWT 令牌认证：

1. **登录：** 会员/管理员提交账号密码到 `/api/userLogin` 或 `/api/adminLogin`
2. **获取令牌：** 服务端验证通过后返回 JWT token（有效期 24 小时，HMAC-SHA256 签名）
3. **存储令牌：** 前端将 token 存入 `localStorage`，key 为 `gym_token`
4. **发送请求：** 后续所有请求自动在请求头携带 `Authorization: Bearer {token}`
5. **网关校验：** API 网关的 `MyGlobalFilter` 拦截所有请求：
   - 白名单路径（`/api/userLogin`、`/api/adminLogin`）→ 直接放行
   - 没有 token → 返回 `401 Unauthorized`，提示"未登录"
   - token 无效或过期 → 返回 `401 Unauthorized`，提示"token无效或已过期"
   - token 有效 → 转发请求到目标服务

## 服务配置

### Nacos

所有服务注册到 Nacos 地址 `192.168.1.147:8848`，如需修改请在各模块的 `application.yml` 中更新。

### 数据库

| 服务 | 数据库 | 用户名 | 密码 |
|------|--------|--------|------|
| userService | `gym-user` | root | 123456 |
| memberService | `gym-member` | root | 123456 |
| employeeService | `gym-employee` | root | 123456 |
| equipmentService | `gym-equipment` | root | 123456 |

### Redis

userService 使用 Lettuce 连接池连接 Redis，地址为 `localhost:6379`。