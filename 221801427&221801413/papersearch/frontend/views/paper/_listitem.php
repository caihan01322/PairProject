<?php
use yii\helpers\Html;
?>

<div class="post">
	<div class="title" style="margin-top: 36px">
		<h2><a href="<?= $model->url;?>"><?= Html::encode($model->title);?></a></h2>

		<div class="origin">
		<span class="glyphicon glyphicon-time" aria-hidden="true"></span><em><?= $model->release_time."&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";?></em>
		<span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span><em><?= $model->conference;?></em>
		</div>
	</div>
	
	<br>
	<div class="content">
	<?= $model->beginning;?>	
	</div>
	
	<br>
	<div class="nav">
		<span class="glyphicon glyphicon-tag" aria-hidden="true"></span>
		<?= implode(', ',$model->tagLinks);?>
	</div>
	
</div>