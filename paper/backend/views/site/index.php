<?php
use yii\widgets\ListView;
use common\models\Keyword;
use backend\components\KeywordCloud;
use yii\helpers\Html;
use yii\bootstrap\Carousel;

/* @var $this yii\web\View */

$this->title = 'Bookstore Freeloader';
?>
<div class="site-index">

    <div class="jumbotron">
        <h1>欢迎来到Bookstore Freeloader</h1>

        <p class="lead">你可以在此查找相关论文</p>

        <p><?= Html::a('开始查找', ['paper/index'], ['class' => 'btn btn-lg btn-success']) ?></p>
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
                <div class="keywordcloud">
                    <ul class="list-group">
                        <li class="list-group">
                            可能感兴趣
                        </li>
                        <li class="list-group-item">
                            <?= KeywordCloud::widget(['keywords'=>$keywords])?>
                        </li>
                    </ul>
                </div>
                    <div class="carousel-inner">
                        <div class="item active" style="height: max-content">
                            <?php echo Carousel::widget([
                                'items' => [
                                        // 包含图片和字幕的格式
                                    [
                                        'content' => '<img src="http://iccv2021.thecvf.com/sites/default/files/2020-01/bigstock-Montreal--August----Montr-315789169.jpg"/>',
                                        //'options' => [...],       //配置对应的样式
                                    ],
                                    [
                                        'content' => '<img src="https://eccv2020.eu/wp-content/uploads/2020/06/ECCV2020-newBG.jpg"/>',
                                        //'options' => [...],       //配置对应的样式
                                    ],
                                    [
                                        'content' => '<img src="http://cvpr2021.thecvf.com/sites/default/files/2020-12/CVPR_2021_Home%20Page.jpg" />',
                                        //'options' => [...],       //配置对应的样式
                                    ],
                                ]
                            ]);
                            ?>
                        </div>
                    </div>
                <div class="media">
                    <iframe width="355" height="404"
                            src="https://www.youtube.com/embed/ZoWKrxNC3QM"
                            title="YouTube video player"
                            frameborder="0"
                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                            allowfullscreen></iframe>
                </div>

            </div>
        </div>

    </div>
</div>
