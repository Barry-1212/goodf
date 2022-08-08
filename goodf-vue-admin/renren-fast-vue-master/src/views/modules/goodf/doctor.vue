<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <!-- <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item> -->
      <el-form-item>
        <el-button @click="getDataList()">刷新</el-button>
        <el-button v-if="isAuth('goodf:doctor:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('goodf:doctor:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="did"
        header-align="center"
        align="center"
        label="医生ID">
      </el-table-column>
      <el-table-column
        prop="dName"
        header-align="center"
        align="center"
        label="医生姓名">
      </el-table-column>
      <el-table-column
        prop="hId"
        header-align="center"
        align="center"
        label="医院ID">
      </el-table-column>
      <el-table-column
        prop="hName"
        header-align="center"
        align="center"
        label="所属医院">
      </el-table-column>
      <el-table-column
        prop="dcId"
        header-align="center"
        align="center"
        label="科室分类ID">
      </el-table-column>
      <el-table-column
        prop="dcName"
        header-align="center"
        align="center"
        label="科室分类">
      </el-table-column>
      <el-table-column
        prop="depId"
        header-align="center"
        align="center"
        label="科室ID">
      </el-table-column>
      <el-table-column
        prop="depName"
        header-align="center"
        align="center"
        label="科室">
      </el-table-column>
      <el-table-column
        prop="reMoney"
        header-align="center"
        align="center"
        label="挂号费">
      </el-table-column>
      <el-table-column
        value-format="yyyy-MM-dd HH:mm:ss"
        :formatter="timeFormat2"
        prop="startTime"
        header-align="center"
        align="center"
        label="上班时间">
      </el-table-column>
      <el-table-column
        value-format="yyyy-MM-dd HH:mm:ss"
        :formatter="timeFormat"
        prop="endTime"
        header-align="center"
        align="center"
        label="下班时间">
      </el-table-column>
      <el-table-column
        prop="docPhoto"
        header-align="center"
        align="center"
        label="医生照片">
      </el-table-column>
      <el-table-column
        prop="docIntroduction"
        header-align="center"
        align="center"
        label="医生介绍">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.did)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.did)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './doctor-add-or-update'
  export default {
    data () {
      return {
        dataForm: {
          key: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    activated () {
      this.getDataList()
    },
    methods: {
      //格式化时间
      timeFormat(cellValue){
        return cellValue.endTime.split(' ')[1];
      },
      timeFormat2(cellValue){
        return cellValue.startTime.split(' ')[1];
      },
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
           url: 'http://localhost:10000/goodf/doctor/list',
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.did
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: 'http://localhost:10000/goodf/doctor/delete',
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      }
    }
  }
</script>
