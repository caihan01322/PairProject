<?php
namespace common\models;
use Yii;
class Keyword extends \yii\db\ActiveRecord
{
    public static function tableName()
    {
        return 'keyword';
    }
    public function rules()
    {
        return [
            [['name'], 'required'],
            [['frequency'], 'integer'],
            [['name'], 'string', 'max' => 128],
        ];
    }
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

    public static function findKeyWordWeights($limit=20)
    {
        $tag_size_level = 5;

        $models=Keyword::find()->orderBy('frequency desc')->limit($limit)->all();
        $total=Keyword::find()->limit($limit)->count();
        $stepper=ceil($total/$tag_size_level);
        $keywords=array();
        $counter=1;
        if($total>0)
        {
            foreach($models as $model)
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