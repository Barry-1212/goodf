<template>
  <el-dialog
    :title="!dataForm.numId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户名" prop="userName">
      <el-input v-model="dataForm.userName" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="医院id" prop="hId">
      <el-input v-model="dataForm.hId" placeholder="医院id"></el-input>
    </el-form-item>
    <el-form-item label="医院名" prop="hName">
      <el-input v-model="dataForm.hName" placeholder="医院名"></el-input>
    </el-form-item>
    <el-form-item label="医生id" prop="dId">
      <el-input v-model="dataForm.dId" placeholder="医生id"></el-input>
    </el-form-item>
    <el-form-item label="医生名称" prop="dName">
      <el-input v-model="dataForm.dName" placeholder="医生名称"></el-input>
    </el-form-item>
    <el-form-item label="科室表id" prop="depId">
      <el-input v-model="dataForm.depId" placeholder="科室表id"></el-input>
    </el-form-item>
    <el-form-item label="科室表名" prop="depName">
      <el-input v-model="dataForm.depName" placeholder="科室表名"></el-input>
    </el-form-item>
    <el-form-item label="科室分类id" prop="dcId">
      <el-input v-model="dataForm.dcId" placeholder="科室分类id"></el-input>
    </el-form-item>
    <el-form-item label="科室分类名" prop="dcName">
      <el-input v-model="dataForm.dcName" placeholder="科室分类名"></el-input>
    </el-form-item>
    <el-form-item label="就诊时间" prop="time">
      <el-input v-model="dataForm.time" placeholder="就诊时间"></el-input>
    </el-form-item>
    <el-form-item label="支付状态" prop="paymentStatus">
      <el-input v-model="dataForm.paymentStatus" placeholder="支付状态"></el-input>
    </el-form-item>
    <el-form-item label="就诊状态" prop="visitStatus">
      <el-input v-model="dataForm.visitStatus" placeholder="就诊状态"></el-input>
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
          numId: 0,
          userName: '',
          hId: '',
          hName: '',
          dId: '',
          dName: '',
          depId: '',
          depName: '',
          dcId: '',
          dcName: '',
          time: '',
          paymentStatus: '',
          visitStatus: ''
        },
        dataRule: {
          userName: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          hId: [
            { required: true, message: '医院id不能为空', trigger: 'blur' }
          ],
          hName: [
            { required: true, message: '医院名不能为空', trigger: 'blur' }
          ],
          dId: [
            { required: true, message: '医生id不能为空', trigger: 'blur' }
          ],
          dName: [
            { required: true, message: '医生名称不能为空', trigger: 'blur' }
          ],
          depId: [
            { required: true, message: '科室表id不能为空', trigger: 'blur' }
          ],
          depName: [
            { required: true, message: '科室表名不能为空', trigger: 'blur' }
          ],
          dcId: [
            { required: true, message: '科室分类id不能为空', trigger: 'blur' }
          ],
          dcName: [
            { required: true, message: '科室分类名不能为空', trigger: 'blur' }
          ],
          time: [
            { required: true, message: '就诊时间不能为空', trigger: 'blur' }
          ],
          paymentStatus: [
            { required: true, message: '支付状态不能为空', trigger: 'blur' }
          ],
          visitStatus: [
            { required: true, message: '就诊状态不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.numId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.numId) {
            this.$http({
              url: `http://localhost:10000/goodf/registration/info/${this.dataForm.numId}`,
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userName = data.registration.userName
                this.dataForm.hId = data.registration.hid
                this.dataForm.hName = data.registration.hname
                this.dataForm.dId = data.registration.did
                this.dataForm.dName = data.registration.dname
                this.dataForm.depId = data.registration.depId
                this.dataForm.depName = data.registration.depName
                this.dataForm.dcId = data.registration.dcId
                this.dataForm.dcName = data.registration.dcName
                this.dataForm.time = data.registration.time
                this.dataForm.paymentStatus = data.registration.paymentStatus
                this.dataForm.visitStatus = data.registration.visitStatus
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
              url: `http://localhost:10000/goodf/registration/${!this.dataForm.numId ? 'save' : 'update'}`,
              method: 'post',
              data: this.$http.adornData({
                'numId': this.dataForm.numId || undefined,
                'userName': this.dataForm.userName,
                'hid': this.dataForm.hId,
                'hname': this.dataForm.hName,
                'did': this.dataForm.dId,
                'dname': this.dataForm.dName,
                'depId': this.dataForm.depId,
                'depName': this.dataForm.depName,
                'dcId': this.dataForm.dcId,
                'dcName': this.dataForm.dcName,
                'time': this.dataForm.time,
                'paymentStatus': this.dataForm.paymentStatus,
                'visitStatus': this.dataForm.visitStatus
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
