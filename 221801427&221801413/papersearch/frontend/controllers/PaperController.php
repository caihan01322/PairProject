<?php

namespace frontend\controllers;

use Yii;
use common\models\Paper;
use common\models\PaperSearch;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use yii\filters\AccessControl;
use yii\rest\Serializer;

use common\models\Keyword;

/**
 * PaperController implements the CRUD actions for Paper model.
 */
class PaperController extends Controller
{
    /**
     * @inheritdoc
     */
    public function behaviors()
    {
        return [
            'verbs' => [
                'class' => VerbFilter::className(),
                'actions' => [
                    'delete' => ['POST'],
                ],
            ],

            'access' =>[
                'class' => AccessControl::className(),
                'rules' =>
                    [
                        [
                            'actions' => ['index'],
                            'allow' => true,
                            'roles' => ['?'],
                        ],
                        [
                            'actions' => ['index', 'detail'],
                            'allow' => true,
                            'roles' => ['@'],
                        ],
                    ],
            ],

            'pageCache'=>[
                'class'=>'yii\filters\PageCache',
                'only'=>['index'],
                'duration'=>600,
                'variations'=>[
                        Yii::$app->request->get('page'),
                        Yii::$app->request->get('PaperSearch'),
                ],
                'dependency'=>[
                        'class'=>'yii\caching\DbDependency',
                        'sql'=>'select count(id) from paper',
                ],
            ],

            'httpCache'=>[
                'class'=>'yii\filters\HttpCache',
                'only'=>['detail'],
                'etagSeed'=>function ($action,$params) {
                    $paper = $this->findModel(Yii::$app->request->get('id'));
                    return serialize([$paper->title,$paper->abstract]);
                },
                
                'cacheControlHeader' => 'public,max-age=600',
                
        ],
        ];
    }

    /**
     * Lists all Paper models.
     * @return mixed
     */
    public function actionIndex()
    {
        $keywords=Keyword::findKeywordWeights();

        $searchModel = new PaperSearch();
        $dataProvider = $searchModel->search(Yii::$app->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
            'keywords'=>$keywords,
        ]);
    }

    /**
     * Displays a single Paper model.
     * @param integer $id
     * @return mixed
     */
    public function actionView($id)
    {
        return $this->render('view', [
            'model' => $this->findModel($id),
        ]);
    }

    /**
     * Creates a new Paper model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return mixed
     */
    public function actionCreate()
    {
        $model = new Paper();

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id]);
        } else {
            return $this->render('create', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Updates an existing Paper model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param integer $id
     * @return mixed
     */
    public function actionUpdate($id)
    {
        $model = $this->findModel($id);

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id]);
        } else {
            return $this->render('update', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Deletes an existing Paper model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param integer $id
     * @return mixed
     */
    public function actionDelete($id)
    {
        $this->findModel($id)->delete();

        return $this->redirect(['index']);
    }

    /**
     * Finds the Paper model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param integer $id
     * @return Paper the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = Paper::findOne($id)) !== null) {
            return $model;
        } else {
            throw new NotFoundHttpException('The requested page does not exist.');
        }
    }

    public function actionDetail($id)
    {
        $model = $this->findModel($id);
        $keywords=Keyword::findKeywordWeights();
        
        return $this->render('detail',[
            'model'=>$model,
            'keywords'=>$keywords,
        ]);

    }

}
