<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model common\models\Paper */

$this->title = '修改论文: ' . $model->title;
$this->params['breadcrumbs'][] = ['label' => '论文列表', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => '查看论文', 'url' => ['view', 'id' => $model->link]];
$this->params['breadcrumbs'][] = '修改论文';
?>
<div class="paper-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
