<template>
    <div>
<el-container>
  <el-header style="background-color:black">
    <el-row>
      <el-col :span="5"><el-avatar shape="square" :size="40" :src='iconurl'></el-avatar><div class="title">vc论文</div></el-col>
      <el-col :span="14">
       <el-menu id ="menu"
  :default-active="activeIndex"
  class="el-menu-demo"
  mode="horizontal"
  font-size="50px"
  @select="handleSelect"
  background-color="black"
  text-color="#fff"
  active-text-color="#ffd04b">
  <el-menu-item class="menu" index="1">论文搜索</el-menu-item>
  <el-menu-item class="menu" index="2">
    论文列表
  </el-menu-item>
  <el-menu-item class="menu" index="3">论文热词</el-menu-item>
  <el-menu-item class="menu" index="4" >论文信息</el-menu-item>
</el-menu>
        </el-col>
       <el-col :span="5">
         
         
         <div class="login">
           <el-link :underline="false">登录/注册</el-link>
         </div>
         
         </el-col>
        
 </el-row>
  </el-header>
  <el-main style="background-color:transparent;height:678px">
      <el-container>
          <el-header style="background-color:transparent;height:40px;line-height:40px;font-size:20px;">
                论文题目
          </el-header>
          <el-header style="background-color:transparent;height:36px;line-height:36px;margin-top:10px">
              <el-row>
                <el-col :span="8">
                 <div  style="text-align:right;margin-right:10px">
                    <el-tag type="success" style="margin-top:2px;height:40px;line-height:40px" >论文作者</el-tag>
                    </div></el-col>
                <el-col :span="11"><div  class="color" style="border-radius:4px;height:32px;margin-top:3px">
                <el-input v-model="input" placeholder="请输入内容"></el-input>
                </div></el-col>
                <el-col :span="5"><div ></div></el-col>
                </el-row>

                <el-row style="margin-top:20px">
                <el-col :span="8">
                 <div  style="text-align:right;margin-right:10px">
                    <el-tag type="success" style="margin-top:2px;height:40px;line-height:40px" >论文网址</el-tag>
                    </div></el-col>
                <el-col :span="11"><div  class="color" style="border-radius:4px;height:32px;margin-top:3px">
                    <el-input v-model="input" placeholder="请输入内容"></el-input>
                    </div></el-col>
                <el-col :span="5"><div ></div></el-col>
                </el-row>
                
                <el-row style="margin-top:20px">
                <el-col :span="8">
                 <div  style="text-align:right;margin-right:10px">
                    <el-tag type="success"  style="margin-top:2px;height:40px;line-height:40px" >论文顶会</el-tag>
                    </div></el-col>
                <el-col :span="11"><div  class="color" style="border-radius:4px;height:32px;margin-top:3px">
                <el-input v-model="input" placeholder="请输入内容"></el-input>    
                </div></el-col>
                <el-col :span="5"><div ></div></el-col>
                </el-row>

                 <el-row style="margin-top:20px">
                <el-col :span="8">
                 <div  style="text-align:right;margin-right:10px">
                    <el-tag type="success" style="margin-top:2px;height:40px;line-height:40px" >论文摘要</el-tag>
                    </div></el-col>
                <el-col :span="11"><div  class="color" style="border-radius:4px;height:32px;margin-top:3px;height:250px;">
                    <el-input
                        type="textarea"
                        :rows="12"
                        placeholder="请输入内容"
                        v-model="textarea">
                    </el-input>    
                </div></el-col>
                <el-col :span="5"><div ></div></el-col>
                </el-row>

                <el-row style="margin-top:234px">
                <el-col :span="8">
                 <div  style="text-align:right;margin-right:10px">
                    <el-tag type="success" style="margin-top:6px;height:32px;line-height:32px">论文关键词</el-tag>
                    </div></el-col>
                <el-col :span="11"><div   style="border-radius:4px;height:32px;margin-top:3px;text-align:left">
                <el-tag

                        :key="tag"
                        v-for="tag in dynamicTags"
                        closable
                        :disable-transitions="false"
                        @close="handleClose(tag)">
                        {{tag}}
                        </el-tag>
                        <el-input
                        class="input-new-tag"
                        v-if="inputVisible"
                        v-model="inputValue"
                        ref="saveTagInput"
                        size="small"
                        @keyup.enter.native="handleInputConfirm"
                        @blur="handleInputConfirm"
                        >
                    </el-input>
                    <el-button v-else class="button-new-tag" size="small" @click="showInput">+ New Tag</el-button>    
                </div></el-col>
                <el-col :span="5"><div ></div></el-col>
                </el-row>
          </el-header>
          <el-main style="background-color:transparent;margin-top:460px;height:60px;line-height:40px;padding-bottom:0px">
            <el-row style="margin-top:0px;background-color:transparent;height:auto;margin-bottom:0px;line-height:20px">
            <el-col :span="8"><div  class="grid-content bg-purple-dark"  style="background-color:transparent"></div></el-col>
            <el-col :span="2" ><div >
                 <el-button type="primary" round>确定</el-button>
            </div>
            
            </el-col>
            <el-col :span="7"><div  class="grid-content bg-purple-dark"  style="background-color:transparent"></div></el-col>
            <el-col :span="2"><div >
                <el-button type="primary" round>退出</el-button>
                </div></el-col>
           
                        <el-col :span="5"><div   class="grid-content bg-purple-dark" style="background-color:transparent"></div></el-col>
            </el-row>
          </el-main>
      </el-container>

      </el-main>
    </el-container>
    </div>
</template>

<script>
import icon from "@/assets/vcicon.jpg";

  export default {
    data() {
      return {
        iconurl:icon,
        activeIndex:'4',
        searchinfo:'',
         dynamicTags: ['标签一', '标签二', '标签三','标签四'],
        inputVisible: false,
        inputValue:'',
        textarea:''
      }
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      },
          handleClose(tag) {
        this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
      },

      showInput() {
        this.inputVisible = true;
        this.$nextTick(_ => {
          this.$refs.saveTagInput.$refs.input.focus();
        });
      },

      handleInputConfirm() {
        let inputValue = this.inputValue;
        if (inputValue) {
          this.dynamicTags.push(inputValue);
        }
        this.inputVisible = false;
        this.inputValue = '';
      }
    }
  }

</script>

<style>
.color{
    background-color:#ecf5ff !important;
    border-color: #d9ecff !important;
    color:#409EFF;
}
    .menu:hover{
      background-color:mediumseagreen !important;

  }
.el-row {
     height:100%;
        padding-bottom: 0;
  }
  .el-col el-col-5{
      height:100%;
      margin-bottom:0px;
  }
  .el-col {
    border-radius: 0px;
    height:100%;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  .el-header, .el-footer {
    background-color:transparent;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
    .title{
      color: white;
      margin-left: 30px;
      text-align: left;
      font-size: 17px;
  }
  .menu{
    background-color: black;
    margin-left: 80px !important;
    text-align: center;
  }
    .el-avatar{
      float:left;
      margin-left:20px;
      margin-top:10px;
      margin-right: 15px;
  }
    .el-link{
    color:white !important;
    float: right;
    margin-right: 15px;
    font-size:17px;
  }
    .el-tag + .el-tag {
    margin-left: 10px;
  }
  .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }
</style>