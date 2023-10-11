<template>
    <div class="main" v-if="this.$store.state.user != null">
        <!-- 收藏数为0时 -->
        <el-empty v-if="this.postList == null" description="快去寻找宝藏贴！" />
         <PostSlot :toList="postList">
            <template v-slot:post></template>
         </PostSlot>
    </div>
    <div v-if="this.$store.state.user == null" style="width:300px;margin: 0 auto; padding-top:200px;text-align:center">
        <img src="@/assets/img/login.png"><br>
        请先登录
    </div>
</template>

<script>
import request from '@/utils/request'
import PostSlot from '@/components/PostSlot.vue'
export default {
    components: { 
        PostSlot,
    },
    data () {
      return { 
        postList: null,
      }
    },
    methods: { 
        getCollect() {
            request({
                method: "get",
                url: "post/collect",
            }).then( response => {
                this.postList = response.data.data;
            })
        }
    },
    watch: { 

    },
    created () { 
        if (this.$store.state.user != null) {
            this.getCollect();
        }
    },
    mounted () { 

    },
}
</script>

<style scoped>
.main {
    width: 82%;
    margin: 0 auto;
    padding:55px 10px 0;
}
</style>
