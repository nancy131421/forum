<template>
    <MainSlot>
      <template v-slot:left>
        <PostSlot :toList="postList">
          <template v-slot:post></template>
        </PostSlot>
      </template>
      <template v-slot:right>
        <div class="hot">
          <div class="header">本站热榜<el-icon color="#ffb001" style="vertical-align: middle"><Medal/></el-icon></div>
          <div class="content" v-for="(item,index) in top10" :key="index" @click="turnToPost(index)">
            {{ index+1 }}&nbsp;{{ item.title }}
          </div>
        </div>
      </template>
    </MainSlot>
</template>
  
<script lang="ts">
import request from '@/utils/request';
import MainSlot from '@/components/MainSlot.vue';
import PostSlot from '@/components/PostSlot.vue';
import { ElMessage } from 'element-plus'
export default {
    setup() {
        const warn = (msg) => {
            if (document.getElementsByClassName('el-message').length == 0) {
                ElMessage({
                message: msg,
                type: 'warning',
                duration: 1500,
                })
            }
        };
        const success = (msg) => {
            ElMessage.closeAll();
            ElMessage({
            message: msg,
            type: 'success',
            duration: 1500,
            })
        };
        const def = (msg) => {
            ElMessage.closeAll();
            ElMessage({
            message: msg,
            type: 'info',
            duration: 1500,
            })
        };

        return {
            warn,
            success,
            def,
        }
    },
    data() {
      return {
        //全部帖子
        postList: [],
        //热榜top10
        top10: [],

        postInfo: [],
      }
    },  
    created () {
      this.getAll();
      this.getHot();
    },
    mounted () {
     },
    methods: {
      //获取全部帖子
      getAll() {
        request({
          method: "get",
          url: "/post/findAll",
        }).then(response => {
          this.postList = response.data.data;
        })
      },
      //获取热榜
      getHot() {
        request({
          method: "get",
          url: "/post/top10",
        }).then(response => {
          this.top10 = response.data.data;
        })
      },


      //跳转到帖子
      async turnToPost(index) {
         //浏览量增加
         request({
            method: "put",
            url: "post/view/" + this.top10[index].id,
        });

        //获取流量信息
        await request({
          method: "get",
          url: "post/info/" + this.top10[index].id,
        }).then ( response => {
          this.postInfo = response.data.data;
        })
        
        this.$router.push({
            name: "detail",
            state: {
                info: JSON.stringify(this.postInfo),
                post: JSON.stringify(this.top10[index]),
                
            }
        });
      },
    },
    watch: {
       
    },
    components: {
      MainSlot,
      PostSlot,
    }
}
</script>
  
<style scoped>
/* 热榜css */
.hot {  
  position: fixed;
  width: 21%;
  margin-left: 1%;
  border: 1px solid black;
}
.header {
  text-align: center;
  font-size: 25px;
  font-weight: 600;
}
.content {
  cursor: pointer;
  height: 40px;
  width: 100%;
  padding: 0 5%;
  margin-top: 10px;
  line-height: 40px;
}
.content:not(:last-child) {
  border-bottom: 1px solid black;
}


/* 右侧热榜css */
.content {
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}
</style>