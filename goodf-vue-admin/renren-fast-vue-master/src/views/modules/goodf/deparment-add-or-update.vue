<template>
  <el-dialog
    :title="!dataForm.depId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="科室名称" prop="depName">
      <el-input v-model="dataForm.depName" placeholder="科室名称"></el-input>
    </el-form-item>
    <el-form-item label="科室分类" prop="dcId">
      <!-- <el-input v-model="dataForm.dcId" placeholder="所属科室分类id"></el-input> -->
      <template>
        <el-select v-model="dataForm.dcId" placeholder="请选择">
          <el-option
            v-for="item in depClassList"
            :key="item.dcId"
            :label="item.dcName"
            :value="item.dcId">
          </el-option>
        </el-select>
      </template>
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
          depId: 0,
          depName: '',
          dcId: ''
        },
        depClassList:[],
        dataRule: {
          depName: [
            { required: true, message: '科室表名称不能为空', trigger: 'blur' }
          ],
          dcId: [
            { required: true, message: '所属科室分类id不能为空', trigger: 'blur' }
          ]
        }
      }
    },

  

    methods: {
      getDepClassIdList () {
        this.$http({
          url: 'http://localhost:10000/goodf/depclass/idList',
          method: 'get'
        }).then(({data}) => {
              if (data && data.code === 0) {
                this.depClassList = data.list;
              }
            })
      },
      init (id) {
        this.dataForm.depId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.depId) {
            this.$http({//  url: this.$http.adornUrl(`/goodf/deparment/info/${this.dataForm.depId}`)
              url: `http://localhost:10000/goodf/deparment/info/${this.dataForm.depId}`,
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.depName = data.deparment.depName
                this.dataForm.dcId = data.deparment.dcId
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
              // url: this.$http.adornUrl(`/goodf/deparment/${!this.dataForm.depId ? 'save' : 'update'}`),
              url: `http://localhost:10000/goodf/deparment/${!this.dataForm.depId ? 'save' : 'update'}`,
              method: 'post',
              data: this.$http.adornData({
                'depId': this.dataForm.depId || undefined,
                'depName': this.dataForm.depName,
                'dcId': this.dataForm.dcId
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
      this.getDepClassIdList();
    },
  }
</script>
