<template>
  <div class="main" id="main">
    <h1>注册账户</h1>
    <div class="sign">
      <div>
        账<span/><span/>号:
        <input type="text" maxlength="11" placeholder="请输入账号(最大长度11)" v-model="signInfo.account">
      </div>
      <div>
        昵<span/><span/>称:
        <input type="text" maxlength="20" placeholder="请输入昵称(最大长度20)" v-model="signInfo.nickName">
      </div>
      <div>
        密<span/><span/>码:
        <input type="password" placeholder="请输入密码" v-model="signInfo.password">
      </div>
      <div>
        确认密码:
        <input type="password" placeholder="请确认密码" v-model="repassword">
      </div>
      <div>
        个性签名:
        <input type="text" maxlength="25" placeholder="请输入个性签名(最大长度为25)" v-model="signInfo.signature">
      </div>
      <div style="height:260px">
        上传头像:
        <input type="text" style="border:none;background-color:white" disabled ><br>
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8080/user/upload"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <img v-if="imageUrl" :src="imageUrl" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </div>    
      <div>
        <el-button type="danger" size="large" @click="reset()" style="margin-right:80px">重置</el-button>
        <el-button type="success" size="large" @click="sign()">注册</el-button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import request from '@/utils/request'
import { ref } from 'vue'
import { ElMessage } from 'element-plus'

import type { UploadProps } from 'element-plus'
export default {
  setup() {
    // 头像
    const imageUrl = ref('')
    const handleAvatarSuccess: UploadProps['onSuccess'] = (
      Response,
      uploadFile
    ) => {
      imageUrl.value = URL.createObjectURL(uploadFile.raw!);
    }
    const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {
      if (rawFile.type !== 'image/jpeg') {
        ElMessage.error('头像必须为jpg文件！')
        return false
      } else if (rawFile.size / 1024 / 1024 > 2) {
        ElMessage.error('头像文件不能大于2MB！')
        return false
      }
      return true
    }

    // 错误信息提示 
    const error = (msg) => {
      ElMessage.closeAll();
      ElMessage.error({
        message: msg,
        duration: 1500,
      })
    }
    //成功信息提示
    const success = (msg) => {
      ElMessage({
        message: msg,
        type: 'success',
        duration: 1500,
      })
    }
    
    // 返回
    return {
      handleAvatarSuccess,
      beforeAvatarUpload,
      imageUrl,
      error,
      success,
    }
  },
    components: { 

    },
    data () {
      return { 
        //注册信息
        signInfo: {
          account: "",
          nickName: "",
          password: "",
          signature: "",
        },
        //确认密码
        repassword: '',
      }
    },
    methods: { 
      //重置
      reset() {
        this.signInfo.account = "";
        this.signInfo.nickName = "";
        this.signInfo.password = "";
        this.signInfo.signature = "";
        this.repassword = "";

      },
      // 注册
      sign() {
        if (this.signInfo.password == this.repassword) {
          request({
          method: "post",
          url: "user/sign",
          data: this.signInfo
          }).then( response => {
            let data = response.data;
            if (data.code == 0) {
              this.error(data.msg);
            }
            if (data.code == 200) {
               this.success(data.msg);
               this.$router.push("/login");
            }
          });
        }else {
          this.error("两次密码不一致")
        }
      },
    },
    watch: { 

    },
    created () { 
      
    },
    mounted () { 
      
    },
}
</script>

<style scoped>
span {
  margin-left: 1em;
}
.main {
  width: 80%;
  margin: auto;
  padding-top: 10px;
  text-align: center;
  font-size: 20px;
}

/* 注册表格css */
.sign div {
  margin: 20px 0;
}
.sign div input {
  width: 400px;
  height: 40px;
  padding-left: 5px;
}
.sign {
  width: 90%;
  border: 1px solid;
  margin: 20px auto 0;
}

/* 头像 */
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
