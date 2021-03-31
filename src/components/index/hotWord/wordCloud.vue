<template>
  <div id="indexWordCloud">
    <div id="wordCloudTitle">
      <div>关键词图谱（Top10）</div>
    </div>
    <div id="wordCloudBody">
      <Carousel  v-model="value" loop dots="outside" class="myCarouse">
        <CarouselItem>
          <div class="demo-carousel">
            <wordcloud
              :data="cvpr"
              nameKey="name"
              valueKey="value"
              :color="myColors"
              :showTooltip="false"
              :rotate="rotate"
              :wordClick="wordClickHandler">
            </wordcloud>
          </div>
        </CarouselItem>
        <CarouselItem>
          <div class="demo-carousel">
            <wordcloud
              :data="iccv"
              nameKey="name"
              valueKey="value"
              :color="myColors"
              :showTooltip="false"
              :rotate="rotate"
              :wordClick="wordClickHandler">
            </wordcloud>
          </div>
        </CarouselItem>
        <CarouselItem>
          <div class="demo-carousel">
            <wordcloud
              :data="eccv"
              nameKey="name"
              valueKey="value"
              :color="myColors"
              :showTooltip="true"
              :rotate="rotate"
              :wordClick="wordClickHandler">
            </wordcloud>
          </div>
        </CarouselItem>
        <CarouselItem>
          <div class="demo-carousel">
          </div>
        </CarouselItem>
      </Carousel>
    </div>
  </div>
</template>

<script>

import wordcloud from 'vue-wordcloud'
import { mapState, mapMutations } from 'vuex'
export default {
  name: 'wordCloud',
  data () {
    return {
      value: 0,
      myColors: ['#4699f1', '#cb2981', '#9eafb2', '#abe3b7', '#a73774'],
      cvpr: [
        {
          name: 'Cat',
          value: 26
        },
        {
          name: 'fish',
          value: 19
        },
        {
          name: 'things',
          value: 18
        },
        {
          name: 'look',
          value: 16
        },
        {
          name: 'two',
          value: 15
        },
        {
          name: 'fun',
          value: 9
        },
        {
          name: 'know',
          value: 9
        },
        {
          name: 'good',
          value: 9
        },
        {
          name: 'play',
          value: 6
        }
      ],
      iccv: [
        {
          name: 'Cat',
          value: 26
        },
        {
          name: 'fish',
          value: 19
        },
        {
          name: 'things',
          value: 18
        },
        {
          name: 'look',
          value: 16
        },
        {
          name: 'two',
          value: 15
        },
        {
          name: 'fun',
          value: 9
        },
        {
          name: 'know',
          value: 9
        },
        {
          name: 'good',
          value: 9
        },
        {
          name: 'play',
          value: 6
        }
      ],
      eccv: [
        {
          name: 'Cat',
          value: 26
        },
        {
          name: 'fish',
          value: 19
        },
        {
          name: 'things',
          value: 18
        },
        {
          name: 'look',
          value: 16
        },
        {
          name: 'two',
          value: 15
        },
        {
          name: 'fun',
          value: 9
        },
        {
          name: 'know',
          value: 9
        },
        {
          name: 'good',
          value: 9
        },
        {
          name: 'play',
          value: 6
        }
      ],
      rotate: {
        from: -10,
        to: 10,
        numOfOrientation: 5
      }
    }
  },
  computed: mapState(['searchKey', 'isSearchKey']),
  ...mapMutations(['setSerchKey', 'openSearchKey', 'closeSearchKey']),
  methods: {
    wordClickHandler (name) {
      console.log('wordClickHandler')
      this.$store.commit('openSearchKey')
      this.$store.commit('setSerchKey', name)
      this.$router.push('/index/search')
    },
    getWord () {
      this.$axios.post('http://localhost:8081/KeywordMapController/getWordMap')
        .then(res => {
          this.cvpr = res.data.cvpr
          this.iccv = res.data.iccv
          this.eccv = res.data.eccv
        })
        .catch(err => {
          console.log(err)
        })
        .finally({
        })
    }
  },
  components: {
    wordcloud
  }
}
</script>

<style scoped lang="less">
#indexWordCloud {
  width: 100%;
  height: 600px;
  top: 0;
  background-color: #7F7F7F;
}
#wordCloudTitle {
  width: 100%;
  height: 50px;
  background-color: #555555;
  color: white;
  margin: 0;
  padding: 0;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  >div{
    color: white;
    font-size: 20px;
    font-weight: 700;
    margin: 0 20px;
  }
}
  .myCarouse {
    width: 100%;
    height: 550px;
    .demo-carousel {
      width: 100%;
      height: 550px;
      display: flex;
      flex-direction: row;
      justify-content: center;
      align-items: center;
    }
  }

</style>
