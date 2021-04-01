<?php

use yii\helpers\Html;
use yii\grid\GridView;
use yii\widgets\ListView;
use common\models\Paper;
use frontend\components\TagsCloudWidget;
use yii\bootstrap\Carousel

/* @var $this yii\web\View */
/* @var $searchModel common\models\PaperSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

?>

<div class="container">

    <div class="row">

        <div class="col-md-8">

            <ol class="breadcrumb">
                <li><a href="<?= Yii::$app->homeUrl;?>">首页</a></li>
                <li>论文列表</li>
            </ol>

            <div class="img-wall">
                <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="4000" >
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                        <li data-target="#myCarousel" data-slide-to="3"></li>
                        <li data-target="#myCarousel" data-slide-to="4"></li>
                    </ol>
                    <div class="carousel-inner">
                        <div class="item active">
                            <img src="images/bg0.jpg" alt="First slide">
                            <div class="carousel-caption">Virtual Reality</div>
                        </div>
                        <div class="item">
                            <img src="images/bg1.jpg" alt="Second slide">
                            <div class="carousel-caption">Computer Vision</div>
                        </div>
                        <div class="item">
                            <img src="images/bg2.jpg" alt="Third slide">
                            <div class="carousel-caption">Artificial Intelligence</div>
                        </div>
                        <div class="item">
                            <img src="images/bg3.jpg" alt="Fourth slide">
                            <div class="carousel-caption">Training</div>
                        </div>
                        <div class="item">
                            <img src="images/bg4.jpg" alt="Fifth slide">
                            <div class="carousel-caption">Data Visualization</div>
                        </div>
                    </div>

                    <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
                    <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>

                </div>
            </div>
            
            <div class="searchbox">
                <ul class="list-group">
                    <li class="list-group-item">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span> 查找论文（
                        <?= Paper::find()->count();?>
                        ）
                    </li>
                    <li class="list-group-item">
                        <form class="form-inline" action="<?= Yii::$app->urlManager->createUrl(['paper/index']);?>" id="w0" method="get">
                            <div class="form-group" style="margin-left:15px">
                                <input type="text" class="form-control" name="PaperSearch[title]" id="w0input" placeholder="按标题">
                                <input type="text" class="form-control" name="PaperSearch[conference]" id="w0input" placeholder="按会议">
                                <input type="text" class="form-control" name="PaperSearch[keywords]" id="w0input" placeholder="按关键词">
                            </div>
                            <button type="submit" class="btn btn-default" style="width:150px;margin-left:5px">搜索</button>
                        </form>
                    </li>
                </ul>
            </div>

            <?= ListView::widget([
                'id'=>'paperList',
                'dataProvider'=>$dataProvider,
                'itemView'=>'_listitem',//子视图,显示一篇论文的标题等内容.
                'layout'=>'{items} {pager}',
                'pager'=>[
                    'maxButtonCount'=>10,
                    'nextPageLabel'=>Yii::t('app','下一页'),
                    'prevPageLabel'=>Yii::t('app','上一页'),
                ],
            ])?>
            <p class="link-item"><a class="btn btn-default" href="http://cvpr2020.thecvf.com/">CVPR2020官网 &raquo;</a>
            <a class="btn btn-default" href="https://eccv2020.eu/">ECCV2020官网 &raquo;</a>
            <a class="btn btn-default" href="https://iccv2019.thecvf.com/">ICCV2019官网&raquo;</a></p>
        </div>

        <div class="col-md-4">
        
            <div class="tagcloudbox">
                <ul class="list-group">
                    <li class="list-group-item">
                        <span class="glyphicon glyphicon-tags" aria-hidden="true"></span> Top10关键词
                    </li>
                    <li class="list-group-item">
                        <?= TagsCloudWidget::widget(['keywords'=>$keywords]);?>
                    </li>
                </ul>
            </div>
            <br>
            <div class="pic">
                <img src="images/pic0.png" alt="picture0">
                <img src="images/pic1.png" alt="picture1">
            </div>
        </div>
    </div>
</div>
