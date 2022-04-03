<template>
  <div class="app-container">
    <h2>活动发布</h2>
    <el-form ref="form" :model="form" :rules="rules" label-width="120px" style="margin-top: 50px;">
      <el-row>
        <el-col :span="12">
          <el-form-item label="活动名称" prop="activityName">
            <el-input v-model="form.activityName" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="活动标签" prop="activityTag">
            <el-input v-model="form.activityTag" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="活动地点">
            <el-input v-model="form.activitiyLocation" placeholder="请输入活动地点" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="报名要求">
            <el-input v-model="form.requirement" placeholder="请输入报名要求" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="联系人" prop="contactName">
            <el-input v-model="form.contactName" placeholder="请输入联系人" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="联系电话" prop="contactPhone">
            <el-input v-model="form.contactPhone" placeholder="请输入联系电话" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="开始时间" prop="beginTime">
            <el-date-picker
                clearable size="small"
                v-model="form.beginTime"
                type="datetime"
                value-format="yyyy-MM-dd HH:mm:ss"
                placeholder="选择活动开始时间"
                style="width: 100%;"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="结束时间" prop="endTime">
            <el-date-picker
                clearable size="small"
                v-model="form.endTime"
                type="datetime"
                value-format="yyyy-MM-dd HH:mm:ss"
                placeholder="选择活动结束时间"
                style="width: 100%;"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="截止时间" prop="deadline">
            <el-date-picker
                clearable size="small"
                v-model="form.deadline"
                type="datetime"
                value-format="yyyy-MM-dd HH:mm:ss"
                placeholder="选择报名截止时间"
                style="width: 100%;"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="活动人数" prop="allowCount">
            <el-input-number style="display: flex" v-model="form.allowCount" :min="1"></el-input-number>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="活动积分" prop="activitiyPoint">
            <el-input v-model="form.activitiyPoint" placeholder="请输入活动积分" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="备注" prop="remark">
            <el-input v-model="form.remark" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="活动内容">
            <el-input type="textarea" :autosize="textSize" v-model="form.activityContent"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="上传图片" prop="picture">
            <el-upload
                style="text-align: left"
                ref="picture"
                :file-list="picturefileList"
                :action="pictureAction"
                :on-success="handleUploadSuccess"
                :before-upload="pictureBeforeUpload"
                :on-preview="handlePictureCardPreview"
                list-type="picture-card"
                accept="image/*"
            >
              <i class="el-icon-plus"></i>
              <div slot="tip" class="el-upload__tip">只能上传不超过 2MB 的image/*文件</div>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible">
              <img width="100%" :src="dialogImageUrl" alt="">
            </el-dialog>
          </el-form-item>
        </el-col>
      </el-row>


      <el-col :span="24">
        <el-form-item size="large">
          <el-button type="primary" @click="onSubmit">提交</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-col>
    </el-form>

  </div>
</template>

<script>

export default {
  name: "release",
  data() {
    return {
      /*图片上传*/
      dialogImageUrl: '',
      dialogVisible: false,
      textSize:{ minRows: 7 },
      form: {
        activityName: undefined,
        activityTag: '其他',
        activitiyLocation: undefined,
        requirement: '无',
        contactName: undefined,
        contactPhone: undefined,
        beginTime: null,
        endTime: null,
        deadline: null,
        remark: undefined,
        activitiyPoint: 10,
        allowCount: 1,
        picture: null,
        activityContent: undefined,
      },
      rules: {
        activityName: [{
          required: true,
          message: '请输入活动名称',
          trigger: 'blur'
        }],
        activityTag: [{
          required: true,
          message: '请输入活动标签',
          trigger: 'blur'
        }],
        activitiyLocation: [],
        requirement: [],
        contactName: [],
        contactPhone: [],
        beginTime: [{
          required: true,
          message: '请选择开始时间',
          trigger: 'change'
        }],
        endTime: [{
          required: true,
          message: '请选择结束时间',
          trigger: 'change'
        }],
        deadline: [{
          required: true,
          message: '请选择截止时间',
          trigger: 'change'
        }],
      },
      pictureAction: 'http://localhost:8888/upload/uploadPicture',
      picturefileList: [],
    }
  },
  methods: {
    onSubmit() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.submitUpload();
          this.axios.post("http://localhost:8888/activity/insert",this.form).then(response => {
              this.$message({
                message: '活动创建成功！',
                type: 'success'
              });
              this.resetForm();
            });
          }
      });
    },
    resetForm() {
      this.$refs['form'].resetFields();
      this.$refs['picture'].clearFiles();
    },
    pictureBeforeUpload(file) {
      let isRightSize = file.size / 1024 / 1024 < 2
      if (!isRightSize) {
        this.$message.error('文件大小超过 2MB')
      }
      let isAccept = new RegExp('image/*').test(file.type)
      if (!isAccept) {
        this.$message.error('应该选择image/*类型的文件')
      }
      return isRightSize && isAccept
    },
    submitUpload() {
      this.$refs['picture'].submit()
    },
    handleUploadSuccess(res, file) {
      if(res.url){
        this.form.picture = res.url;
      }
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    }
  }
}
</script>

<style scoped>
.el-upload__tip {
  line-height: 1.2;
}
.line{
  text-align: center;
}
.app-container {
  padding: 20px;
}
</style>
