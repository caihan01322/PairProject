<?php

namespace common\models;

use Yii;
use yii\helpers\Html;

/**
 * This is the model class for table "paper".
 *
 * @property string $title
 * @property string $abstract
 * @property string $typeandyear
 * @property string $keywords
 * @property string $releasetime
 * @property string $link
 */
class Paper extends \yii\db\ActiveRecord
{
    
    public static function tableName()
    {
        return 'paper';//表里的字段即模型类的属性
    }

    
    public function rules()
    {
        return [
            [['title', 'abstract', 'typeandyear', 'releasetime', 'link'], 'required'],
            [['abstract'], 'string'],
            [['title', 'typeandyear', 'keywords', 'releasetime', 'link'], 'string', 'max' => 255],
        ];
    }

    
    public function attributeLabels()
    {
        return [
            'title' => 'Title',
            'abstract' => 'Abstract',
            'typeandyear' => 'Typeandyear',
            'keywords' => 'Keywords',
            'releasetime' => 'Releasetime',
            'link' => 'Link',
        ];
    }


    public function  getOrgLink()
    {
        return Html::a($this->link, "//{$this->link}", ['target' => '_blank']);
    }

    public function getUrl()
    {
        return Yii::$app->urlManager->createUrl(
            ['site/detial','link'=>$this->link,'title'=>$this->title]
        );
    }

    public function getBeginning($length=288)
    {
        $tmpStr = strip_tags($this->abstract);
        $tmpLen = mb_strlen($tmpStr);

        $tmpStr = mb_substr($tmpStr,0,$length,'utf-8');
        return $tmpStr.($tmpLen>$length?'...':'');
    }

    public function getKeywordLinks()
    {
        $links=array();
        foreach(Keyword::string2array($this->keywords) as $keyword)
        {
            $links[]=Html::a(Html::encode($keyword),array('paper/index','PaperFrontSearch[keywords]'=>$keyword));
        }
        return $links;
    }

}
