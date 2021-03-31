<!--  -->
<template>
    <div class='display'>
        <a-layout id="components-layout-demo-custom-trigger">
            <a-layout-sider id="asider" v-model="collapsed" :trigger="null" collapsible>
                <div class="logo" >
                    <h1>查询结果</h1>
                </div>
                <a-menu mode="inline" :default-selected-keys="['paper']" @click="route">
                <a-menu-item key="paper">
                    <a-icon type="global" />
                    <span>相关论文</span>
                </a-menu-item>
                <a-menu-item key="statistics">
                    <a-icon type="unordered-list" />
                    <span>统计结果</span>
                </a-menu-item>
                </a-menu>
            </a-layout-sider>
            <a-layout>
                <a-layout-header class="header" style="background: #fff; padding: 0">
                <a-icon
                    class="trigger"
                    :type="collapsed ? 'menu-unfold' : 'menu-fold'"
                    @click="() => (collapsed = !collapsed)"
                />
                <a-dropdown style="padding-right: 24px" placement="topLeft">
                    <a class="ant-dropdown-link" @click="e => e.preventDefault()">
                        <span>{{username}}</span>
                    </a>
                    <a-menu slot="overlay">
                    <a-menu-item>
                        <a @click="logout"><a-icon type="poweroff" /> 退出</a>
                    </a-menu-item>
                    </a-menu>
                </a-dropdown>
                </a-layout-header>
                <a-layout-content :style="{ minHeight: '800px' }" >
                <router-view></router-view>
                </a-layout-content>
            </a-layout>
        </a-layout>
    </div>
</template>

<script>

export default {
    name: 'Display',
    components: {},
    data () {
        return {
            collapsed: false,
            username: "",
        }
    },
    methods: {
        route({ item, key, keyPath }) {
            this.$router.push("/display/"+key);
        },
        logout() {
            // console.log("logout")
            localStorage.clear();
            this.$router.push("/");
        }
    },
    mounted() {
        // this.$router.push("/center/brief");
        this.username = localStorage.getItem("username");
    },
}
</script>
<style lang='scss' scoped>

.coat {
    width: 100%;
    height: 100%;
}

#asider {
  background-color: #ffffff;
  box-shadow: 1px 0 2px #eeeeee;
}

#components-layout-demo-custom-trigger {
  width: 100%;
  height: 100%;
}

#components-layout-demo-custom-trigger .trigger {
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}

#components-layout-demo-custom-trigger .trigger:hover {
  color: #1890ff;
}

#components-layout-demo-custom-trigger .logo {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 32px;
    margin: 16px;
    h1 {
        margin: 0;
        color: #1890ff;
        font-weight: 700;
        overflow: hidden;
        white-space: nowrap;
    }
}

.header {
    display: flex;
    justify-content: space-between;
}
</style>