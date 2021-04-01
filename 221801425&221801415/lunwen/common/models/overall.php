<?php

namespace common\models;

use Yii;

class overall extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'overall';
    }

    public static function getOverall(){
        return overall::find()->asArray()->all();

    }
    
}
