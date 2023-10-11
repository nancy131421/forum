<template>
    <div class="pad">
    </div>
    <div class="main">
        <h2>帖子详情</h2>
        <div class="head">
            <img @click="turnToPerson(post)" :src="require('@/assets/img/' + post.headUrl + '.jpg')">
            <span @click="turnToPerson(post)">{{ post.nickName }}</span>
        </div>
        <div class="date">{{ post.createDate }}</div>
        <div class="title">{{ post.title }}</div>
        <div class="contetnt" style="margin: 40px 0">{{ post.content }}</div>
        <hr>

        <!-- 评论框 -->
        <div class="textArea">
            <textarea @keydown.enter="putDiscuss()" name="" id="" cols="150" rows="3" placeholder="说些什么吧" style="padding:10px 10px;resize:none" v-model="content"></textarea>
            <el-button type="primary" size="large" style="margin-left:15px" @click="putDiscuss()">评论</el-button>
        </div>

        <!-- 选择评论排序方式 -->
        <div class="type">
            <span @click="hotSort(post.id)" :class="{active: pick1}">按热度排序</span> | <span @click="timeSort(post.id)" :class="{active: pick2}">按时间排序</span>
        </div>

        <!-- 评论区 -->
        <div class="discussArea">
            <div class="line" v-for="(item,index) in first" :key="index" @click.stop="doreply(item,12)" @click.capture="getTop(item)">
                <div class="linehead">
                    <img :src="require('@/assets/img/' + item.headUrl + '.jpg')" @click.stop="turnToPerson(item)">
                    <span style="margin-left:10px" @click.stop="turnToPerson(item)">{{ item.nickName }}</span>
                </div>
                <div class="linedate">{{ item.createDate }}</div>
                <div class="linecontent">
                    {{ item.content }}
                    <div class="linegood" @click.stop="dogood(item,item.cid)">
                        <el-icon :size="20" :class="{good: item.isGood}" style="vertical-align:middle;margin-left:10px"><Star /></el-icon>
                        {{ item.good }}
                    </div>
                </div>
                <div class="linereply">
                    回复
                </div>

                <!-- 子评论 -->
                <div class="out">
                    <div class="subbary" v-show="item.isOpen" v-for="(sub,index) in item.replyList" :key="index" @click.stop="doreply(sub)">
                        <div class="linehead">
                            <img :src="require('@/assets/img/' + sub.headUrl + '.jpg')" @click.stop="turnToPerson(sub)">
                            <span style="margin-left:10px" @click.stop="turnToPerson(sub)">{{ sub.nickName }}</span>
                        </div>
                        <div class="linedate">{{ sub.createDate }}</div>
                        <!-- 评论内容 -->
                        <div class="linecontent" style="height:30px">
                            <span style="margin-right:10px;font-size:13px;color:gray" v-if="sub.rname != sub.nickName && sub.rname != item.nickName">回复</span>
                            <div class="linehead" style="height:30px;line-height:30px;margin:0;" v-if="sub.rname != sub.nickName && sub.rname != item.nickName">
                                <img :src="require('@/assets/img/' + sub.rhead + '.jpg')" @click.stop="turnToPersonSub(sub)" style="width:30px;height:30px;margin-right:10px">
                                <span @click.stop="turnToPersonSub(sub)" style="height:30px;">{{ sub.rname }}</span>:
                            </div>
                            <span style="margin-left:10px">{{ sub.content }}</span>
                            <div class="linegood" @click.stop="dogood(sub,sub.cid)">
                                <el-icon :size="20" :class="{good: sub.isGood}" style="vertical-align:middle;margin-left:10px"><Star /></el-icon>
                                {{ sub.good }}
                            </div>
                        </div>
                        <div class="linereply" style="color:salmon">
                            回复
                        </div>
                    </div>
                </div>
                <!-- 子评论结束 -->

                <div class="linesub" v-show="item.sub" @click.stop="showSub(item)">
                    <span v-show="!item.isOpen"><el-icon><CaretBottom /></el-icon>剩余{{ item.sub }}条评论</span>
                    <span v-show="item.isOpen"><el-icon><CaretTop /></el-icon>收起评论</span>
                </div>
            </div>
        </div>
    </div>

    <!-- 左侧流量信息 -->
    <ul class="info">
        <li title="喜欢" @click="good(post)"><el-icon :size="35" :class="{good: info ? info.isGood : post.isGood}" style="vertical-align:middle;"><Star /></el-icon>{{ info ? info.good : post.good }}</li>
        <li title="收藏" @click="collect(post)"><el-icon :size="35" :class="{collect: info ? info.isCollect : post.isCollect}" style="vertical-align:middle;"><CollectionTag /></el-icon>{{ info ? info.collect : post.collect }}</li>
        <li title="讨论" @click="discuss()"><el-icon :size="35" style="vertical-align:middle;"><ChatDotRound /></el-icon>{{ info ? info.discuss : post.discuss }}</li>
        <li title="浏览量"><el-icon :size="35" style="vertical-align:middle;"><View /></el-icon>{{ this.$store.state.user ? (info ? info.view + 1 : post.view + 1) : (info ? info.view : post.view) }}</li>
    </ul>

    <!-- 弹出回复框 -->
    <el-drawer v-model="drawer2" :direction="direction">
        <template #header>
            <h4>评论</h4>
        </template>
        <template #default>
            <div class="popbox">
                <span v-if="this.isReply">回复：{{ this.current.nickName }}</span> 
                <br>
                <!--  回复框内容 -->
                <textarea @keydown.enter="confirmClick()" name="" id="" cols="180" rows="2" style="resize:none;padding:5px 5px;" placeholder="说些什么吧" v-model="content1">
                </textarea>
            </div>
        </template>
        <template #footer>
        <div style="flex: auto">
            <el-button @click="cancelClick()">取消</el-button>
            <el-button type="primary" @click="confirmClick()">评论</el-button>
        </div>
        </template>
  </el-drawer>
