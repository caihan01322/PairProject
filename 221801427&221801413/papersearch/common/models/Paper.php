<?php

namespace common\models;

use Yii;
use yii\helpers\Html;

/**
 * This is the model class for table "paper".
 *
 * @property integer $id
 * @property string $title
 * @property string $abstract
 * @property string $conference
 * @property string $keywords
 * @property string $release_time
 * @property string $link
 */
class Paper extends \yii\db\ActiveRecord
{
    private $_oldKeywords;
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'paper';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['title', 'conference', 'release_time', 'link'], 'required'],
            [['title', 'abstract', 'keywords'], 'string'],
            [['conference', 'release_time', 'link'], 'string', 'max' => 255],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'title' => '标题',
            'abstract' => '摘要',
            'conference' => '会议',
            'keywords' => '关键词',
            'release_time' => '发布时间',
            'link' => '论文链接',
        ];
    }

    public function  afterFind()
    {
        parent::afterFind();
        $this->_oldKeywords = $this->keywords;
    }

    public function afterSave($insert, $changedAttributes)
    {
        parent::afterSave($insert, $changedAttributes);
        Keyword::updateFrequency($this->_oldKeywords, $this->keywords);
    }

    public function afterDelete()
    {
        parent::afterDelete();
        Keyword::updateFrequency($this->keywords, '');
    }

    public function getUrl()
    {
        return Yii::$app->urlManager->createUrl(
            ['paper/detail','id'=>$this->id,'title'=>$this->title]);
    }

    public function getBeginning($length=288)
    {
        $tmpStr = strip_tags($this->abstract);
        $tmpLen = mb_strlen($tmpStr);

        $tmpStr = mb_substr($tmpStr,0,$length,'utf-8');
        return $tmpStr.($tmpLen>$length?'...':'');
    }

    public function getBeginning2($length=40)
    {
        $tmpStr = strip_tags($this->abstract);
        $tmpLen = mb_strlen($tmpStr);

        $tmpStr = mb_substr($tmpStr,0,$length,'utf-8');
        return $tmpStr.($tmpLen>$length?'...':'');
    }

    public function getSummary($length=40)
    {
        $tmpStr = strip_tags($this->title);
        $tmpLen = mb_strlen($tmpStr);

        $tmpStr = mb_substr($tmpStr,0,$length,'utf-8');
        return $tmpStr.($tmpLen>$length?'...':'');
    }

    public function getShort($length=40)
    {
        $tmpStr = strip_tags($this->keywords);
        $tmpLen = mb_strlen($tmpStr);
        $tmpStr = mb_substr($tmpStr,0,$length,'utf-8');
        return $tmpStr.($tmpLen>$length?'...':'');
    }

    public function  getTagLinks()
    {
        $links=array();
        foreach(Keyword::string2array($this->keywords) as $tag)
        {
            $links[]=Html::a(Html::encode($tag),array('paper/index','PaperSearch[keywords]'=>$tag));
        }
        return $links;
    }

    public function  getOrgLink()
    {
        return Html::a($this->link, "//{$this->link}", ['target' => '_blank']);
    }

}
