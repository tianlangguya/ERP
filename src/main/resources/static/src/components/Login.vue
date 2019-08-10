<template>
    <div>
        <div class="">{{title}}</div>
        <div><input placeholder="用户名" /></div>
        <div><input placeholder="密码" /></div>
        <div><button>登录</button></div>
        <div><button>注册</button></div>
    </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      title: '办公考勤ERP系统'
    }
  }
}
</script>

<style>

</style>
