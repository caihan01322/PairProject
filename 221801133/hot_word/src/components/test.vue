<template>
<div calss="123">
  <p>123</p>
  <button @click="tiao">123</button>
</div>
</template>
<style scoped>

</style>
<script>
export default {
  methods:{
    tiao(){
      var id="Image";
      this.$router.push({path:"/sk",query:{key:id}})
    }
  }
}
</script>