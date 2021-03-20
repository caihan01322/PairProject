<template>
    <div id="app">
        <navbar></navbar>

        <router-view></router-view>
    </div>
</template>

<script>
import navbar from "./components/navbar";

export default {
    name: "App",
    components: {
        navbar,
    },
};
</script>

<style>
* {
    margin: 0;
    padding: 0;
    position: relative;
}

html,
body {
    width: 100%;
    height: 100%;
}

#app {
    width: 100%;
    height: 100%;
    background-color: #fcfdf5;
}
</style>