</template>

<script>
import request from '@/utils/request';
import { ElMessage} from 'element-plus'
import { ref } from 'vue';
export default {
    components: {

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
        
        const direction = ref('btt')

        return {
            warn,
            success,
            def,
            direction,
        }
    },
    data () {
      return { 
        post: null,
        info: null,

        //显示红色
        pick1: true,
        pick2: false,

        content: null,

        //评论弹出框
        drawer2: false,
        content1: null,
        //当前评论
        current: null,

        // 一级评论
        first: [],

        isReply: false,

        //当前子评论对应的最顶级评论
        top: null,
      }
    },
    methods: { 
        // 按时间排序
        timeSort(id) {
            this.pick1 = false;
            this.pick2 = true;
            request({
                method: "get",
                url: "comment/timeSort/" + id,
            }).then( response => {
                this.first = response.data.data;
            })
        },
        // 按热度排序
        hotSort(id) {
            this.pick1 = true;
            this.pick2 = false;
            request({
                method: "get",
                url: "comment/hotSort/" + id,
            }).then( response => {
                this.first = response.data.data;
            })
        },


        //点赞
      good(post) {
        if (this.$store.state.user == null) {
            this.warn("您未登录，请先登录！");
        }else {
          request({
            method: "put",
            url: "post/good/" + post.id,
          }).then( response => {
            if (response.data.data) {
              this.info ? this.info.good++ : post.good++;
              this.success("点赞成功");
              this.info ? this.info.isGood = true : post.isGood = true; 
            } else {
                this.info ? this.info.good-- : post.good--;
              this.def("取消点赞")
              this.info ? this.info.isGood = false : post.isGood = false; 
            }
          })
        }
      },


      //收藏
      collect(post) {
        if (this.$store.state.user == null) {
            this.warn("您未登录，请先登录！");
        } else {
            request({
                method: "put",
                url: "post/collect/" + post.id,
            }).then (response => {
                if (response.data.data) {
                    this.info ? this.info.isCollect = true : post.isCollect = true;
                    this.info ? this.info.collect++ : post.collect++;
                    this.success("收藏成功");
                }else {
                    this.info ? this.info.isCollect = false : post.isCollect = false;
                    this.info ? this.info.collect-- : post.collect--;
                    this.def("取消收藏");
                    
                }
            })
        }
      },

      
      //隐藏评论弹出框
      cancelClick() {
        this.drawer2 = false;
        this.isReply = false;
      },

      //弹出框发表评论
     async confirmClick() {
        let comment = {};
        if (this.$store.state.user == null) {
            this.warn("您未登录，请先登录！")
        }else {
            if (this.content1 == null) {
                this.warn("评论不能为空!")
            }else {
                //此为回复评论
                if (this.isReply) {
                    comment = {
                        content: this.content1,
                        postId: this.post.id,
                        replyId: this.current.uid,
                        topId: this.current.topId == -1 ? this.current.cid : this.current.topId,
                    }
                }else {
                    comment = {
                        content: this.content1,
                        postId: this.post.id,
                        replyId: -1,
                        topId: -1,
                    }
                }
            }
            await request({
                method: "post",
                url: "comment/insert",
                data: comment,
            }).then( response => {
                if (this.isReply) {
                    //刷新子评论
                    request({
                        method: "get",
                        url: "comment/findSub/" + this.post.id + "/" + this.current.topId,
                    }).then( response => {
                        this.top.replyList = response.data.data;
                    })
                }else {
                    if (this.pick1) {
                        this.hotSort(this.post.id);
                    }else {
                        this.timeSort(this.post.id);
                    }
                }
                this.post.discuss++;
                this.content1 = null;
                this.success(response.data.msg);
            })
        }
        
        //关闭弹出框
        this.drawer2 = false;
        //去除回复标签
        this.isReply = false;
      },

      //回复评论
      doreply(item) {
        this.current = item;
        this.drawer2 = true;
        this.isReply = true;
      },
      //获取子评论的最顶级评论
      getTop(item) {
        this.top = item;
      },

      //点击左侧评论图标
      discuss() {
        if (this.$store.state.user == null) {
            this.warn("您未登录，请先登录！");
        } else {
            this.isReply = false;
            this.drawer2 = true;
        }
      },


      //发表评论
      putDiscuss() {
        if (this.$store.state.user == null) {
            this.warn("您未登录，请先登录！")
        }else {
            if (this.content == null) {
                this.warn("评论不能为空!")
            }else {
                let comment = {
                    content: this.content,
                    postId: this.post.id,
                    replyId: -1,
                    topId: -1,
                }
                request({
                    method: "post",
                    url: "comment/insert",
                    data: comment,
                }).then( response => {
                    this.post.discuss++;
                    if (this.pick1) {
                        this.hotSort(this.post.id);
                    }else {
                        this.timeSort(this.post.id);
                    }
                    this.content= null;
                    this.success(response.data.msg);
                })
            }
        }
      },


    //跳转个人主页
    turnToPerson(item) {
        let p = {
            "id": item.createId ? item.createId : item.uid,
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
    //跳转个人主页(子评论版)
    turnToPersonSub(sub) {
        let p = {
            "id": sub.rid,
            "nickName": sub.rname,
            "headUrl": sub.rhead,
            "signature": sub.rsignature,
        }
        this.$router.push({
            name: "person",
            state: {
                person: JSON.stringify(p),
            },
        });
    },


    //评论点赞
    dogood(item,id) {
        request({
            method: "put",
            url: "comment/doGood/" + id,
        }).then( response => {
            if (response.data.data) {
                //点赞成功
                item.good++;
                item.isGood = true;
                this.success("点赞成功");
            } else {
                item.good--;
                item.isGood = false;
                this.success("取消点赞");
            }
        })
    },


    //展示子评论
    showSub(item) {
        item.isOpen = !item.isOpen;
        if (item.isOpen) {
            request({
                method: "get",
                url: "comment/findSub/" + item.postId + "/" + item.cid
            }).then( response => {
                item.replyList = response.data.data;
            })
        }
    }
    },
    watch: { 

    },
    created () { 
        this.post = JSON.parse(history.state.post);
        if (history.state.info) {
            this.info = JSON.parse(history.state.info)
        }

        //获取当前帖子一级评论
        this.hotSort(this.post.id);
    },
    mounted () { 

    },
}
</script>

<style scoped>
.pad {
    width: 82%;
    height: 10px;
    margin: 0 auto;
    padding:55px 0 0;
}

/* 左侧图标 */
.info {
    width: 9%;
    position: fixed;
    top: 150px;
    padding-left: 3%;
}
.info li {
    margin-top: 80px;
    user-select: none;
    cursor: pointer;
}
.good {
    color: red;
}
.collect {
    color: red;
}

/* 内容 */
.main {
    width: 82%;
    margin: 0 auto; 
    padding: 0 10px 0;
    border: 1px solid black;
}
.main > * {
    margin-bottom: 10px;
}
.head {
    display: flex;
    height: 60px;
    line-height: 60px;
    font-size: 20px;
    cursor: pointer;
}
.head span:hover {
    color: red;
}
.head img {
    width: 60px;
    height: 60px;
    border-radius: 50%;
    margin-right: 10px;
}
.date{
    font-size: 15px;
    color: gray;
}
.title {
    font-size: 28px;    
    font-weight: 500;
}

/*  评论框 */
.textArea{
    display: flex;
    height: 65px;
    align-items: center;
}
/* 评论弹出框 */
.popbox {
    height: 45px;
}

/* 评论 */
.active {
    color: red;
}
.type {
    margin: 20px 0;
}
.type span  {
    cursor: pointer;
}

/* 每条评论样式 */
.line {
    margin-bottom: 20px;
    border-bottom: 1px solid #000;
    border-width: 80%;
    cursor: pointer;
}
.line div {
    margin: 5px 0;
}
.linehead {
    height: 40px;
    display: flex;
    line-height: 40px;
}

.linehead span:hover {
    color: red;
}
.linehead img {
    width: 40px;
    height: 40px;
    border-radius: 50%;
}
.linedate{
    font-size: 13px;
    color: gray;
}
.linecontent {
    display: flex;
    height: 32px;
    line-height: 32px;
}
.linegood {
    display: flex;
    height: 25px;
    line-height: 25px;
}
.linereply {
    display: flex;
    height: 20px;
    line-height: 20px;
    justify-content: end;
    padding-right: 40px;
    color: deepskyblue;
}
.linesub {
    margin: 0 0 !important;
    border: 1px solid gray;
    height: 17px;
    text-align: center;
    font-size: 12px;
}

/* 子评论样式 */
.subbary {
    width: 93%;
    height: 128px;
    margin-left: 7% !important;
    border-bottom: 1px solid #000;
}
</style>
