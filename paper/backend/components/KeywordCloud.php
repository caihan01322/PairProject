<?php
namespace backend\components;
use Yii;
use yii\Base\widget;
use yii\helpers\Html;

class KeywordCloud extends widget
{
    public $keywords;
    public function init()
    {
        parent::init();
    }
    public function run()
    {
        $keywordString='';
        $fontStyle=array("6"=>"danger",//红色
                        "5"=>"info",//浅蓝色
                        "4"=>"warning",//黄色
                        "3"=>"primary",//蓝色
                        "2"=>"success",//绿色
    );
    foreach ($this->keywords as $keyword=>$weight)
    {
        $url = Yii::$app->urlManager->createUrl(['site/index','PaperSearch[keywords]'=>$keyword]);
        $keywordString.='<a href="'.$url.'">'.
                ' <h'.$weight.' style="display:inline-block;"><span class="label label-'
                .$fontStyle[$weight].'">'.$keyword.'</span></h'.$weight.'></a>';
    }
    sleep(3);
    return $keywordString;
    }
}