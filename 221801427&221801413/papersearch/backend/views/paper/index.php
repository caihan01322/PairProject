<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel common\models\PaperSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = '论文列表';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="paper-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('添加论文', ['create'], ['class' => 'btn btn-success']) ?>
    </p>
    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'layout'=>"{summary}{items}{pager}<div class='text-right tooltip-demo'></div>",
        'summary' => "  显示 {totalCount} 篇论文中的 第 {begin} 到 {end} 篇 ",
        'formatter' => ['class' => 'yii\i18n\Formatter','nullDisplay' => ' - '],
        'pager' => [
            'prevPageLabel' => '上一页',
            'nextPageLabel' => '下一页',
            'firstPageLabel' => '首页',
            'lastPageLabel' => '末页',
            'maxButtonCount' => 10,
        ],
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            //'id',
            //'title:ntext',
            [
                'attribute' => 'title',
                'headerOptions' => ['width' => '20%'],
                'value' => function ($model) {
                    return $model->summary;
                },
                'format' => 'ntext',
            ],
            'conference',
            //'keywords:ntext',
            [
                'attribute' => 'keywords',
                'headerOptions' => ['width' => '20%'],
                'value' => function ($model) {
                    if($model->short)
                    return $model->short;
                },
                'format' => 'ntext',
            ],
            'release_time:date',
            //'link',
            [
                'attribute' => 'abstract',
                'headerOptions' => ['width' => '20%'],
                'value' => function ($model) {
                    return $model->beginning2;
                },
                'format' => 'ntext',
            ],
            ['class' => 'yii\grid\ActionColumn',
            'contentOptions' => ['style' => 'width:10%;'],
            'header'=>'操作',
            ],

        ],
    ]); ?>
</div>
