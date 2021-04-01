<?php

use yii\helpers\Html;

/* @var $this yii\web\View */

$this->title = 'Paper Search';
?>
<div class="site-index">

    <div class="jumbotron">

        <div class="img-wall">

            <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="4000" >

                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                    <li data-target="#myCarousel" data-slide-to="3"></li>
                    <li data-target="#myCarousel" data-slide-to="4"></li>
                </ol>

                <div class="carousel-inner">
                    <div class="item active">
                        <img src="images/bg0.jpg" alt="First slide">
                    </div>
                    <div class="item">
                        <img src="images/bg1.jpg" alt="Second slide">
                    </div>
                    <div class="item">
                        <img src="images/bg2.jpg" alt="Third slide">
                    </div>
                    <div class="item">
                        <img src="images/bg3.jpg" alt="Fourth slide">
                    </div>
                    <div class="item">
                        <img src="images/bg4.jpg" alt="Fifth slide">
                    </div>
                </div>

            </div>

        </div>

        <h1 class="title">欢迎来到Paper Search!</h1>

        <p class="title">你可以在这里自由搜索、管理国际计算机视觉三大顶会历年论文。</p>

        <p><?= Html::a('点我开始探索', ['paper/index'], ['class' => 'btn btn-success']) ?></p>

    </div>

    <br>
    <br>

    <div class="body-content">

        <div class="row">

            <div class="col-lg-4">
                <h2 class="title">CVPR</h2>

                <p>国际计算机视觉与模式识别会议（CVPR）是IEEE一年一度的学术性会议，会议的主要内容是计算机视觉与模式识别技术。
                CVPR是世界顶级的计算机视觉会议（三大顶会之一，另外两个是ICCV和ECCV），近年来每年有约1500名参加者，
                收录的论文数量一般300篇左右。本会议每年都会有固定的研讨主题，而每一年都会有公司赞助该会议并获得在会场展示的机会。
                CVPR有着较为严苛的录用标准，会议整体的录取率通常不超过30%，而口头报告的论文比例更是不高于5%。而会议的组织方是一个循环的志愿群体
                ，通常在某次会议召开的三年之前通过遴选产生。CVPR的审稿一般是双盲的，也就是说会议的审稿与投稿方均不知道对方的
                信息。通常某一篇论文需要由三位审稿者进行审读。最后再由会议的领域主席(area chair)决定论文是否可被接收。
                </p>

                <p><a class="btn btn-default" href="http://cvpr2020.thecvf.com/">CVPR2020官网 &raquo;</a></p>
            </div>

            <div class="col-lg-4">
                <h2 class="title">ECCV</h2>

                <p>ECCV的全称是European Conference on Computer Vision(欧洲计算机视觉国际会议) ，两年一次，是计算机视觉三大会议（另外两个是ICCV和CVPR）之一。
                每次会议在全球范围录用论文300篇左右，主要的录用论文都来自美国、欧洲等顶尖实验室及研究所，中国大陆的论文数量一般在10-20篇之间。ECCV2010的论文录取率为27%。
                ECCV是一个欧洲会议，欧洲人一般比较看中理论，但是从最近一次会议来看，似乎大家也开始注重应用了，oral里面的demo非常之多，演示效果很好，让人赏心悦目、叹为观止。
                不过欧洲的会有一个不好，就是他们的人通常英语口音很重，有些人甚至不太会说英文，所以开会和交流的时候，稍微有些费劲。
                </p><br><br>

                <p><a class="btn btn-default" href="https://eccv2020.eu/">ECCV2020官网 &raquo;</a></p>
            </div>

            <div class="col-lg-4">
                <h2 class="title">ICCV</h2>

                <p>ICCV 的全称是 IEEE International Conference on Computer Vision，即国际计算机视觉大会，由IEEE主办，与计算机视觉模式识别会议（CVPR）
                和欧洲计算机视觉会议（ECCV）并称计算机视觉方向的三大顶级会议，被澳大利亚ICT学术会议排名和中国计算机学会等机构评为最高级别学术会议，在业内
                具有极高的评价。不同于在美国每年召开一次的CVPR和只在欧洲召开的ECCV，ICCV在世界范围内每两年召开一次。ICCV论文录用率非常低，是三大会议中公
                认级别最高的。ICCV会议时间通常在四到五天，相关领域的专家将会展示最新的研究成果。2019年ICCV将在韩国首尔举办。
                </p><br><br><br>

                <p><a class="btn btn-default" href="https://iccv2019.thecvf.com/">ICCV2019官网 &raquo;</a></p>
            </div>

        </div>

    </div>

</div>
