<template>
    <div class="box" @keydown.enter="login()">
        <div class="login_table">
            <div>
                <span class="title">Login</span>
            </div>
            <div>
                账号：
                <input type="text" placeholder="请输入账号" v-model="loginInfo.account">
            </div>
            <div>
                密码：
                <input type="password" placeholder="请输入密码" v-model="loginInfo.password">
            </div>
            <div>
                <el-button type="primary" size="large" round @click="sign()">注册</el-button>
                <el-button type="success" size="large" round style="margin-left: 40px;" @click="login()">登录</el-button>
            </div>
        </div>
        <video muted autoplay loop>
            <source src="@/assets/video/login_bg.mp4">
        </video>
    </div>
</template>

<script >
import { ElMessage } from 'element-plus'

import request from '@/utils/request';
import qs from 'qs';

export default {
    setup() {
        const error = (msg) => {
            if (document.getElementsByClassName('el-message').length == 0) {
                ElMessage.error({
                    message: msg,
                    duration: 1500,
                    }); 
            }
        };

        return {
            error,
        }
    },
    data () {
        return {
            //登录信息
            loginInfo:{
                account: '',
                password: ''
            }
        }
    },
    watch: {
        
    },
    created () { },
    mounted () { },
    methods: {
        show() {
            alert(1);
        },
        // 登录方法
        login() {
            if(this.loginInfo.account == '' || this.loginInfo.password == '') {
                this.error("账户或密码不能为空")
            }
            request({
                method: "get",
                url: "user/login?" + qs.stringify(this.loginInfo),
            }).then (response => {
                let data = response.data;
                if (data.code == 200) {
                    localStorage.setItem("user",JSON.stringify(data.data));
                    sessionStorage.setItem("token",data.data.account);
                    this.$store.state.user = JSON.parse(localStorage.getItem("user"));
                    this.$router.replace("/home");
                }else {
                    this.error(data.msg);
                }
            })
            
        },

        //注册方法
        sign() {
            this.$router.push("/sign");
        }
    },
    components: {}
}
</script>

<style scoped src="../style/login.css">

</style>
