<template>
  <div class="header">
    <div class="category">
      <ul>
        <li
          v-for="(item, index) in category"
          :key="item"
          @click="select(index)"
          :class="{ active: this.i == index }">
          {{ item }}
        </li>
      </ul>
    </div>
    <div class="right" @click="login()">
      <span v-show="this.$store.state.user == null">登录</span>
      <img
        v-if="this.$store.state.user != null"
        :src="require('@/assets/img/' + this.$store.state.user.headUrl + '.jpg')"
      />
    </div>
    <div class="operate" v-show="this.opeShow">
      <ul>
        <li @click="logout()">退出登录</li>
      </ul>
    </div>
  </div>
  <router-view />
</template>

<script>
export default {
  data() {
    return {
      // 目录数据
      i: 0,
      category: ["首页", "我的收藏", "我要发贴", "个人中心"],
      path: ["home", "collect", "post", "person"],

      //显示登录的操作框
      opeShow: false,
    };
  },
  created() {},
  mounted() {
  },
  methods: {
    // 路由跳转
    select(index) {
      this.$router.push("/" + this.path[index]);
    },

    // 登录
    login() {
      if (this.$store.state.user != null) {
        this.opeShow = !this.opeShow;
      } else {
        this.$router.push("/login");
      }
    },

    //退出登录
    logout() {
      localStorage.removeItem("user");
      sessionStorage.removeItem("token");
      this.$store.state.user = null;
      this.$router.push("/login");
    },
  },
  components: {},
  watch: {
    $route: {
      immediate: true,
      handler() {
        for (let index in this.path) {
          if (this.$route.name == this.path[index]) {
            this.i = index;
            return;
          }
        }
        this.i = -1;
      },
    },
  },
};
</script>

<style scoped>
/* 头部css */
.header {
  z-index: 1;
  height: 45px;
  width: 100%;
  min-width: 1500px;
  background-color: #37394d;
  display: flex;
  padding: 0 8%;
  justify-content: space-between;
  position: fixed;
  top: 0;
}
/* 目录样式 */
.category {
  height: 100%;
}
.category > ul {
  display: flex;
}
.category > ul > li {
  line-height: 45px;
  font-weight: 600;
  font-size: 18px;
  color: #fff;
  margin-right: 40px;
  cursor: pointer;
}
/* 鼠标悬停 */
.category > ul :hover {
  color: red;
}
/* 目录被点击选择时 */
.active {
  color: #ffb001 !important;
}

/* 导航栏右侧(登录) */
.right {
  width: 45px;
  height: 45px;
  line-height: 45px;
  text-align: center;
  user-select: none;
  border-radius: 50%;
  cursor: pointer;
  border-radius: 45px;
  background-color: #fff;
}
.right img {
  width: 45px;
  border-radius: 45px;
}

/* 点击显示框 */
.operate {
  width: 90px;
  position: fixed;
  right: 6.5%;
  top: 45px;
  text-align: center;
  background-color: #37394d;
  z-index: 10;
}
.operate > ul > li {
  height: 25px;
  line-height: 25px;
  color: white;
  user-select: none;
  margin-bottom: 5px;
}
.operate > ul > li:not(:first-of-type) {
  border-top: 1px solid white;
}
</style>