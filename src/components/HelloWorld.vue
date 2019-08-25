<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
        <router-link to='/login'><a href='/login'>登录</a></router-link>
        <router-link to='/register'><a href='/register'>注册</a></router-link>
        <router-link to='/forgetPassword'><a href='/register'>忘记密码</a></router-link>
        <router-link to='/forgetPassword'><a href='/register'>修改密码</a></router-link>
        <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'Index',
  data () {
    return {
      msg: '页面路由'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
