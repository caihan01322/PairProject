<template>
  <div id="wrap">
    <div id="nav">
      <img class="p" src="../assets/css/1.jpg" />
      <input class="nav-button" type="button" value="登录" @click="link1" />
      <input class="nav-button" type="button" value="注册" @click="link2" />
      <div id="main">
        <div id="main1">
          <img class="p1" src="../assets/css/5.jpg" />
          <h1>服务介绍</h1>
          <p>为您分析国际计算机视觉与模式识别会议（CVPR）,<br/>国际计算机视觉大会（ICCV）
            和欧洲计算机视觉国际会议（ECCV）热词走势。</p>
          <el-button class="go1" @click="link1">马上体验</el-button>
        </div>
        <div id="main2">
          <img class="p2" src="../assets/css/6.jpg" />
          <h1>服务介绍</h1>
          <p>为您爬取，分析论文热门词汇及热门领域，让您快速了解计算机视觉领域的热门研究方向。</p>
          <el-button class="go2" @click="link1">马上体验</el-button>
        </div>
        <div id="main3">
          <el-collapse v-model="activeName" accordion>
            <el-collapse-item title="国际计算机视觉大会（ICCV）" name="1">
              <div>  “ICCV”是“International Conference on Computer Vision”的简称。该会议由美国电气和电子工程师学会主办，
                主要在欧洲、亚洲、美洲的一些科研实力较强的国家举行。作为世界顶级的学术会议，首届国际计算机视觉大会于1987年在伦敦揭幕，其后两年举办一届。</div>
            </el-collapse-item>
            <el-collapse-item title="国际计算机视觉与模式识别会议（CVPR）" name="2">
              <div>国际计算机视觉与模式识别会议（CVPR）是IEEE一年一度的学术性会议，会议的主要内容是计算机视觉与模式识别技术。CVPR是世界顶级的计算机视觉会议（三大顶会之一，另外两个是ICCV和ECCV），近年来每年有约1500名参加者，收录的论文数量一般300篇左右。
                本会议每年都会有固定的研讨主题，而每一年都会有公司赞助该会议并获得在会场展示的机会。</div>
            </el-collapse-item>
            <el-collapse-item title="欧洲计算机视觉国际会议（ECCV）" name="3">
              <div>ECCV的全称是European Conference on Computer Vision(欧洲计算机视觉国际会议) 。每次会议在全球范围录用论文300篇左右，主要的录用论文都来自美国、欧洲等顶尖实验室及研究所，
                中国大陆的论文数量一般在10-20篇之间。ECCV2010的论文录取率为27%。</div>
            </el-collapse-item>
          </el-collapse>
          <div class="block">
            <span class="demonstration"></span>
            <el-carousel>
              <el-carousel-item v-for="item in imagesBox" :key="item.id">
                <img :src="item.url" class="image" />
              </el-carousel-item>
            </el-carousel>
          </div>
        </div>
      </div>
      <div id="footer">
        <input class="footer-button" id="indexButton" type="button" value="首页官网" onclick />
        <input class="footer-button" type="button" value="关于我们" onclick />
        <input class="footer-button" type="button" value="联系我们" onclick />
        <span id="copyright">Copyright © 2020. All rights reserved.</span>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      activeName: "1",
      imagesBox: [
        {
          id: 0,
          /* idView: require("~@/assets/p2") */
          url: require("../assets/css/2.jpg")
        },
        {
          id: 1,
          /* idView: require("~@/assets/p2") */
          url: require("../assets/css/3.jpg")
        },
        {
          id: 2,
          /* idView: require("~@/assets/p2") */
          url: require("../assets/css/4.jpg")
        }
      ],
      fiveNews: []
    };
  },
  methods: {
    
    link1() {
      this.$router.replace("/login");
    },
    link2() {
      this.$router.replace("/signin");
    }
  }
};
</script>
<style scoped>
#main {
  width: 100%;
  height: 1300px;
  background-color: #101010;
}

.p {
  padding-left: 80px;
  width: 70px;
  height: 60px;
}

#main1 h1 {
  position: absolute;
  top: 200px;
  left: 100px;
  color: #eae9e9;
}

#copyright {
  padding-top: 24px;
  float: right;
  padding-right: 280px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  font-size: 14px;
}

#main1 p {
  position: absolute;
  top: 250px;
  left: 100px;
  color: #eae9e9;
}

.go1 {
  position: absolute;
  top: 330px;
  left: 120px;
  background-color:#2d8ed0; 
  border: #2d8ed0;
  color: #eae9e9;
}

#main2 h1 {
  position: absolute;
  top: 600px;
  left: 900px;
  color: #eae9e9;
}
#main2 p {
  position: absolute;
  top: 650px;
  left: 900px;
  color: #eae9e9;
}

.go2 {
  position: absolute;
  top: 715px;
  left: 910px;
  background-color: #2d8ed0;
  border: #2d8ed0;
  color: #eae9e9;
}

#nav,
#footer {
  height: 61px;
  background-color: #353d48;
}

input.nav-button {
  background-color: #353d48;
  border-color: #353d48;
  border-bottom-style: none;
  border-right-style: none;

  color: #eae9e9;
  font-family: 思源黑体;
  font-size: 16px;
  vertical-align: top;
  line-height: 24px;
  padding-top: 24px;
  padding-right: 40px;
  float: right;
}

input.footer-button {
  background-color: #353d48;
  border-color: #353d48;
  border-bottom-style: none;
  border-right-style: none;

  color: #eae9e9;
  font-family: 思源黑体;
  font-size: 16px;
  vertical-align: top;
  line-height: 24px;
  padding-top: 24px;
  padding-left: 40px;
}

input#findButton {
  padding-left: 73px;
}
input#loginOut {
  float: right;
  padding-right: 126px;
}
#footer {
  margin-top: -61px;

  border-top: 1px solid;
  border-color: #eae9e9;
}
input.footer-button {
  font-size: 14px;
  padding-left: 76px;
}
input.footer-button#indexButton {
  padding-left: 225px;
}

.p1,
.p2 {
  width: 40%;
  height: 80%;
}

.p1 {
  padding-left: 800px;
  padding-top: 50px;
}

.p2 {
  padding-top: 50px;
  padding-left: 100px;
}

#main1,
#main2,
#main3 {
  width: 100%;
  height: 400px;
}

.el-collapse {
  width: 600px;
  height: 300px;
  position: absolute;
  padding-top: 50px;
  padding-left: 100px;
  background-color: #101010;
  color:#eae9e9;
  border: none;
  /* top:
  left: 80px; */
}

.el-collapse-item{
  background-color: #101010;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.block {
  width: 650px;
  height: 200px;
  padding-left: 850px;
  padding-top: 50px;
}

.block img {
  width: 100%;
  height: 100%;
}
</style>