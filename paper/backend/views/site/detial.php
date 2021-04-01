<?php
use yii\widgets\ListView;
use common\models\Keyword;
use yii\Base\widget;
use backend\components\KeywordCloud;
use yii\helpers\Html;
use yii\helpers\HTMLPurifier;
?>
<div class="site-index">

    

    <div class="body-content">

        <div class="row">

                <div class="post">
                    <div class="title">
                        <h2>标题：<?= Html::encode($model->title);?></h2>
                    </div>
                </div>
                <br>
                <div class="content">
                    <h4>摘要：<?= HTMLPurifier::process($model->abstract);?></h4>
                </div>
                <br>
                <div class="keyword">
                    <h5>关键字：<?= implode(',',$model->keywordLinks);?></h5>
                </div>
              
        </div>

    </div>
</div>