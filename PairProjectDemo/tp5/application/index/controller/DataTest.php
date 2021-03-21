<?php
namespace app\index\controller;

use app\index\model\Paper;
use think\Controller;

class DataTest extends Controller
{
    public function index()
    {
        return 'datatest';
    }
    public function getModelData()
    {
        $data=Paper::select();
        return json($data);
    }
}