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
            'title' => '标题',
            'abstract' => '摘要',
            'typeandyear' => '类别和时间',
            'keywords' => '关键词',
            'releasetime' => '发布时间',
            'link' => '链接',
        ];
    }
}
