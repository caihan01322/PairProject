<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model common\models\Paper */

$this->title = $model->title;
$this->params['breadcrumbs'][] = ['label' => '论文管理', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="paper-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('修改', ['update', 'id' => $model->id], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('删除', ['delete', 'id' => $model->id], [
            'class' => 'btn btn-danger',
            'data' => [
                '确认' => '您确定要删除这篇论文吗？',
                'method' => 'post',
            ],
        ]) ?>
    </p>

    <?= DetailView::widget([
        'model' => $model,
        'attributes' => [
            //'id',
            'title:ntext',
            'abstract:ntext',
            'conference',
            'keywords:ntext',
            'release_time:date',
            [
                'attribute' => 'link',
                'label' => '链接',
                'format' => ['url',['target' => '_blank']],
            ],
        ],
        
    ]) ?>

</div>
