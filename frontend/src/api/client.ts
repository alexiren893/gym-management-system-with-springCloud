import axios from 'axios'

const TOKEN_KEY = 'gym_token'

export function getToken(): string | null {
  return localStorage.getItem(TOKEN_KEY)
}

export function clearToken() {
  localStorage.removeItem(TOKEN_KEY)
}

const api = axios.create({
  withCredentials: true
})

// 请求拦截器：localStorage 里有 token 就自动带上
api.interceptors.request.use((config) => {
  const token = getToken()
  if (token) {
    config.headers = config.headers || {}
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

// 响应拦截器：登录接口返回 token 时自动存起来
api.interceptors.response.use((response) => {
  const token = response?.data?.token
  if (token && typeof token === 'string' && token.length > 0) {
    localStorage.setItem(TOKEN_KEY, token)
  }
  return response
})

export function postForm(url: string, data: Record<string, any>) {
  const body = new URLSearchParams()
  Object.keys(data || {}).forEach((k) => {
    const v = data[k]
    if (v !== undefined && v !== null) body.append(k, String(v))
  })
  return api.post(url, body, {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
    }
  })
}

export default api
