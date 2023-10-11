<template>
    <div class="main" v-if="this.$store.state.user != null">
        <div class="header">标题：<input type="text" v-model="post.title"></div>
        <div class="content">
            <textarea name="content" id="main" cols="165" rows="33" style="padding:10px;margin-top:20px;resize:none;width:" v-model="post.content"></textarea>
        </div>

        <div class="operation">
            <el-button type="info" size="large" @click="reset()">重置</el-button>
            <el-button type="primary" size="large" style="margin-left: 100px" @click="publish()">发布</el-button>
        </div>
    </div>
    <div v-if="this.$store.state.user == null" style="width:300px;margin: 0 auto; padding-top:200px;text-align:center">
        <img src="@/assets/img/login.png"><br>
        请先登录
    </div>
</template>

<script lang="ts">
import request from '@/utils/request';
import { ElMessage } from 'element-plus'
export default {
    setup() {
        const war = (msg) => {
            if (document.getElementsByClassName('el-message').length == 0)
                ElMessage({
                    message: msg,
                    type: 'warning',
                    duration: 1500,
                })
        };
        const success = (msg) => {
            ElMessage({
                message: msg,
                type: 'success',
                duration: 1500,
            });
        }
        return {
            war,
            success,
        }
    },
    components: { 

    },
    data () {
      return { 
        post: {
            title: '',
            content: '',
        }
      }
    },
    methods: { 
        //重置
        reset() {
            this.post.title = '';
            this.post.content = '';
        },
        //发布帖子
        publish() {
            if (this.post.title == '') {
                this.war("标题不能为空");
            }else {
                request({
                    method: "post",
                    url: 'post/up',
                    data: this.post
                }).then (response => {
                    this.success(response.data.msg);
                    this.post.title = '';
                    this.post.content = '';
                })
            }
        }
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
.main {
    width: 80%;
    height: 100%;
    padding-top: 55px;
    margin: 0 auto;
}

/* 头部css */
 .header {
    font-size: 20px;
 }
 .header input {    
    width: 95%;
    height: 35px;
 }
 .content {
    width: 100%;
    
 }
 
 .operation {
    margin: 40px; 
    display: flex;
    justify-content: center;
 }
</style>
