let baseUrl = '/api' // 本地代理

switch (process.env.NODE_ENV) {
  case 'dev':
    baseUrl = 'http://47.52.58.92:8080/' // 测试环境url
    break
  case 'pre':
    baseUrl = 'https://47.52.58.92:8080' // 预上线环境url
    break
  case 'production':
    baseUrl = 'https://47.52.58.92:8080' // 生产环境url
    break
}

export default baseUrl