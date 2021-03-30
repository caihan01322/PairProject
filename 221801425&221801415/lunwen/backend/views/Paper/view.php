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
        <?= Html::a('修改', ['update', 'id' => $model->link], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('删除', ['delete', 'id' => $model->link], [
            'class' => 'btn btn-danger',
            'data' => [
                'confirm' => '您确定删除这篇论文吗?',
                'method' => 'post',
            ],
        ]) ?>
    </p>

    <?= DetailView::widget([
        'model' => $model,
        'attributes' => [
            'title',
            'abstract:ntext',
            'typeandyear',
            'keywords',
            'releasetime',
            'link',
        ],
    ]) ?>

</div>
