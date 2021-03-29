<?php
/* @var $this yii\web\View */
use yii\helpers\Html;
use backend\components\KeywordsCloudWidget;
use common\models\Paper;
use yii\grid\GridView;
use yii\widgets\ListView;
use common\models\PaperSearch;
use yii\bootstrap\Carousel;

$this->title = '论文管理系统';
?>
<div class="site-index">

<div class="jumbotron">
    <h1>论文管理系统</h1>
 
</div>
<div class="img-wall" style="width:660">

<div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="4000" >

    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <div class="carousel-inner">
        <div class="item active">
            <img src="http://5b0988e595225.cdn.sohucs.com/images/20190731/98a8e684a10240d4ba4be9c1cca522f8.jpeg" alt="First slide">
            <div class="carousel-caption">ICCV</div>
        </div>
        <div class="item">
            <img src="http://p4.itc.cn/q_70/images03/20200703/0cfcf8427e884ca99ed1f45f984a9d44.png" alt="Second slide">
            <div class="carousel-caption">ECCV</div>
        </div>
        <div class="item">
            <img src="https://img-blog.csdnimg.cn/img_convert/80b20f224cf3cd6300f106ea6e57c268.png" alt="Third slide">
            <div class="carousel-caption">CVPR</div>
        </div>
    </div>

    <a class="carousel-control left" href="#myCarousel"
       data-slide="prev">&lsaquo;</a>
    <a class="carousel-control right" href="#myCarousel"
       data-slide="next">&rsaquo;</a>
</div>
</div>
<br/>
<br/>
<div class="container">
    <div class="row">
        <div class="col-md-8">
        <?= ListView::widget([
				'id'=>'paperList',
				'dataProvider'=>$dataProvider,
				'itemView'=>'_listitem',//子视图,显示一篇文章的标题等内容.
                'layout'=>'{items} {pager}',
				'pager'=>[
						'maxButtonCount'=>10,
						'nextPageLabel'=>Yii::t('app','下一页'),
						'prevPageLabel'=>Yii::t('app','上一页'),
                ],
		])?>
        </div>

        <div class="col-md-4">
            <div class="introduce">
                <ul class="list-group">
                   <li class="list-group-item">
                   <span class="glyphion-search" aria-hidden="true"></span>背景介绍
                   </li>
                   <li class="list-group-item">CVPR国际计算机视觉与模式识别会议（CVPR）是IEEE一年一度的学术性会议，会议的主要内容是计算机视觉与模式识别技术。CVPR是世界顶级的计算机视觉会议（三大顶会之一，另外两个是ICCV和ECCV），近年来每年有约1500名参加者，收录的论文数量一般300篇左右。本会议每年都会有固定的研讨主题，而每一年都会有公司赞助该会议并获得在会场展示的机会。ICCVICCV 的全称是 IEEE International Conference on Computer Vision，即国际计算机视觉大会，由IEEE主办，与计算机视觉模式识别会议（CVPR）和欧洲计算机视觉会议（ECCV）并称计算机视觉方向的三大顶级会议，被澳大利亚ICT学术会议排名和中国计算机学会等机构评为最高级别学术会议，在业内具有极高的评价。不同于在美国每年召开一次的CVPR和只在欧洲召开的ECCV，ICCV在世界范围内每两年召开一次。ICCV论文录用率非常低，是三大会议中公认级别最高的。ECCVECCV的全称是European Conference on Computer Vision(欧洲计算机视觉国际会议) ，两年一次，是计算机视觉三大会议（另外两个是ICCV和CVPR）之一。每次会议在全球范围录用论文300篇左右，主要的录用论文都来自美国、欧洲等顶 尖实验室及研究所，中国大陆的论文数量一般在10-20篇之间。ECCV2010的论文录取率为27%。</li>
                </ul>
            </div>
        

            <div class="keyword">
                <ul class="list-group">
                   <li class="list-group-item">
                   <span class="glyphion-search" aria-hidden="true"></span>关键词top10
                   </li>
                   <li class="list-group-item">
                   <?= KeywordsCloudWidget::widget(['keywords'=>$keywords]);?>
                   </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</div>




