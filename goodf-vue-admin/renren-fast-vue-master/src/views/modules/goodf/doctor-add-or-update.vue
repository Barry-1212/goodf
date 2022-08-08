<template>
  <el-dialog
    :title="!dataForm.did ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="医生姓名" prop="dName">
      <el-input v-model="dataForm.dName" placeholder="医生姓名"></el-input>
    </el-form-item>
    <el-form-item label="所属医院" prop="hId">
      <!-- <el-input v-model="dataForm.hId" placeholder="医院id"></el-input> -->
      <template>
        <el-select v-model="dataForm.hId" placeholder="请选择">
          <el-option
            v-for="item in hospitalList"
            :key="item.hid"
            :label="item.hname"
            :value="item.hid">
          </el-option>
        </el-select>
      </template>
    </el-form-item>
    <!-- <el-form-item label="医院姓名" prop="hName">
      <el-input v-model="dataForm.hName" placeholder="医院姓名"></el-input>
    </el-form-item> -->
    <el-form-item label="科室分类" prop="dcId">
      <!-- <el-input v-model="dataForm.dcId" placeholder="科室分类id"></el-input> -->
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
    <!-- <el-form-item label="科室分类名" prop="dcName">
      <el-input v-model="dataForm.dcName" placeholder="科室分类名"></el-input>
    </el-form-item> -->
    <el-form-item label="科室" prop="depId">
      <!-- <el-input v-model="dataForm.depId" placeholder="科室id"></el-input> -->
      <template>
        <el-select v-model="dataForm.depId" placeholder="请选择">
          <el-option
            v-for="item in depList"
            :key="item.depId"
            :label="item.depName"
            :value="item.depId">
          </el-option>
        </el-select>
      </template>
    </el-form-item>
    <!-- <el-form-item label="科室名" prop="depName">
      <el-input v-model="dataForm.depName" placeholder="科室名"></el-input>
    </el-form-item> -->
    <el-form-item label="挂号费" prop="reMoney">
      <el-input v-model="dataForm.reMoney" placeholder="挂号费"></el-input>
    </el-form-item>
    <el-form-item label="上班时间" prop="startTime">
       <el-time-picker
       value-format="yyyy-MM-dd HH:mm:ss"
        v-model="dataForm.startTime"
        :picker-options="{
          selectableRange: '00:00:00 - 23:59:59'
        }"
        placeholder="任意时间点">
      </el-time-picker>
    </el-form-item>
    <el-form-item label="下班时间" prop="endTime">
      <el-time-picker
      value-format="yyyy-MM-dd HH:mm:ss"
        arrow-control
        v-model="dataForm.endTime"
        :picker-options="{
          selectableRange: '00:00:00 - 23:59:59'
        }"
        placeholder="任意时间点">
      </el-time-picker>
    </el-form-item>
    <el-form-item label="医生介绍" prop="docIntroduction">
      <el-input v-model="dataForm.docIntroduction" placeholder="医生介绍"></el-input>
    </el-form-item>
    <el-form-item label="医生照片" prop="docPhoto">
      <single-upload v-model='dataForm.docPhoto'></single-upload>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>

<script>
  import SingleUpload from "@/components/upload/singleUpload";

  export default {
    components:{SingleUpload},
    data () {
      return {
        visible: false,
        dataForm: {
          did: 0,
          dName: '',
          hId: '',
          hName: '',
          dcId: '',
          dcName: '',
          depId: '',
          depName: '',
          reMoney: '',
          startTime: '',
          endTime: '',
          docPhoto: '',
          docIntroduction:''
        },
        depClassList:[],
        hospitalList:[],
        depList:[],
        dataRule: {
          dName: [
            { required: true, message: '医生姓名不能为空', trigger: 'blur' }
          ],
          hId: [
            { required: true, message: '医院id不能为空', trigger: 'blur' }
          ],
          hName: [
            { required: true, message: '医院姓名不能为空', trigger: 'blur' }
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
          startTime: [
            { required: true, message: '上班时间不能为空', trigger: 'blur' }
          ],
          endTime: [
            { required: true, message: '下班时间不能为空', trigger: 'blur' }
          ],
          docPhoto: [
            { required: true, message: '医生照片不能为空', trigger: 'blur' }
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

      getDepIdList () {
        this.$http({
          url: 'http://localhost:10000/goodf/deparment/idList',
          method: 'get'
        }).then(({data}) => {
              if (data && data.code === 0) {
                this.depList = data.list;
              }
            })
      },

      getHospitalIdList () {
        this.$http({
          url: 'http://localhost:10000/goodf/hospital/idList',
          method: 'get'
        }).then(({data}) => {
              if (data && data.code === 0) {
                this.hospitalList = data.list;
              }
            })
      },

      init (id) {
        this.dataForm.did = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.did) {
            this.$http({
              url: `http://localhost:10000/goodf/doctor/info/${this.dataForm.did}`,
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
                this.dataForm.startTime = data.doctor.startTime
                this.dataForm.endTime = data.doctor.endTime
                this.dataForm.docPhoto = data.doctor.docPhoto
                this.dataForm.docIntroduction = data.doctor.docIntroduction
              }
            })
          }
        })
      },

      handleAvatarSuccess(res, file) {
        this.dataForm.docPhoto = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },

      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: `http://localhost:10000/goodf/doctor/${!this.dataForm.dId ? 'save2' : 'update'}`,
              method: 'post',
              data: this.$http.adornData({
                'dId': this.dataForm.did || undefined,
                'dName': this.dataForm.dName,
                'hId': this.dataForm.hId,
                'hName': this.dataForm.hName,
                'dcId': this.dataForm.dcId,
                'dcName': this.dataForm.dcName,
                'depId': this.dataForm.depId,
                'depName': this.dataForm.depName,
                'reMoney': this.dataForm.reMoney,
                'startTime': this.dataForm.startTime,
                'endTime': this.dataForm.endTime,
                'docPhoto': this.dataForm.docPhoto,
                'docIntroduction': this.dataForm.docIntroduction
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
      this.getDepIdList();
      this.getHospitalIdList();
    },
  }
</script>
