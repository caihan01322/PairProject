<template>
    <div id="home">
        <router-view></router-view>

        <toplist></toplist>
    </div>
</template>

<script>
import toplist from "../components/toplist";

export default {
    name: "Home",

    components: {
        toplist,
    },

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
    /* border: 1px red solid; */
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 90%;
}
</style>