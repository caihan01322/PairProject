<?php

namespace common\models;

use Yii;

class taganalysis extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'taganalysis';
    }

    public static function getDataPagercvrp(){
        return taganalysis::find()->where([

            'AND',
        
            ['year' => 2020],
        
            ['type' => 'CVPR'],
        
        ])->orderBy('frequency DESC')->asArray()->all();

    }
    public static function getDataPagereccv(){
        return taganalysis::find()->where([

            'AND',
        
            ['year' => 2020],
        
            ['type' => 'ECCV'],
        
        ])->orderBy('frequency DESC')->asArray()->all();

    }
    public static function getDataPagericcv(){
        return taganalysis::find()->where([

            'AND',
        
            ['year' => 2019],
        
            ['type' => 'ICCV'],
        
        ])->orderBy('frequency DESC')->asArray()->all();

    }

}