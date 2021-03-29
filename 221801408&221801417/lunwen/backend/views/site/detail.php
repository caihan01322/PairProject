<?php

use yii\helpers\Html;
use yii\grid\GridView;
use yii\widgets\ListView;
use common\models\Paper;
use backend\components\KeywordsCloudWidget;

use yii\helpers\Url;


/* @var $this yii\web\View */
/* @var $searchModel common\models\PostSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

?>

<div class="container">

	<div class="row">
	
		<div class="col-md-7">
		
			<ol class="breadcrumb">
			<li><a href="<?= Yii::$app->homeUrl;?>">首页</a></li>
			<li><a href="<?= Url::to(['site/index']);?>">文章列表</a></li>
			<li class="active"><?= $model->title?></li>
			</ol>
			
			<div class="paper">
				<div class="title">
					<h2><a href="<?= $model->url;?>"><?= Html::encode($model->title);?></a></h2>						
				</div>
		
			</div>
			<br>
			
			<div class="content">
			<?= $model->abstract;?>
			</div>
			
			</br>
			<br>
            <div class="nav">
            关键词：
		    <span class="glyphicon glyphicon-keyword" aria-hidden="true"></span>
		    <?= implode(', ',$model->keywordLinks);?>
		    <br>
	    </div>
		</div>

		
		<div class="col-md-5">
            <div class="searchbox">
                <ul class="list-group">
                   <li class="list-group-item">
                   <span class="glyphion-search" aria-hidden="true"></span>背景介绍
                   </li>
                   <li class="list-group-item">CVPR国际计算机视觉与模式识别会议（CVPR）是IEEE一年一度的学术性会议，会议的主要内容是计算机视觉与模式识别技术。CVPR是世界顶级的计算机视觉会议（三大顶会之一，另外两个是ICCV和ECCV），近年来每年有约1500名参加者，收录的论文数量一般300篇左右。本会议每年都会有固定的研讨主题，而每一年都会有公司赞助该会议并获得在会场展示的机会。ICCVICCV 的全称是 IEEE International Conference on Computer Vision，即国际计算机视觉大会，由IEEE主办，与计算机视觉模式识别会议（CVPR）和欧洲计算机视觉会议（ECCV）并称计算机视觉方向的三大顶级会议，被澳大利亚ICT学术会议排名和中国计算机学会等机构评为最高级别学术会议，在业内具有极高的评价。不同于在美国每年召开一次的CVPR和只在欧洲召开的ECCV，ICCV在世界范围内每两年召开一次。ICCV论文录用率非常低，是三大会议中公认级别最高的。ECCVECCV的全称是European Conference on Computer Vision(欧洲计算机视觉国际会议) ，两年一次，是计算机视觉三大会议（另外两个是ICCV和CVPR）之一。每次会议在全球范围录用论文300篇左右，主要的录用论文都来自美国、欧洲等顶 尖实验室及研究所，中国大陆的论文数量一般在10-20篇之间。ECCV2010的论文录取率为27%。</li>
                </ul>
            </div>
			
			<div class="searchbox">
				<ul class="list-group">
				  <li class="list-group-item">
				  <span class="glyphicon glyphicon-tags" aria-hidden="true"></span> 关键词
				  </li>
				  <li class="list-group-item">
				  <?= KeywordsCloudWidget::widget(['keywords'=>$keywords])?>
				   </li>
				</ul>			
			</div>
			
		
		</div>
		
		
	</div>

</div>