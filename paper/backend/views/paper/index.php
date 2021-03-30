<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel common\models\PaperSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$cssString = "
.form-group {
    display: inline-block;
    margin: auto 7px;
};
.grid-view td {white-space:inherit;}; ";
$this->registerCss($cssString);
$this->title = '论文查找';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="paper-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php  echo $this->render('_search', ['model' => $searchModel]); ?>
  
    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'pager' => [
            'firstPageLabel' => '第一页',
            'lastPageLabel' => '最后一页',
        ],
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],
            [
                'attribute' => 'title',
                'format'=>'raw',
                'value'=>function($model){
                    return "<div style=\"width:150px;display: -webkit-box;
                                        -webkit-box-orient: vertical;
                                        -webkit-line-clamp: 3;
                                        overflow: hidden;\">".$model->title."</div>";
                },
            ],
            [
                'attribute' => 'abstract',
                'format'=>'raw',
                'value'=>function($model){
                    return "<div style=\"width:300px;display: -webkit-box;
                                        -webkit-box-orient: vertical;
                                        -webkit-line-clamp: 4;
                                        overflow: hidden;\">".$model->abstract."</div>";
                },

            ],
            [
                'attribute' => 'typeandyear',
                'format'=>'raw',
                'value'=>function($model){
                    return "<div style=\"width:110px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis\">".$model->typeandyear."</div>";
                },

            ],
            [
                'attribute' => 'keywords',
                'format'=>'raw',
                'value'=>function($model){
                    return "<div style=\"width:150px;display: -webkit-box;
                                        -webkit-box-orient: vertical;
                                        -webkit-line-clamp: 3;
                                        overflow: hidden;\">".$model->keywords."</div>";
                },

            ],
            [
                'attribute' => 'releasetime',
                'format'=>'raw',
                'value'=>function($model){
                    return "<div style=\"width:120px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis\">".$model->releasetime."</div>";
                },

            ],
            [
                'attribute' => 'link',
                'value' => function ($model) {
                    return Html::a($model->link, "{$model->link}", ['target' => '_blank']);
                },
                'format' => 'raw',
            ],

            ['class' => 'yii\grid\ActionColumn'],
        ],

    ]); ?>
</div>
