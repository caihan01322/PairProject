<template>
    <div id="navbar">
        <div id="logo">
            <img id="nav_logo" src="../assets/logo.png" alt="" />
        </div>
        <div>
            <div id="nav_button">
                <div class="nav_button_item">
                    <i class="iconfont icon-home"></i>
                    <span>首页</span>
                </div>
                <div class="nav_button_item">
                    <i class="iconfont icon-insert"></i>
                    <span>论文导入</span>
                </div>
                <div class="nav_button_item">
                    <i class="iconfont icon-data"></i>
                    <span>本站聚焦</span>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "Navbar",

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
#navbar {
    display: flex;
    justify-content: space-between;
    width: 100%;
    height: 10%;
    background-color: #cee6b4;
}

#logo {
    /* border: 1px red solid; */
    height: 100%;
    width: 80px;
    display: flex;
    align-items: center;
}

#nav_logo {
    /* border: 1px red solid; */
    width: 80px;
    margin-left: 200px;
}

#nav_button {
    display: flex;
    height: 100%;
    margin-right: 100px;
    align-items: center;
}

.nav_button_item {
    display: flex;
    /* border: 1px red solid; */
    height: 100%;
    color: #003333;
    font-size: 24px;
    font-weight: bold;
    margin: 0 20px;
    padding: 0 30px;
    justify-content: center;
    align-items: center;
}

.nav_button_item:hover {
    background-color: #fcfdf5;
    cursor: pointer;
}

.iconfont {
    /* border: 1px red solid; */
    font-size: 24px;
    margin-right: 5px;
}
</style>