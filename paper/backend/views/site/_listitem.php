<?php
use yii\helpers\Html;
?>

<div class="post">
    <div class="title">
        <h2><a href="<?=$model->url;?>"><?= Html::encode($model->title);?></a></h2>
    </div>

    <div class="content">
        摘要：<?= $model->beginning;?>
    </div>

    <br>
    <div class="nav">
        关键词：<?= implode(',',$model->keywordLinks);?>
    </div>

</div>