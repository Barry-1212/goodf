<template>
  <el-dialog
    :title="!dataForm.hId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="hName">
      <el-input v-model="dataForm.hName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="tel">
      <el-input v-model="dataForm.tel" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="area">
      <el-input v-model="dataForm.area" placeholder=""></el-input>
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
          hId: 0,
          hName: '',
          tel: '',
          area: ''
        },
        dataRule: {
          hName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          tel: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          area: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.hId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.hId) {
            this.$http({
              url: this.$http.adornUrl(`/goodf/hospital/info/${this.dataForm.hId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.hName = data.hospital.hName
                this.dataForm.tel = data.hospital.tel
                this.dataForm.area = data.hospital.area
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
              url: this.$http.adornUrl(`/goodf/hospital/${!this.dataForm.hId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'hId': this.dataForm.hId || undefined,
                'hName': this.dataForm.hName,
                'tel': this.dataForm.tel,
                'area': this.dataForm.area
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
