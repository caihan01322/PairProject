<?php

/* @var $this yii\web\View */
/* @var $form yii\bootstrap\ActiveForm */
/* @var $model \frontend\models\ContactForm */

use yii\helpers\Html;
use yii\bootstrap\ActiveForm;
use yii\captcha\Captcha;

//require 'phpQuery.php';
//require 'QueryList.php';
//use QL\QueryList;

$this->title = 'Contact';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="site-contact">
    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        如果你发现任何关于此网站的问题，请联系我们，谢谢
    </p>

    <div class="row">
        <div class="col-lg-5">
            <?php $form = ActiveForm::begin(['id' => 'contact-form']); ?>

                <?= $form->field($model, 'name')->textInput(['autofocus' => true]) ?>

                <?= $form->field($model, 'email') ?>

                <?= $form->field($model, 'subject') ?>

                <?= $form->field($model, 'body')->textArea(['rows' => 6]) ?>

                <?= $form->field($model, 'verifyCode')->widget(Captcha::className(), [
                    'template' => '<div class="row"><div class="col-lg-3">{image}</div><div class="col-lg-6">{input}</div></div>',
                ]) ?>

                <div class="form-group">
                    <?= Html::submitButton('提交', ['class' => 'btn btn-primary', 'name' => 'contact-button']) ?>
                </div>

            <?php ActiveForm::end(); ?>
        </div>
    </div>

</div>

<?php //以下实现简单的爬取事件
// require 'phpQuery.php';
// require 'QueryList.php';
// use \QL\QueryList;
// error_reporting(1);
// 
// ini_set('max_execution_time', '0');
//  
// $html = "https://openaccess.thecvf.com/CVPR2018?day=2018-06-19" ;
// $baseUrl = "https://openaccess.thecvf.com/" ;
// 
// $data = QueryList::Query($html,array(
    // "ptitle"=>array('.ptitle>a', 'href','',function($content) use($baseUrl){
        // return $baseUrl.$content;
    // }),
// ))->getData(function($item){
// 
// $item['ptitle'] = QueryList::Query($item['ptitle'],array(
        // "title" => array('#papertitle','text'),
        // 'authors' =>array('#authors','text'),
        // 'abstract' =>array('#abstract','text'),
    // ))->data;
// return $item;
// 
// });
// 
// foreach ($data as $arr) {
    // echo "title:";print_r($arr['ptitle'][0]['title']); echo "<br/>";echo "<br/>";
    // echo "authors:";print_r($arr['ptitle'][0]['authors']); echo "<br/>";echo "<br/>";
    // echo "abstract:";print_r($arr['ptitle'][0]['abstract']); echo "<br/>";echo "<br/>";echo "<br/>";
// } 
// 
// exit;
?>