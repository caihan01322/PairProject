<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "keyword".
 *
 * @property integer $id
 * @property string $name
 * @property integer $frequency
 */
class Keyword extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'keyword';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['name'], 'required'],
            [['frequency'], 'integer'],
            [['name'], 'string', 'max' => 128],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'name' => 'Name',
            'frequency' => 'Frequency',
        ];
    }
    public static  function string2array($keywords)
    {
        return preg_split('/\s*,\s*/',trim($keywords),-1,PREG_SPLIT_NO_EMPTY);
    }

    public static  function array2string($keywords)
    {
        return implode(', ',$keywords);
    }

    public static function addKeywords($keywords)
    {
        if(empty($keywords)) return ;

        foreach ($keywords as $name)
        {
            $aKeyword = Keyword::find()->where(['name'=>$name])->one();
            $aKeywordCount = Keyword::find()->where(['name'=>$name])->count();

            if(!$aKeywordCount)
            {
                $keyword = new Keyword;
                $keyword->name = $name;
                $keyword->frequency = 1;
                $keyword->save();
            }
            else
            {
                $aKeyword->frequency += 1;
                $aKeyword->save();
            }
        }
    }

    public static function removeKeywords($keywords)
    {
        if(empty($keywords)) return ;

        foreach($keywords as $name)
        {
            $aKeyword = Keyword::find()->where(['name'=>$name])->one();
            $aKeywordCount = Keyword::find()->where(['name'=>$name])->count();

            if($aKeywordCount)
            {
                if($aKeywordCount && $aKeyword->frequency<=1)
                {
                    $aKeyword->delete();
                }
                else
                {
                    $aKeyword->frequency -= 1;
                    $aKeyword->save();
                }
            }
        }
    }

    public static function updateFrequency($oldKeywords,$newKeywords)
    {
        if(!empty($oldKeywords) || !empty($newKeywords))
        {
            $oldKeywordsArray = self::string2array($oldKeywords);
            $newKeywordsArray = self::string2array($newKeywords);

            self::addKeywords(array_values(array_diff($newKeywordsArray,$oldKeywordsArray)));
            self::removeKeywords(array_values(array_diff($oldKeywordsArray,$newKeywordsArray)));
        }
    }

    public static function findKeywordWeights($limit=10)
    {
        $keyword_size_level = 5;

        $models=Keyword::find()->orderBy('frequency desc')->limit($limit)->all();
        $total=Keyword::find()->limit($limit)->count();

        $stepper=ceil($total/$keyword_size_level);

        $keywords=array();
        $counter=1;

        if($total>0)
        {
            foreach ($models as $model)
            {
                $weight=ceil($counter/$stepper)+1;
                $keywords[$model->name]=$weight;
                $counter++;
            }
            ksort($keywords);
        }
        return $keywords;
    }
}
