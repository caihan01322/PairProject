<template>
    <div id="home">
        <router-view></router-view>
    </div>
</template>

<script>
export default {
    name: "Home",

    data() {
        return {
            //参数数据
        };
    },

    methods: {
        //函数
    },
};
</script>

<style scoped>
#home {
    border: 1px red solid;
}
</style>