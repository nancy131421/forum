<template>
    <slot name="post">
        <div class="line" v-for="(item,index) in this.postList" :key="index" @click="turnToPost(index)">
            <div class="head-img">
                <img @click.stop="turnToPerson(item)" :src="require('@/assets/img/' + item.headUrl + '.jpg')">
                <span @click.stop="turnToPerson(item)">{{ item.nickName }}</span>
            </div>
            <div class="date">{{ item.createDate }}</div>
            <h2>{{ item.title }}</h2>
            <div class="body">{{ item.content }}</div>   
            <div class="foot">
                <div title="喜欢" @click.stop="good(index,item.id)"><el-icon :class="{good: item.isGood}" :size="25" style="vertical-align:middle;"><Star /></el-icon>{{ item.good }}</div>
                <div title="收藏" @click.stop="collect(index,item.id)"><el-icon :size="25" :class="{collect: item.isCollect}" style="vertical-align:middle;"><CollectionTag /></el-icon>{{ item.collect }}</div>
                <div title="讨论"><el-icon :size="25" style="vertical-align:middle;"><ChatDotRound /></el-icon>{{ item.discuss }}</div>
                <div title="浏览量"><el-icon :size="25" style="vertical-align:middle;"><View /></el-icon>{{ item.view  }}</div>
            </div>
        </div> 
    </slot>
</template>

<script lang="ts">
import request from '@/utils/request';
import { ElMessage} from 'element-plus'
export default {
    props: {
        toList: {},
    },
    setup() {
        //消息
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
        postList: null,
        sure: false,
      }
    },
    watch: {
        toList: {
            handler:function(newVal) {
                if (newVal != null) {
                    this.postList = newVal
                }
            },
        }
    },
    created () {
    },
    mounted () {
        
    },
    computed: {

    },
    methods: {
      //跳转至个人界面
      turnToPerson(item){
        let p = {
            "id": item.createId,
            "nickName": item.nickName,
            "headUrl": item.headUrl,
            "signature": item.signature,
        }
        this.$router.push({
            name: "person",
            state: {
                person: JSON.stringify(p),
            },
        });
      },    


      //跳转到帖子
      turnToPost(index) {
        //浏览量增加
        request({
            method: "put",
            url: "post/view/" + this.postList[index].id,
        });

        
        this.$router.push({
            name: "detail",
            state: {
                post: JSON.stringify(this.postList[index]),
            }
        });
      },

      //点赞
      good(index,id) {
        if (this.$store.state.user == null) {
            this.warn("您未登录，请先登录！");
        }else {
          request({
            method: "put",
            url: "post/good/" + id,
          }).then( response => {
            if (response.data.data) {
              this.postList[index].good++;
              this.success("点赞成功");
              this.postList[index].isGood = true; 
            } else {
              this.postList[index].good--;
              this.def("取消点赞")
              this.postList[index].isGood = false; 
            }
          })
        }
      },


      //收藏
      collect(index,id) {
        if (this.$store.state.user == null) {
            this.warn("您未登录，请先登录！");
        } else {
            request({
                method: "put",
                url: "post/collect/" + id,
            }).then (response => {
                if (response.data.data) {
                    this.postList[index].isCollect = true;
                    this.postList[index].collect++;
                    this.success("收藏成功");
                }else {
                    if (this.$route.path == '/collect') {
                        this.postList.splice(index,1);
                    }else {
                        this.postList[index].isCollect = false;
                        this.postList[index].collect--;
                    }
                    this.def("取消收藏");
                    
                }
            })
        }
      },
    },
    components: {
    }
}
</script>

<style scoped>
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
    cursor: pointer;
}
.line > h2{
    margin: 10px auto;
}
.line > div:not(:first-child){
    margin: 10px auto;
}

.good {
    color: red;
}
.collect {
    color: red;
}
.body {
    font-size: 17px;
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
    height: 25px;
}
/* 头像 */
.head-img{
    display: flex;
    height: 45px;
    margin-top: 5px;
    line-height: 45px;
}
.head-img span:hover {
    color: red;
}
.line img {
    width: 45px;
    height: 45px;
    border-radius: 50%;
    margin-right: 5px;
}
.date {
    font-size: 13px;
    color: gray;
}
.foot {
    display: flex;
    justify-content: space-between;
    width: 80%;
}
</style>
