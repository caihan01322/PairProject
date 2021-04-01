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
