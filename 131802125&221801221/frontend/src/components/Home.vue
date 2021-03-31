<template>
    <div class='home'>
        <br /><br />
        <br /><br />
        <br /><br />
        <br /><br />
        <br /><br />
        <br /><br />
        <br /><br />
        <h1>X J B S</h1>
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
                    var token = localStorage.getItem("token");
                    var username = localStorage.getItem("username");
                    localStorage.clear();
                    localStorage.setItem("token",token);
                    localStorage.setItem("username",username);
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