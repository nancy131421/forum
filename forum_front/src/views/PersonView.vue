<template>
    <MainSlot v-if="user != null">
        <template v-slot:left>
            <div class="content">
                <div v-if="postList.length != 0">
                    <span @click="timeSort()" :class="{active: pick1}">按时间排序</span> | <span @click="hotSort()" :class="{active: pick2}">按热度排序</span>
                </div>
                <el-empty v-if="postList.length == 0" description="目前暂无数据！" />
                <br>
                <PostSlot :toList="postList">
                    <template v-slot:post></template>
                </PostSlot>
            </div>
        </template>
        <template v-slot:right>
            <div class="info">
                <div class="head">
                    <img :src="require('@/assets/img/' + user.headUrl + '.jpg')">
                </div>
                <div class="nick-name">{{ user.nickName }}</div>
                <div class="mes">{{ user.signature }}</div>
            </div>
        </template>
    </MainSlot>
    <div v-if="user == null" style="width:300px;margin: 0 auto; padding-top:200px;text-align:center">
        <img src="@/assets/img/login.png"><br>
        请先登录
    </div>
</template>

<script>
import MainSlot from '@/components/MainSlot.vue';
import PostSlot from '@/components/PostSlot.vue';
import request from '@/utils/request';
export default {
    components: { 
        MainSlot,
        PostSlot,
    },
    data () {
      return { 
        //显示红色
        pick1: true,
        pick2: false,

        //帖子列表
        postList: [],

        //当前用户
        user: null,

      }
    },
    methods: { 
        // 按时间排序
        timeSort() {
            this.pick1 = true;
            this.pick2 = false;
            request({
                method: "get",
                url: "post/time/" + this.user.id,
            }).then( response => {
                this.postList = response.data.data;
            })
        },
        // 按热度排序
        hotSort() {
            this.pick1 = false;
            this.pick2 = true;
            request({
                method: "get",
                url: "post/hot/" + this.user.id,
            }).then(response => {
                this.postList = response.data.data;
            })
        },
    },
    watch: { 

    },
    created () { 
        //更换用户
        if (history.state.person != undefined) {
            this.user = JSON.parse(history.state.person);
        }else if (this.$store.state.user != null) {
            this.user = this.$store.state.user;
        }

        //初始时，用户不为空则查询
        if (this.user != null) {
            this.timeSort();
        }
    },
    mounted () { 

    },
}
</script>

<style scoped>
.content {
    padding: 10px;
} 
/* 每一行的样式 */
.line {
    width: 100%;
    height: 200px;
    margin-bottom: 20px;
    user-select: none;
    border: 1px solid black;
    padding-left: 5px;
}
.line:hover {
    border: 1px solid red;
}
.line > div,h2{
    margin: 20px auto;
}

.content span {
    user-select: none;
}
.active {
    color: red;
}

/* 每一行内容样式 */
.body {
    font-size: 17px;
    text-overflow: ellipsis;
    height: 25px;
}
.date {
    font-size: 14px;
    color: gray;
}
.foot {
    display: flex;
    justify-content: space-between;
    width: 80%;
}
.foot div {
    vertical-align:middle;
}

/* 个人信息css */
.info {
    position: fixed;
    top: 55px;
    width: 22%;
}
.head {
    width: 150px;
    height: 150px;
    margin: 40px auto;
}
.head img {
    width: 150px;
    height: 150px;
    border-radius: 50%;
}
.nick-name {
    text-align: center;
    font-size: 20px;
}
.mes {
    margin-top: 20px;
    text-align: center;
    font-size: 14px;
}
.num {
    margin-top: 40px;
    text-align: center;
}
</style>
