<?php

namespace common\models;

use Yii;

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
}
