<?php
namespace backend\components;
use Yii;
use yii\Base\Widget;
use yii\helpers\Html;
class KeywordsCloudWidget extends Widget
{
    public $keywords;

    public function init()
    {
        parent::init();
    }

    public function run()
    {
        $keywordString='';
        $fontStyle=array("6"=>"danger",
        "5"=>"info",
        "4"=>"warning",
        "3"=>"primary",
        "2"=>"success",
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