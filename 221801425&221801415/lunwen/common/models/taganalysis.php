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
        return 'data—_pager';
    }

    public static function getDataPagertw(){
        return taganalysis::find()->where(['year'=>2020])->all();

    }
    public static function getDataPagerni(){
        return taganalysis::find()->where(['year'=>2019])->all();

    }
    public static function getDataPagerei(){
        return taganalysis::find()->where(['year'=>2018])->all();

    }
    public static function getDataPagerse(){
        return taganalysis::find()->where(['year'=>2017])->all();

    }
}
