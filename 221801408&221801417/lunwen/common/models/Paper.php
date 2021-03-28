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
            [['title', 'abstract', 'typeandyear', 'releasetime', 'link'], 'required'],
            [['abstract'], 'string'],
            [['title', 'typeandyear', 'keywords', 'releasetime', 'link'], 'string', 'max' => 255],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'title' => '会议名称',
            'abstract' => '摘要',
            'typeandyear' => '类型年份',
            'keywords' => '关键词',
            'releasetime' => '会议时间',
            'link' => '原文链接',
        ];
    }
    public function afterFind()
    {
        parent::afterFind();
        $this->_oldKeywords = $this->keywords;
    }
    public function afterSave($insert,$changedAttributes)
    {
        parent::afterSave($insert,$changedAttributes);
        Keyword::updateFrequency($this->_oldKeywords,$this->keywords);
    }
    public function afterDelete()
    {
        parent::afterDelete();
        Keyword::updateFrequency($this->keywords,'');
    }
  
}