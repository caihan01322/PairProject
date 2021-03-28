<?php

use yii\helpers\Html;
use yii\grid\GridView;


$this->title = '论文管理';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="paper-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <h3>搜索</h3>
    <?php  echo $this->render('_search', ['model' => $searchModel]); ?>
  
    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'title',
            'abstract:ntext',
            'typeandyear',
            'keywords',
            'releasetime',
            'link',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>
</div>
