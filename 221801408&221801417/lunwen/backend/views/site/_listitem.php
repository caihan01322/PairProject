<?php
use yii\helpers\Html;
?>
<div class="paper">
<div class="title" style="margin-top: 36px">
<h2><a href="<?= $model->url;?>"><?= Html::encode($model->title);?></a></h2>	
	</div>
    <br>
	<div class="content">
	<?= $model->beginning;?>	
	</div>
</div>