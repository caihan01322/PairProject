<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model common\models\Paper */

$this->title = '添加论文';
$this->params['breadcrumbs'][] = ['label' => '论文管理', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="paper-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
