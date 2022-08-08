<template>
  <el-dialog
    :title="!dataForm.userId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户名" prop="userName">
      <el-input v-model="dataForm.userName" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="dataForm.password" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item label="用户地址" prop="address">
      <el-input v-model="dataForm.address" placeholder="用户地址"></el-input>
    </el-form-item>
    <el-form-item label="用户电话" prop="tel">
      <el-input v-model="dataForm.tel" placeholder="用户电话"></el-input>
    </el-form-item>
    <el-form-item label="用户权限" prop="superuser">
      <el-input v-model="dataForm.superuser" placeholder="用户权限"></el-input>
    </el-form-item>
    <el-form-item label="盐" prop="salt">
      <el-input v-model="dataForm.salt" placeholder="盐"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          userId: 0,
          userName: '',
          password: '',
          address: '',
          tel: '',
          superuser: '',
          salt: ''
        },
        dataRule: {
          userName: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          address: [
            { required: true, message: '用户地址不能为空', trigger: 'blur' }
          ],
          tel: [
            { required: true, message: '用户电话不能为空', trigger: 'blur' }
          ],
          superuser: [
            { required: true, message: '用户权限不能为空', trigger: 'blur' }
          ],
          salt: [
            { required: true, message: '盐不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.userId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.userId) {
            this.$http({
              url: `http://localhost:10000/goodf/user/info/${this.dataForm.userId}`,
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userName = data.user.userName
                this.dataForm.password = data.user.password
                this.dataForm.address = data.user.address
                this.dataForm.tel = data.user.tel
                this.dataForm.superuser = data.user.superuser
                this.dataForm.salt = data.user.salt
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: `http://localhost:10000/goodf/user/${!this.dataForm.userId ? 'save' : 'update'}`,
              method: 'post',
              data: this.$http.adornData({
                'userId': this.dataForm.userId || undefined,
                'userName': this.dataForm.userName,
                'password': this.dataForm.password,
                'address': this.dataForm.address,
                'tel': this.dataForm.tel,
                'superuser': this.dataForm.superuser,
                'salt': this.dataForm.salt
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
