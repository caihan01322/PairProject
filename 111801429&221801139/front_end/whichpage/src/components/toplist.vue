<template>
    <div id="top_list">
        <div id="title">
            <i class="iconfont icon-hot"></i>
            <span id="title_name">Top10 :</span>
        </div>

        <div
            id="list_items"
            v-for="(item, index) in items"
            :key="index"
            :title="item.title"
            @click="go_search(item.title)"
        >
            <span>{{ index + 1 }}. {{ item.title }}</span>
        </div>
    </div>
</template>

<script>
export default {
    name: "Toplist",

    data() {
        return {
            items: [],
        };
    },

    created() {
        this.$axios({
            method: "GET",
            url: `/tag`,
        }).then((re) => {
            console.log(re);
            if (re.data.errno == 0) {
                this.items = re.data.data;
            }
        });
    },

    methods: {
        go_search(title) {
            localStorage.setItem("tag", title);
            if (this.$route.path != "/home/listpage") {
                this.$router.push({ name: "listpage" });
            } else {
                this.$router.go(0);
            }
        },
    },
};
</script>

<style scoped>
#top_list {
    border-left: 2px #033 solid;
    box-sizing: border-box;
    height: 90%;
    width: 23%;
}

#title {
    display: flex;
    align-items: center;
    margin-top: 4%;
    margin-left: 4%;
    margin-bottom: 10%;
}

.iconfont {
    font-size: 30px;
}

#title_name {
    font-size: 20px;
    font-weight: bold;
}

#list_items {
    border-bottom: 1px #9ecca4 solid;
    box-sizing: border-box;
    font-size: 18px;
    margin-left: 14%;
    margin-bottom: 5%;
    padding: 1% 0;
    background-color: #fff;
    width: 75%;
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
}

#list_items:hover {
    cursor: pointer;
}
</style>