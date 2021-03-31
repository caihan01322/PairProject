<template>
    <div class='home'>
<!--        <br /><br />-->
<!--        <br /><br />-->
<!--        <br /><br />-->
<!--        <br /><br />-->
<!--        <br /><br />-->
<!--        <br /><br />-->
<!--        <br /><br />-->
<!--        <h1>X J B S</h1>-->
        <div class="brand"></div>
        <br /><br />
        <a-alert type="error" message="搜索失败" banner v-if="invalid" />
        <a-input-search placeholder="点此搜索" style="width: 400px" @search="onSearch" />

    </div>
</template>

<script>

import request from '../requests/search'

export default {
    name: 'onSearch',
    components: {},
    data () {
        return {
            invalid: false,
        }
    },
    methods: {
        onSearch(key) {
            request.search({
                key,
                token: localStorage.getItem("token")})
            .then((res)=>{
                if(res.code != 200) {
                    that.invalid = true;
                }
                else {
                    localStorage.setItem("key",key);
                    localStorage.setItem("maxPage",res.data / 10);
                    this.$router.push("/display/paper");
                }
            })
        }
    }
}
</script>

<style lang='scss' scoped>
.search {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    background-color: #ffffff;
}
.home {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    /*background-color: #ffffff;*/
    background-image: url("../assets/1.jpg");
    opacity: 0.8;
}
.brand {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 300px;
    height: 120px;
    /*background-image: url("../../src/assets/161716308065739.png");*/
    background-image: url("../assets/brand.png")
}
.search-form {
    width: 400px;
    padding: 48px;
    box-sizing: border-box;
    background-color: #ffffff;
}
#components-form .search-form-forgot {
  float: right;
}
#components-form .search-form-button {
  width: 100%;
}
</style>
