<template>

</template>

<script>
export default {
  name: "index",
  mounted() {
    this.$store.commit('setUserData', {})
    this.$router.push('/')
    this.$message.success('成功登出')
  }
}
</script>

<style scoped>

</style>