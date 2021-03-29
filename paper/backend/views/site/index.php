<?php
use yii\widgets\ListView;
use common\models\Keyword;
/* @var $this yii\web\View */

$this->title = 'My Yii Application';
?>
<div class="site-index">

    <div class="jumbotron">
        <h1>欢迎来到Bookstore Freeloader</h1>

        <p class="lead">你可以在此查找相关论文</p>

        <p><a class="btn btn-lg btn-success" href="http://www.yiiframework.com">Get started with Yii</a></p>
    </div>

    <div class="body-content">

        <div class="row">
            <div class="col-md-8">
            <?= ListView::widget([
                'id'=>'paperList',
                'dataProvider'=>$dataProvider,
                'itemView'=>'_listitem',//子视图，显示一篇文章的标题等内容
                'layout'=>'{items} {pager}',
                'pager'=>[
                    'maxButtonCount'=>10,
                    'nextPageLabel'=>Yii::t('app','下一页'),
                    'prevPageLabel'=>Yii::t('app','上一页'),
                ],
            ])?>
            </div>
            <div class="col-md-4">
                词云
            </div>
        </div>

    </div>
</div>
