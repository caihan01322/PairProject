<!--  -->
<template>
    <div class='analyze'>

        <div class="line">
            <div class="wordCloud_block block" >
                <div class="title">
                    <h3>热门领域</h3>
                </div>
                <div class="wordCloud_container">
                    <word-cloud></word-cloud>
                </div>
            </div>

            <div class="rank_block block" >
                <rank></rank>
            </div>
        </div>

        <div class="line">
            <div class="chart_container"></div>
        </div>
        
    </div>
</template>

<script>
import Rank from '../components/Rank.vue'

import WordCloud from '../components/WordCloud.vue'

export default {
    name: 'Analyze',
    components: {},
    data () {
        return {

        }
    },
    components: {
        'word-cloud': WordCloud, 
        Rank,
    },
    methods: {

    }
}
</script>

<style lang='scss' scoped>
//@import url();
.analyze {
    padding: 24px 16px;
    box-sizing: border-box;
}
.line {
    display: flex;
    width: 100%;
    .block {
        background-color: #fff;
    }
    .wordCloud_block {
        margin-right: 12px;
        .title {
            display: flex;
            align-items: center;
            height: 48px;
            width: 100%;
            padding: 0 24px;
            box-sizing: border-box;
            border-bottom: 1px solid #eeeeee;
            h3 {
                margin: 0;
                font-weight: 700;
            }
        }
        .wordCloud_container {
            padding: 12px;
        }
    }
    .rank_block {
        width: auto;
        flex-grow: 1;
    }
}
</style>