<template>
  <el-dialog
    :title="!dataForm.hId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="医院姓名" prop="hname">
      <el-input v-model="dataForm.hname" placeholder="医院姓名"></el-input>
    </el-form-item>
    <el-form-item label="医院电话" prop="tel">
      <el-input v-model="dataForm.tel" placeholder="医院电话"></el-input>
    </el-form-item>
    <el-form-item label="医院所属地区" prop="area">
      <!-- <el-input v-model="dataForm.area" placeholder="医院所属地区"></el-input> -->
      <template>
        <el-select v-model="dataForm.area" placeholder="请选择">
          <el-option
            v-for="item in areaList"
            :key="item.area"
            :label="item.area"
            :value="item.area">
          </el-option>
        </el-select>
      </template>
    </el-form-item>
    <el-form-item label="医院简介" prop="hbi">
      <el-input v-model="dataForm.hbi" placeholder="医院的简介"></el-input>
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
          hname: '',
          tel: '',
          area: '',
          hbi: ''
        },
        areaList:[],
        dataRule: {
          hname: [
            { required: true, message: '医院名不能为空', trigger: 'blur' }
          ],
          tel: [
            { required: true, message: '医院电话不能为空', trigger: 'blur' }
          ],
          area: [
            { required: true, message: '医院所属地区不能为空', trigger: 'blur' }
          ],
          hbi: [
            { required: true, message: '医院的简介不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {

      getAreaIdList () {
        this.$http({
          url: 'http://localhost:10000/goodf/area/idList',
          method: 'get'
        }).then(({data}) => {
              if (data && data.code === 0) {
                this.areaList = data.list;
              }
            })
      },

      init (id) {
        this.dataForm.hId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.hId) {
            this.$http({
              url: `http://localhost:10000/goodf/hospital/info/${this.dataForm.hId}`,
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.hname = data.hospital.hname
                this.dataForm.tel = data.hospital.tel
                this.dataForm.area = data.hospital.area
                this.dataForm.hbi = data.hospital.hbi
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
              url: `http://localhost:10000/goodf/hospital/${!this.dataForm.hId ? 'save' : 'update'}`,
              method: 'post',
              data: this.$http.adornData({
                'hid': this.dataForm.hId || undefined,
                'hname': this.dataForm.hname,
                'tel': this.dataForm.tel,
                'area': this.dataForm.area,
                'hbi': this.dataForm.hbi
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
    },

    created(){
      this.getAreaIdList();
    },

  }
</script>
