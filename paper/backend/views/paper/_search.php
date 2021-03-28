<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

?>

<div class="paper-search">

    <?php $form = ActiveForm::begin([
        'action' => ['index'],
        'method' => 'get',
    ]); ?>

    <?= $form->field($model, 'title') ?>

    <?= $form->field($model, 'abstract') ?>

    <?= $form->field($model, 'typeandyear') ?>

    <?= $form->field($model, 'keywords') ?>

    <?= $form->field($model, 'releasetime') ?>

    <?php // echo $form->field($model, 'link') ?>

    <div class="form-group">
        <?= Html::submitButton('Search', ['class' => 'btn btn-primary']) ?>
        <?= Html::resetButton('Reset', ['class' => 'btn btn-default']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
