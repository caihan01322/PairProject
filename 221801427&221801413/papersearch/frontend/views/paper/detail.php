<?php

use yii\helpers\Html;
use yii\grid\GridView;
use yii\widgets\ListView;
use common\models\Paper;
use frontend\components\TagsCloudWidget;

use yii\helpers\Url;

/* @var $this yii\web\View */
/* @var $searchModel common\models\PaperSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

?>

<div class="container">

	<div class="row">
	
		<div class="col-md-8">

			<ol class="breadcrumb">
				<li><a href="<?= Yii::$app->homeUrl;?>">首页</a></li>
				<li><a href="<?= Url::to(['paper/index']);?>">论文列表</a></li>
				<li class="active"><?= $model->title?></li>
			</ol>
			
			<div class="paper">

				<div class="title">
					<h2><a href="<?= $model->url;?>"><?= Html::encode($model->title);?></a></h2>
				</div>

			<br>

				<div class="origin">
					<span class="glyphicon glyphicon-time" aria-hidden="true"></span><em><?= $model->release_time."&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";?></em>
					<span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span><em><?= $model->conference;?></em>
				</div>

			<br>

				<div class="content">
					<?= $model->abstract;?>
				</div>
			
			<br>
			
				<div class="nav">
					<span class="glyphicon glyphicon-tag" aria-hidden="true"></span>
					<?= implode(', ',$model->tagLinks);?>
				</div>
			
			<br>
			
				<div class="content">
					<span class="glyphicon glyphicon-link" aria-hidden="true"></span><em><?= $model->orgLink;?></em>
				</div>

			</div>
		</div>
		
		<div class="col-md-4">
			
			<div class="searchbox">
				<ul class="list-group">
				  <li class="list-group-item">
				  <span class="glyphicon glyphicon-search" aria-hidden="true"></span> 查找论文
				  </li>
				  <li class="list-group-item">				  
					  <form class="form-inline" action="index.php?r=paper/index" id="w0" method="get">
						  <div class="form-group">
						    <input type="text" class="form-control" name="PaperSearch[title]" id="w0input" placeholder="按标题">
						  </div>
						  <button type="submit" class="btn btn-default" style="width:135px;margin-left:15px">搜索</button>
					</form>
				  
				  </li>
				</ul>			
			</div>

			<div class="tagcloudbox">
				<ul class="list-group">
				  <li class="list-group-item">
				  		<span class="glyphicon glyphicon-tags" aria-hidden="true"></span> Top10关键词
				  </li>
				  <li class="list-group-item">
				  <?= TagsCloudWidget::widget(['keywords'=>$keywords])?>
				   </li>
				</ul>			
			</div>
		</div>		
	</div>
</div>
