<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel common\models\PaperSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$cssString = ".grid-view td{white-space:inherit;},";
$this->registerCss($cssString);
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
            [
                'attribute' => 'title',
                'label' => '标题',
                'format'=>'raw',
                'value'=>function($model){
                    return "<div style=\"width:180px;display: -webkit-box;
                                        -webkit-box-orient: vertical;
                                        -webkit-line-clamp: 3;
                                        overflow: hidden;\">".$model->title."</div>";
                },
            ],
            [
                'attribute' => 'abstract',
                'label' => '摘要',
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
                'label' => '所属顶会',
                'format'=>'raw',
                'value'=>function($model){
                    return "<div style=\"width:75px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis\">".$model->typeandyear."</div>";
                },

            ],
            [
                'attribute' => 'keywords',
                'label' => '关键词',
                'format'=>'raw',
                'value'=>function($model){
                    return "<div style=\"width:200px;display: -webkit-box;
                                        -webkit-box-orient: vertical;
                                        -webkit-line-clamp: 3;
                                        overflow: hidden;\">".$model->keywords."</div>";
                },

            ],
            [
                'attribute' => 'releasetime',
                'label' => '发布时间',
                'format'=>'raw',
                'value'=>function($model){
                    return "<div style=\"width:120px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis\">".$model->releasetime."</div>";
                },

            ],
            [
                'attribute' => 'link',
            ],

            ['class' => 'yii\grid\ActionColumn'],
        ],

    ]); ?>
</div>
