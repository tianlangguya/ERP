<template>
    <div id="login-box">
    <el-container>
        <h2>{{message}}</h2>
        <el-form class="demo-form-inline">
        <el-form-item label="用户名">
            <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="密码">
            <el-input v-model="form.password"></el-input>
        </el-form-item>
        <el-row>
            <el-button type="primary" @click="onSubmit">登录</el-button>
            <el-button type="primary">注册</el-button>
        </el-row>
        </el-form>
    </el-container>
    </div>
</template>

<script>
export default {
  el: '#login-box',
  name: 'Login',
  data () {
    return {
      message: '办公考勤erp系统',
      form: {
        name: '',
        password: ''
      }
    }
  },
  methods: {
    onSubmit () {
      console.log('submit!');
      console.log('1',this.form.name+this.form.password);
      this.$axios.get('localhost:8888/erp/rest/user/getByName').then(res=>{
          console.log(res,res);
      })
    }
  }
}
</script>

<style>


</style>
