<template>
    <div style="background:white;">
        <div class="search-header">
            <div> <img alt="PaperRetriveal logo" src="../assets/PaperRetriveal-mini.png" class="logo" @click="backHome"></div>
            <div class="search-box" ><SearchView @inputValue="getInputValue"/></div>
            <div style="width:50%;margin-top: 0px;"><Header style="margin-top: 4px;"/></div>

        </div>
    </div>
</template>

<script>
import SearchView from '@/components/SearchView.vue'
import Header from '@/components/Header.vue'
export default {
    components:{
        SearchView,
        Header
    },
    setup(props,{emit}) {
        function getInputValue(val){
            emit('inputValue',val);
        }
        function backHome(){
            window.location.href='/';
        }
        return{
            getInputValue,
            backHome,
        }
    },
}
</script>
<style scoped>
.logo {
        width:90px;
        height:46px;
        margin:0px;
}
.search-header{
    display:flex;
    
}
.search-box{
    width: fit-content;
    display: flex;
    margin-left: 20px;
    
}
</style>