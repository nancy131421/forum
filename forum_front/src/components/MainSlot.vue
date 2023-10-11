<template>
    <div class="main">
      <div class="left">
        <slot name="left"></slot>
      </div>
      <div class="right">
        <slot name="right"></slot>
      </div>
    </div>
</template>
  
<script lang="ts">
export default {
    data() {
      return {
        
      }
    },
    created () { },
    mounted () { },
    methods: {},
    components: {}
}
</script>
  
<style scoped>

.main {
  width: 80%; 
  margin: 0 auto;
  display: flex;
  padding-top: 55px;
}
.left {
  width: 70%;
  
}
.right {
  width: 30%;
} 
</style>