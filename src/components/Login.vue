<template>
    <div>
    <el-container>
        <h2>{{message}}</h2>
        <el-form class="demo-form-inline">
        <el-row>
            <el-col :span="24">
                    <el-form-item label="用户名">
                        <el-input v-model="form.name"></el-input>
                    </el-form-item>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="30">
                    <el-form-item label="密码">
                        <el-input v-model="form.password"></el-input>
                    </el-form-item>
            </el-col>
        </el-row>
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
  name: 'Login',
  data () {
    return {
      message: '办公考勤erp系统登录页面',
      form: {
        name: '',
        password: ''
      }
    }
  },
  created(){
  },
  methods: {
    async onSubmit () {
      console.log('submit!');
      console.log('1',this.form.name+this.form.password);
      let params={
          userName:"TLGY",
          password:"123456"
      }
      this.$axios.post('/api/erp/login',params).then(res=>{
          console.log(res,res);
      })
    }
  }
}
</script>

<style>
.el-form-item{
    display:flex;
    justify-content:center;
    margin-bottom: 15px;
}
.el-col-30{
    margin-left: 13px;
}

</style>
