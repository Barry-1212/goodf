<template>
  <el-dialog
    :title="!dataForm.dId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="dName">
      <el-input v-model="dataForm.dName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="hId">
      <el-input v-model="dataForm.hId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="hName">
      <el-input v-model="dataForm.hName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="科室分类id" prop="dcId">
      <el-input v-model="dataForm.dcId" placeholder="科室分类id"></el-input>
    </el-form-item>
    <el-form-item label="科室分类名" prop="dcName">
      <el-input v-model="dataForm.dcName" placeholder="科室分类名"></el-input>
    </el-form-item>
    <el-form-item label="科室id" prop="depId">
      <el-input v-model="dataForm.depId" placeholder="科室id"></el-input>
    </el-form-item>
    <el-form-item label="科室名" prop="depName">
      <el-input v-model="dataForm.depName" placeholder="科室名"></el-input>
    </el-form-item>
    <el-form-item label="挂号费" prop="reMoney">
      <el-input v-model="dataForm.reMoney" placeholder="挂号费"></el-input>
    </el-form-item>
    <el-form-item label="出诊时间" prop="time">
      <el-input v-model="dataForm.time" placeholder="出诊时间"></el-input>
    </el-form-item>
    <el-form-item label="医生照片" prop="docPhoto">
      <el-input v-model="dataForm.docPhoto" placeholder="医生照片"></el-input>
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
          dId: 0,
          dName: '',
          hId: '',
          hName: '',
          dcId: '',
          dcName: '',
          depId: '',
          depName: '',
          reMoney: '',
          time: '',
          docPhoto: ''
        },
        dataRule: {
          dName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          hId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          hName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          dcId: [
            { required: true, message: '科室分类id不能为空', trigger: 'blur' }
          ],
          dcName: [
            { required: true, message: '科室分类名不能为空', trigger: 'blur' }
          ],
          depId: [
            { required: true, message: '科室id不能为空', trigger: 'blur' }
          ],
          depName: [
            { required: true, message: '科室名不能为空', trigger: 'blur' }
          ],
          reMoney: [
            { required: true, message: '挂号费不能为空', trigger: 'blur' }
          ],
          time: [
            { required: true, message: '出诊时间不能为空', trigger: 'blur' }
          ],
          docPhoto: [
            { required: true, message: '医生照片不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.dId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.dId) {
            this.$http({
              url: this.$http.adornUrl(`/goodf/doctor/info/${this.dataForm.dId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.dName = data.doctor.dName
                this.dataForm.hId = data.doctor.hId
                this.dataForm.hName = data.doctor.hName
                this.dataForm.dcId = data.doctor.dcId
                this.dataForm.dcName = data.doctor.dcName
                this.dataForm.depId = data.doctor.depId
                this.dataForm.depName = data.doctor.depName
                this.dataForm.reMoney = data.doctor.reMoney
                this.dataForm.time = data.doctor.time
                this.dataForm.docPhoto = data.doctor.docPhoto
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
              url: this.$http.adornUrl(`/goodf/doctor/${!this.dataForm.dId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'dId': this.dataForm.dId || undefined,
                'dName': this.dataForm.dName,
                'hId': this.dataForm.hId,
                'hName': this.dataForm.hName,
                'dcId': this.dataForm.dcId,
                'dcName': this.dataForm.dcName,
                'depId': this.dataForm.depId,
                'depName': this.dataForm.depName,
                'reMoney': this.dataForm.reMoney,
                'time': this.dataForm.time,
                'docPhoto': this.dataForm.docPhoto
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
